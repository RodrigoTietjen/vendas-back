package br.tietjen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.tietjen.dto.LoginDTO;
import br.tietjen.dto.TokenDTO;
import br.tietjen.dto.TokenResponseDTO;
import br.tietjen.security.AuthenticatorServiceImpl;
import br.tietjen.security.JWTFilter;
import br.tietjen.security.PasswordEncode;

@CrossOrigin
@RestController
@RequestMapping("/api/login")
public class AuthenticatorController {

	@Autowired
	private AuthenticatorServiceImpl authenticatorService;

	@PostMapping(value = "/authenticate", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> authorize(@RequestBody LoginDTO loginDTO) {
		TokenDTO token = authenticatorService.authorize(loginDTO);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + token.getToken());
		return new ResponseEntity<TokenDTO>(token, httpHeaders, HttpStatus.OK);
	}

	@PostMapping(value = "/validate", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TokenResponseDTO> validateToken(@RequestBody TokenDTO tokenDTO) {
		return authenticatorService.validateToken(tokenDTO);
	}
	
	@PostMapping(value = "/gerar-senha", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> gerarSenha(@RequestBody LoginDTO login) {
		String encode = new PasswordEncode().encode(login.getPassword());
		return new ResponseEntity<String>(encode, HttpStatus.OK);
	}
}