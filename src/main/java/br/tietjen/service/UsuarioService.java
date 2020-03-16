package br.tietjen.service;

import br.tietjen.entity.Usuario;

public interface UsuarioService {

	Usuario findByUsername(String username);
}
