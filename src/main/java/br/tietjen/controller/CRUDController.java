package br.tietjen.controller;

import java.io.Serializable;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.tietjen.mapper.Map;
import br.tietjen.service.CRUDService;

@Transactional
public class CRUDController<D extends Serializable, E extends Serializable, S extends CRUDService<E>, M extends Map<D, E>> {

	private S service;

	private M mapper;

	public CRUDController(S service, M mapper) {
		this.mapper = mapper;
		this.service = service;

	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/paginator")
	public ResponseEntity<Object> findAll(Pageable pageable) {
		return new ResponseEntity<>(service.findAll(pageable).map(p -> mapper.toDto(p)), HttpStatus.OK);
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> findAll() {
		return new ResponseEntity<>(getMapper().entitiesToDtos(service.findAll()), HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> findByID(@PathVariable Long id) {
		return new ResponseEntity<>(mapper.toDto(service.find(id)), HttpStatus.OK);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> insert(@RequestBody D dto) {
		return new ResponseEntity<>(mapper.toDto(service.insert(mapper.toEntity(dto))), HttpStatus.OK);
	}
	
	@PutMapping(value = "/atualizar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> update(@RequestBody D dto) {
		return new ResponseEntity<>(getMapper().toDto(getService().update(getMapper().toEntity(dto))), HttpStatus.OK);
	}
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	public S getService() {
		return service;
	}

	public M getMapper() {
		return mapper;
	}

}