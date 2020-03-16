package br.tietjen.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.tietjen.enums.EnumSituacao;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "venda")
@Getter
@Setter
public class Venda implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "venda_sequence", sequenceName = "venda_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "venda_sequence")
	private Long id;

	@Column(name = "data")
	private LocalDateTime data;

	@Column(name = "razao_social")
	private String razaoSocial;

	@Column(name = "cnpj", length = 14)
	private String cnpj;

	@Column(name = "telefone", length = 12)
	private String telefone;

	@Column(name = "email")
	private String email;

	@Column(name = "situacao")
	@Enumerated(EnumType.STRING)
	private EnumSituacao situacao;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "venda", orphanRemoval = true, targetEntity = VendaProduto.class)
	private Set<VendaProduto> produtos;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "venda", orphanRemoval = true, targetEntity = VendaResumo.class)
	private VendaResumo vendaResumo;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
}
