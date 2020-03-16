CREATE TABLE public.usuario (
	id int4 NOT NULL,
	username varchar NOT NULL,
	"password" varchar NOT NULL,
	nome varchar NOT NULL,
	CONSTRAINT usuario_pkey PRIMARY KEY (id)
);

CREATE TABLE public.venda (
	id int4 NOT NULL,
	"data" timestamp NOT NULL,
	razao_social varchar NOT NULL,
	cnpj varchar(14) NOT NULL,
	telefone varchar(12) NOT NULL,
	email varchar NOT NULL,
	situacao varchar NOT NULL,
	id_usuario int4 NOT NULL,
	CONSTRAINT venda_pkey PRIMARY KEY (id),
	CONSTRAINT venda_fk FOREIGN KEY (id_usuario) REFERENCES usuario(id)
);

CREATE TABLE public.venda_produto (
	id int4 NOT NULL,
	produto_descricao varchar NOT NULL,
	quantidade int4 NOT NULL,
	preco numeric(10,2) NOT NULL,
	total numeric(10,2) NOT NULL,
	id_venda int4 NOT NULL,
	CONSTRAINT venda_produto_pkey PRIMARY KEY (id),
	CONSTRAINT venda_produto_venda_fk FOREIGN KEY (id_venda) REFERENCES venda(id)
);

CREATE TABLE public.venda_resumo (
	id int4 NOT NULL,
	quantidade_produtos int4 NOT NULL,
	quantidade_itens int4 NOT NULL,
	valor_total numeric(10,2) NOT NULL,
	id_venda int4 NOT NULL,
	CONSTRAINT venda_resumo_pkey PRIMARY KEY (id),
	CONSTRAINT nfe_resumo_id_venda_fkey FOREIGN KEY (id_venda) REFERENCES venda(id)
);
 
create sequence usuario_sequence
       MINVALUE 1  
       START WITH 1
       INCREMENT BY 1;
      
create sequence venda_sequence
       MINVALUE 1  
       START WITH 1
       INCREMENT BY 1;

create sequence venda_produto_sequence
       MINVALUE 1  
       START WITH 1
       INCREMENT BY 1;

create sequence venda_resumo_sequence
       MINVALUE 1  
       START WITH 1
       INCREMENT BY 1;

INSERT INTO public.usuario
(id, username, "password", nome)
VALUES(1, 'admin', 'rIGDvfVqy04=', 'Admin');
