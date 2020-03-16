package br.tietjen.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface CRUDService<E extends Serializable> {

	E insert(E entity);

	E update(E entity);

	void delete(Long idEntity);

	E find(Long primaryKey);

	List<E> findAll();
	
	Page<E> findAll(Pageable pageable);

}