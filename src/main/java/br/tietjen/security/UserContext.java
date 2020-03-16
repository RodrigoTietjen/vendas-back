package br.tietjen.security;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserContext {

	public ContextSecurity getContextSecurity() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return (ContextSecurity) auth.getPrincipal();
	}
}
