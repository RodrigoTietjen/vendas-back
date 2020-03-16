package br.tietjen.dto;

import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Setter
@AllArgsConstructor
public class TokenDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String token;
	
	private String nome;
	
	private Date dataExpiracao;
	
	private String username;
	
	private Long idUsario;
	
	public TokenDTO() {
		
	}
}
