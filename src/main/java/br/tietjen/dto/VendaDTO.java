package br.tietjen.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import br.tietjen.enums.EnumSituacao;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private LocalDateTime data = LocalDateTime.now();

	private String razaoSocial;

	private String cnpj;

	private String telefone;

	private String email;

	private EnumSituacao situacao = EnumSituacao.ABERTO;

	private Set<VendaProdutoDTO> produtos;

	private VendaResumoDTO vendaResumo;
	
	private UsuarioDTO usuario;
}
