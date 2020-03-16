package br.tietjen.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContextSecurity implements UserDetails, CredentialsContainer {

	private static final long serialVersionUID = 1L;

	private Long idUsuario;

	private String nome;
	
	private String username;

	private String password;
	
	private Date dataExpiracao;
	
	public ContextSecurity(Long idUsuario, String nome, String username, String password, Date dataExpiracao) {
		this.idUsuario = idUsuario;
		this.username = username;
		this.nome = nome;
		this.dataExpiracao = dataExpiracao;
		this.password = password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return new ArrayList<GrantedAuthority>();
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public void eraseCredentials() {
		password = null;
	}

}