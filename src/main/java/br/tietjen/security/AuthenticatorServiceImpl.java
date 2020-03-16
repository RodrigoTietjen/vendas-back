package br.tietjen.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.tietjen.dto.LoginDTO;
import br.tietjen.dto.TokenDTO;
import br.tietjen.dto.TokenResponseDTO;

@Service
public class AuthenticatorServiceImpl {

	@Autowired
	private TokenProvider tokenProvider;

	@Autowired
	private AuthenticationManagerBuilder authenticationManagerBuilder;
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public TokenDTO authorize(LoginDTO loginDTO) {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());

		Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		ContextSecurity contextSecurity = (ContextSecurity) authentication.getPrincipal();
		
		String jwt = tokenProvider.createToken(contextSecurity);

		return new TokenDTO(jwt, contextSecurity.getNome(), contextSecurity.getDataExpiracao(), contextSecurity.getUsername(), contextSecurity.getIdUsuario());
	}

	public ResponseEntity<TokenResponseDTO> validateToken(TokenDTO token) {
		TokenResponseDTO tokenResponseDTO = tokenProvider.validateToken(token.getToken());
		if (!tokenResponseDTO.getValid()) {
			return new ResponseEntity<TokenResponseDTO>(tokenResponseDTO, HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<TokenResponseDTO>(tokenResponseDTO, HttpStatus.OK);
	}

}
