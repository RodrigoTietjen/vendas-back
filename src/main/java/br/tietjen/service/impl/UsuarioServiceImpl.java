package br.tietjen.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.tietjen.entity.Usuario;
import br.tietjen.repository.UsuarioRespository;
import br.tietjen.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRespository repository;

	@Override
	public Usuario findByUsername(String username) {
		return repository.findByUsername(username);
	}
	
}
