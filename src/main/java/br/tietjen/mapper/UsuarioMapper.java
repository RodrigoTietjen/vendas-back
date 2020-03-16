package br.tietjen.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.tietjen.dto.UsuarioDTO;
import br.tietjen.entity.Usuario;

@Mapper(componentModel="spring")
public interface UsuarioMapper extends Map<UsuarioDTO, Usuario> {

	@Mapping(target = "password", ignore = true)
	Usuario toEntity(UsuarioDTO dto);
}
