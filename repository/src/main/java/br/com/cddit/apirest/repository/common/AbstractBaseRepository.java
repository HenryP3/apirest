package br.com.cddit.apirest.repository.common;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.cddit.apirest.model.BaseEntity;
import br.com.cddit.apirest.model.common.PageableData;
import lombok.Setter;

@Setter
public abstract class AbstractBaseRepository<T extends BaseEntity> implements BaseRepository<T> {

	@PersistenceContext
	EntityManager em;

	protected abstract Class<T> getDomainClass();

	@Override
	public T saveOrUpdate(final T entity) {
		if (entity.getId() == null) {
			em.persist(entity);
			return entity;
		}
		return em.merge(entity);
	}

	@Override
	public T findById(final Long id) {
		Objects.requireNonNull(id, "The id must not be null!");
		return em.find(getDomainClass(), id);
	}

	@Override
	public void delete(final T entity) {
		Objects.requireNonNull(entity, "The entity must not be null!");
		em.remove(em.contains(entity) ? entity : em.merge(entity));
	}

	@Override
	public void deleteAll(final Iterable<? extends T> entities) {

		Objects.requireNonNull(entities, "The given Iterable of entities not be null!");

		for (final T entity : entities) {
			delete(entity);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> findAll(final String orderField) {
		return em.createQuery(
				"Select e From " + getDomainClass().getSimpleName() + " e Order by e." + orderField)
				.getResultList();
	}

	@Override
	public boolean alreadyExists(final String propertyName, final String propertyValue, final Long id) {
		final StringBuilder jpql = new StringBuilder();
		jpql.append("Select 1 From " + getDomainClass().getSimpleName() + " e where e." + propertyName
				+ " = :propertyValue");
		if (id != null) {
			jpql.append(" and e.id != :id");
		}

		final Query query = em.createQuery(jpql.toString());
		query.setParameter("propertyValue", propertyValue);
		if (id != null) {
			query.setParameter("id", id);
		}

		return query.setMaxResults(1).getResultList().size() > 0;
	}

	@Override
	public boolean existsById(final Long id) {
		return em.createQuery("Select 1 From " + getDomainClass().getSimpleName() + " e where e.id = :id")
				.setParameter("id", id)
				.setMaxResults(1)
				.getResultList().size() > 0;
	}

	@Override
	@SuppressWarnings("unchecked")
	public PageableData<T> findByParameters(final String clause, final PageData paginationData,
			final Map<String, Object> queryParameters, final String defaultSortFieldWithDirection) {
		final String clauseSort = "Order by e." + getSortField(paginationData, defaultSortFieldWithDirection);
		final Query queryEntities = em.createQuery(
				"Select e From " + getDomainClass().getSimpleName()
						+ " e " + clause + " " + clauseSort);
		applyQueryParametersOnQuery(queryParameters, queryEntities);
		applyPaginationOnQuery(paginationData, queryEntities);

		final List<T> entities = queryEntities.getResultList();

		return new PageableData<T>(countWithFilter(clause, queryParameters), entities);
	}

	private int countWithFilter(final String clause, final Map<String, Object> queryParameters) {
		final Query queryCount = em.createQuery(
				"Select count(e) From " + getDomainClass().getSimpleName() + " e " + clause);
		applyQueryParametersOnQuery(queryParameters, queryCount);
		return ((Long) queryCount.getSingleResult()).intValue();
	}

	private void applyPaginationOnQuery(final PageData paginationData, final Query query) {
		if (paginationData != null) {
			query.setFirstResult(paginationData.getFirstResult());
			query.setMaxResults(paginationData.getMaxResults());
		}
	}

	private String getSortField(final PageData paginationData, final String defaultSortField) {
		if (paginationData == null || paginationData.getOrderField() == null) {
			return defaultSortField;
		}
		return paginationData.getOrderField() + " " + getSortDirection(paginationData);
	}

	private String getSortDirection(final PageData paginationData) {
		return paginationData.isAscending() ? "ASC" : "DESC";
	}

	private void applyQueryParametersOnQuery(final Map<String, Object> queryParameters, final Query query) {
		for (final Entry<String, Object> entryMap : queryParameters.entrySet()) {
			query.setParameter(entryMap.getKey(), entryMap.getValue());
		}
	}
}
