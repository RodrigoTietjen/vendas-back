package br.tietjen.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendaResumoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private Long quantidadeProdutos;

	private Long quantidadeItens;

	private BigDecimal valorTotal;

}
