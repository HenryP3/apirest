package br.com.cddit.apirest.service;

import java.util.Objects;

import javax.persistence.EntityNotFoundException;

import br.com.cddit.apirest.model.BaseEntity;
import br.com.cddit.apirest.model.common.GenericFilter;
import br.com.cddit.apirest.model.common.PageableData;
import br.com.cddit.apirest.repository.common.BaseRepository;

public abstract class AbstractCrudService<T extends BaseEntity, F extends GenericFilter, R extends BaseRepository<T, F>>
		implements CrudService<T, F> {

	public abstract R getRepo();

	@Override
	public PageableData<T> findByFilter(final F userFilter) {
		Objects.requireNonNull(userFilter, "Userfilter cannot be null");
		return getRepo().findByFilter(userFilter);
	}

	@Override
	public T saveOrUpdate(final T u) {
		Objects.requireNonNull(u, "entity must be not null");
		return getRepo().saveOrUpdate(u);
	}

	@Override
	public T findById(final Long id) throws EntityNotFoundException {
		final T entity = getRepo().findById(id);
		if (entity == null) {
			throw new EntityNotFoundException(
					String.format("Could not find %s with id %d", getRepo().getDomainClass(), id));
		}
		return entity;
	}

	@Override
	public void delete(final T e) {
		// TODO Auto-generated method stub

	}

}
