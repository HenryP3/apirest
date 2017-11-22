package br.com.cddit.apirest.repository.common;

import java.util.List;
import java.util.Map;

import br.com.cddit.apirest.model.BaseEntity;
import br.com.cddit.apirest.model.common.PageableData;

public interface BaseRepository<T extends BaseEntity> {

	public T saveOrUpdate(final T entity);

	public T findById(final Long id);

	public void delete(final T entity);

	public void deleteAll(final Iterable<? extends T> entities);

	public List<T> findAll(final String orderField);

	public boolean alreadyExists(final String propertyName, final String propertyValue, final Long id);

	public boolean existsById(final Long id);

	public PageableData<T> findByParameters(final String clause, final PageData paginationData,
			final Map<String, Object> queryParameters, final String defaultSortFieldWithDirection);
}
