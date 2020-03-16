package br.tietjen.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.tietjen.entity.Usuario;

public interface UsuarioRespository extends JpaRepository<Usuario, Long> {

	Usuario findByUsername(String username);

}