package br.tietjen.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendaProdutoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String produtoDescricao;

	private Long quantidade;

	private BigDecimal preco;

	private BigDecimal total;

}
