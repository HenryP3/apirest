package br.com.cddit.apirest.repository;

import br.com.cddit.apirest.model.User;

public interface UserRepository {

	public boolean alreadyExistsByUsername(final User user);

	public boolean alreadyExistsByUsername(final String username);
}
