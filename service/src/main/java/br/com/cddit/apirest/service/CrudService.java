package br.com.cddit.apirest.service;

import javax.persistence.EntityNotFoundException;

import br.com.cddit.apirest.model.BaseEntity;
import br.com.cddit.apirest.model.common.GenericFilter;
import br.com.cddit.apirest.model.common.PageableData;

public interface CrudService<E extends BaseEntity, F extends GenericFilter> {

	public E saveOrUpdate(E u);

	public E findById(Long id) throws EntityNotFoundException;

	public PageableData<E> findByFilter(F userFilter);

	public void delete(E e);
}
