package br.com.cddit.apirest.repository;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.NoResultException;

import br.com.cddit.apirest.model.User;
import br.com.cddit.apirest.model.common.PageableData;
import br.com.cddit.apirest.model.filter.UserFilter;
import br.com.cddit.apirest.repository.common.AbstractBaseRepository;

public class UserRepositoryImpl extends AbstractBaseRepository<User> implements UserRepository {

	@Override
	protected Class<User> getDomainClass() {
		return User.class;
	}

	@Override
	public boolean alreadyExistsByUsername(final User user) {
		return alreadyExists("username", user.getUsername(), user.getId());
	}

	@Override
	public boolean alreadyExistsByUsername(final String username) {
		return alreadyExists("username", username, null);
	}

	@Override
	public User findByUsername(final String username) {
		try {
			return (User) getEm().createQuery("Select e From User e where Upper(e.username) Like Upper(:username)")
					.setParameter("username", String.format("%%%s%%", username))
					.getSingleResult();
		} catch (final NoResultException e) {
			return null;
		}
	}

	public PageableData<User> findByFilter(final UserFilter userFilter) {
		final StringBuilder clause = new StringBuilder("WHERE e.id is not null");
		final Map<String, Object> queryParameters = new HashMap<>();
		if (userFilter.getUsername() != null) {
			clause.append(" And Upper(e.name) Like Upper(:name)");
			queryParameters.put("name", String.format("%%%s%%", userFilter.getUsername()));
		}
		return findByParameters(clause.toString(), userFilter.getPageData(), queryParameters, "name ASC");
	}
}