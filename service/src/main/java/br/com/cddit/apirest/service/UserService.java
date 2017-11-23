package br.com.cddit.apirest.service;

import br.com.cddit.apirest.model.User;
import br.com.cddit.apirest.model.filter.UserFilter;

public interface UserService extends CrudService<User, UserFilter> {

	public boolean alreadyExistsByUsername(final User user);

	public boolean alreadyExistsByUsername(final String username);

	public User findByUsername(final String username);

}
