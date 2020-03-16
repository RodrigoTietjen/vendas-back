package br.tietjen.security;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import br.tietjen.dto.TokenResponseDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;

@Component
public class TokenProvider implements InitializingBean {

	public static final String AUTHORITIES_KEY = "auth";

	public static final String USERNAME = "username";

	public static final String NOME = "nome";

	public static final String IDUSUARIO = "idUsuario";

	public static final String DATEEXPIRATION = "dataExpiracao";

	private static final String JWT_SECRET = "f6a6645193d7ffb9007c8c20e1b57ee2914336c8590e5dccd06f911f4225b11520445557044fd06e03b26dde895d93098b781c6103f0e4c9850e41f0336afcef";

	private static final int TOKENVALIDITYINSECONDS = 36000;

	private Key key;

	private long tokenValidityInMilliseconds;
	
	public TokenProvider() {
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		String secret = JWT_SECRET;
		byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);

		this.key = Keys.hmacShaKeyFor(keyBytes);
		this.tokenValidityInMilliseconds = 1000 * TOKENVALIDITYINSECONDS;
	}

	public String createToken(ContextSecurity contextSecurity) {
		long now = new Date().getTime();
		Date validity;
		
		validity = new Date(now + this.tokenValidityInMilliseconds);
		
		return gerarToken(contextSecurity, validity);
	}

	public String gerarToken(ContextSecurity contextSecurity, Date validity) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(IDUSUARIO, contextSecurity.getIdUsuario());
		map.put(NOME, contextSecurity.getNome());
		map.put(USERNAME, contextSecurity.getUsername());
		map.put(DATEEXPIRATION, validity);
		return Jwts.builder().setSubject(contextSecurity.getUsername()).addClaims(map).signWith(key, SignatureAlgorithm.HS512).setExpiration(validity).compact();
	}

	public Authentication getAuthentication(String token) {
		Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();

		Long idUsuario = ((Integer) claims.get(IDUSUARIO)).longValue();

		String nome = (String) claims.get(NOME);

		Date dataExpiracao = new Date((Long) claims.get(DATEEXPIRATION));

		ContextSecurity principal = new ContextSecurity(idUsuario, nome, claims.getSubject(), "", dataExpiracao);

		return new UsernamePasswordAuthenticationToken(principal, token, new ArrayList<GrantedAuthority>());
	}

	public TokenResponseDTO validateToken(String authToken) {
		TokenResponseDTO tokenResponseDTO = new TokenResponseDTO();
		tokenResponseDTO.setValid(true);
		try {
			Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(authToken).getBody();
			tokenResponseDTO.setInfoToken(claims);
		} catch (SecurityException | MalformedJwtException e) {
			tokenResponseDTO.setValid(false);
			tokenResponseDTO.setMessage("Assinatura JWT inválida.");
			tokenResponseDTO.setStackTrace(e.getMessage());
		} catch (ExpiredJwtException e) {
			tokenResponseDTO.setValid(false);
			tokenResponseDTO.setMessage("Token JWT expirado.");
			tokenResponseDTO.setStackTrace(e.getMessage());
		} catch (UnsupportedJwtException e) {
			tokenResponseDTO.setValid(false);
			tokenResponseDTO.setMessage("Token JWT não suportado.");
			tokenResponseDTO.setStackTrace(e.getMessage());
		} catch (IllegalArgumentException e) {
			tokenResponseDTO.setValid(false);
			tokenResponseDTO.setMessage("O compactador de tokens JWT do handler é inválido.");
			tokenResponseDTO.setStackTrace(e.getMessage());
		}
		return tokenResponseDTO;
	}
}
