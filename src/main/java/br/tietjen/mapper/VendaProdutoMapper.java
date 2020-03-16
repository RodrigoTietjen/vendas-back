package br.tietjen.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.tietjen.dto.VendaProdutoDTO;
import br.tietjen.entity.VendaProduto;

@Mapper(componentModel = "spring")
public interface VendaProdutoMapper extends Map<VendaProdutoDTO, VendaProduto> {

	@Mapping(target = "venda", ignore = true)
	VendaProduto toEntity(VendaProdutoDTO dto);
}
