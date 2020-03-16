package br.tietjen.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "venda_produto")
@Getter
@Setter
public class VendaProduto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "venda_produto_sequence", sequenceName = "venda_produto_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "venda_produto_sequence")
	private Long id;

	@Column(name = "produto_descricao")
	private String produtoDescricao;

	@Column(name = "quantidade")
	private Long quantidade;

	@Column(name = "preco", precision = 10, scale = 2)
	private BigDecimal preco;

	@Column(name = "total")
	private BigDecimal total;

	@ManyToOne
	@JoinColumn(name = "id_venda")
	private Venda venda;
}
