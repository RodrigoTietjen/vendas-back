package br.tietjen.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "venda_resumo")
@Getter
@Setter
public class VendaResumo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "venda_resumo_sequence", sequenceName = "venda_resumo_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "venda_resumo_sequence")
	private Long id;

	@Column(name = "quantidade_produtos")
	private Long quantidadeProdutos;

	@Column(name = "quantidade_itens")
	private Long quantidadeItens;

	@Column(name = "valor_total", precision = 10, scale = 2)
	private BigDecimal valorTotal;
	
	@OneToOne(targetEntity = Venda.class)
	@JoinColumn(name = "id_venda")
	private Venda venda;

}
