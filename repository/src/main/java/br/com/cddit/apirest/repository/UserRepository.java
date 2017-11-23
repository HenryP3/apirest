package br.com.cddit.apirest.repository;

import br.com.cddit.apirest.model.User;
import br.com.cddit.apirest.model.common.PageableData;
import br.com.cddit.apirest.model.filter.UserFilter;
import br.com.cddit.apirest.repository.common.BaseRepository;

public interface UserRepository extends BaseRepository<User> {

	public boolean alreadyExistsByUsername(final User user);

	public boolean alreadyExistsByUsername(final String username);

	public User findByUsername(final String username);

	public PageableData<User> findByFilter(final UserFilter userFilter);
}
