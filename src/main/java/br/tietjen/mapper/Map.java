package br.tietjen.mapper;

import java.io.Serializable;
import java.util.List;

public interface Map<D extends Serializable, E extends Serializable> {

	D toDto(E entity);
	
	E toEntity(D dto);
	
	List<D> entitiesToDtos(List<E> entities);
	
	List<E> dtosToEntities(List<D> dtos);
}
