package br.tietjen.exception;

public class RegisterNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RegisterNotFoundException(String className, Long id) {
		super(String.format("O registro de %s com ID %s não encontrado", className, id));
	}
}
