package br.com.cddit.apirest.service;

import java.util.Objects;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.cddit.apirest.model.User;
import br.com.cddit.apirest.model.common.PageableData;
import br.com.cddit.apirest.model.filter.UserFilter;
import br.com.cddit.apirest.repository.UserRepository;

@Stateless
public class UserServiceImpl extends AbstractCrudService<User, UserFilter, UserRepository> implements UserService {

	@Inject
	private UserRepository repo;

	@Override
	public PageableData<User> findByFilter(final UserFilter userFilter) {
		Objects.requireNonNull(userFilter, "Userfilter cannot be null");
		return repo.findByFilter(userFilter);
	}

	@Override
	public UserRepository getRepo() {
		return repo;
	}

	@Override
	public boolean alreadyExistsByUsername(final User user) {
		Objects.requireNonNull(user, "User cannot be null");
		return repo.alreadyExistsByUsername(user);
	}

	@Override
	public boolean alreadyExistsByUsername(final String username) {
		validateUserNotNull(username);
		return repo.alreadyExistsByUsername(username);
	}

	private void validateUserNotNull(final String username) {
		Objects.requireNonNull(username, "Username cannot be null");
	}

	@Override
	public User findByUsername(final String username) {
		validateUserNotNull(username);
		return repo.findByUsername(username);
	}

}
