package br.tietjen.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.tietjen.dto.VendaResumoDTO;
import br.tietjen.entity.VendaResumo;

@Mapper(componentModel = "spring")
public interface VendaResumoMapper extends Map<VendaResumoDTO, VendaResumo> {

	@Mapping(target = "venda", ignore = true)
	VendaResumo toEntity(VendaResumoDTO dto);
}
