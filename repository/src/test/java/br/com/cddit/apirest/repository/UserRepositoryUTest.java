package br.com.cddit.apirest.repository;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import br.com.cddit.apirest.model.User;
import br.com.cddit.apirest.model.common.PageData;
import br.com.cddit.apirest.model.common.PageData.OrderMode;
import br.com.cddit.apirest.model.common.PageableData;
import br.com.cddit.apirest.model.filter.UserFilter;
import br.com.cddit.apirest.model.validation.enums.UserValidationMessages;
import br.com.cddit.apirest.util.Maps;
import br.com.six2six.fixturefactory.Fixture;

public class UserRepositoryUTest extends BaseRepositoryUTest<User, UserFilter, UserRepositoryImpl> {

	@Test
	public void addUserAndFindIt() {

		final User entity = Fixture.from(User.class).gimme("validUser");

		final Long customerAddedId = executor.executeCommand(() -> {
			return repository.saveOrUpdate(entity).getId();
		});

		assertThat(customerAddedId, is(notNullValue()));

		final User u = repository.findById(customerAddedId);
		assertThat(u, is(notNullValue()));
		assertThat(u.getUsername(), is(equalTo(entity.getUsername())));
	}

	@Test
	public void alreadyExistsForAdd() {

		final User entity = Fixture.from(User.class).gimme("validUser");

		final User userAdded = executor.executeCommand(() -> {
			return repository.saveOrUpdate(entity);
		});

		assertThat(userAdded.getId(), is(notNullValue()));
		assertThat(repository.alreadyExistsByUsername(userAdded), is(equalTo(true)));
		assertThat(repository.alreadyExistsByUsername(userAdded.getUsername()), is(equalTo(true)));
	}

	// @Test
	public void shouldthrowValidationErrors() {
		final User invalidUser = Fixture.from(User.class).gimme("invalidUserFieldUsernameHasNoMinLength");

		final Map<String, String> constraintViolationsExpecteds = Maps.<String, String>builder()
				.put("username", UserValidationMessages.USERNAME_SIZE_MIN_MAX)
				.put("password", UserValidationMessages.PASSWORD_SIZE_MAX)

				.build();

		executor.executeCommandAndCheckViolations(() -> {
			return repository.saveOrUpdate(invalidUser).getId();
		}, constraintViolationsExpecteds);

	}

	@Test(expected = NullPointerException.class)
	public void findCategoryByIdNotFound() {
		repository.findById(null);
	}

	@Test
	public void findAllUsers() {
		final List<User> users = allUsers();
		executor.executeCommand(() -> {
			users.forEach(repository::saveOrUpdate);
			return null;
		});

		final List<User> usersFromDB = repository.findAll("username");
		assertThat(users.size(), is(equalTo(usersFromDB.size())));
	}

	@Test
	public void findByFilterWithPagingOrderingByNameDescending() {
		loadDataForFindByFilter();

		UserFilter userFilter = new UserFilter();
		userFilter.setPageData(new PageData(0, 2, "username", OrderMode.DESCENDING));

		PageableData<User> result = repository.findByFilter(userFilter);
		assertThat(result.getNumberOfRows(), is(equalTo(3)));
		assertThat(result.getRows().size(), is(equalTo(2)));
		// assertThat(result.getRow(0).getUsername(), is(equalTo(mary().getName())));
		// assertThat(result.getRow(1).getUsername(), is(equalTo(johnDoe().getName())));

		userFilter = new UserFilter();
		userFilter.setPageData(new PageData(2, 2, "username", OrderMode.DESCENDING));

		result = repository.findByFilter(userFilter);
		assertThat(result.getNumberOfRows(), is(equalTo(3)));
		assertThat(result.getRows().size(), is(equalTo(1)));
		// assertThat(result.getRow(0).getUsername(), is(equalTo(admin().getName())));
	}

	// @Test
	public void findByFilterFilteringByName() {
		loadDataForFindByFilter();

		final UserFilter userFilter = new UserFilter();
		userFilter.setUsername("m");
		userFilter.setPageData(new PageData(0, 2, "username", OrderMode.ASCENDING));

		final PageableData<User> result = repository.findByFilter(userFilter);
		assertThat(result.getNumberOfRows(), is(equalTo(2)));
		assertThat(result.getRows().size(), is(equalTo(2)));
		// assertThat(result.getRow(0).getUsername(), is(equalTo(admin().getName())));
		// assertThat(result.getRow(1).getUsername(), is(equalTo(mary().getName())));
	}

	// @Testt
	public void findByFilterFilteringByNameAndType() {
		loadDataForFindByFilter();

		final UserFilter userFilter = new UserFilter();
		userFilter.setUsername("m");
		userFilter.setPageData(new PageData(0, 2, "username", OrderMode.ASCENDING));

		final PageableData<User> result = repository.findByFilter(userFilter);
		assertThat(result.getNumberOfRows(), is(equalTo(1)));
		assertThat(result.getRows().size(), is(equalTo(1)));
		// assertThat(result.getRow(0).getUsername(), is(equalTo(admin().getName())));
	}

	private List<User> allUsers() {
		return Fixture.from(User.class).gimme(3, "validUser");
	}

	private void loadDataForFindByFilter() {
		executor.executeCommand(() -> {
			allUsers().forEach(repository::saveOrUpdate);
			return null;
		});
	}

	@Override
	UserRepositoryImpl getRepositoryInstance() {
		return new UserRepositoryImpl();
	}

}
