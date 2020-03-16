package br.tietjen.service.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.tietjen.exception.RegisterNotFoundException;
import br.tietjen.service.CRUDService;

public class CRUDServiceImpl<E extends Serializable, R extends JpaRepository<E, Long>> implements CRUDService<E> {

    private R repository;

    public CRUDServiceImpl(R repository) {
        this.repository = repository;
        
    }

    @Override
    public List<E> findAll() {
        return repository.findAll();
    }
    
    @Override
    public E insert(E entity) {
    	validatesDependentEntities(entity);
    	
        entity = repository.save(entity);

        return entity;

    }

    @Override
    public E update(E entity) {
    	
    	validatesDependentEntities(entity);
    	
        entity = repository.save(entity);

        return entity;
    }

    protected void validatesDependentEntities(E entity) {
	}
    
    @Override
    public void delete(Long idEntity) {

        Optional<E> found = repository.findById(idEntity);

        if (!found.isPresent()) {
            throw new RegisterNotFoundException(getEntityClass().getSimpleName(), idEntity);
        }
        
        repository.delete(found.get());

    }

    @Override
    public E find(Long primaryKey) {
        Optional<E> entity = repository.findById(primaryKey);
        if (entity.isPresent()) {
            return entity.get();
        }
        return null;
    }

	@Override
	public Page<E> findAll(Pageable pageable) {
		 return repository.findAll(pageable);
	}
	
    public R getRepository() {
        return repository;
    }

    @SuppressWarnings("unchecked")
    public Class<E> getEntityClass() {
        Type[] actualTypeArguments = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments();
        return (Class<E>) actualTypeArguments[0];
    }

}