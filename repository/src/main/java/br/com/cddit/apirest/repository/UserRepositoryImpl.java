package br.com.cddit.apirest.repository;

import br.com.cddit.apirest.model.User;
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

}