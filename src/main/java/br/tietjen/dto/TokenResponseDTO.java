package br.tietjen.dto;

import java.io.Serializable;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Boolean valid;

	private String message;

	private String stackTrace;

	private Map<String, Object> infoToken;

}
