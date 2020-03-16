package br.tietjen.mapper;

import org.mapstruct.Mapper;

import br.tietjen.dto.VendaDTO;
import br.tietjen.entity.Venda;

@Mapper(componentModel = "spring", uses = { VendaProdutoMapper.class, VendaResumoMapper.class, UsuarioMapper.class })
public interface VendaMapper extends Map<VendaDTO, Venda> {

}
