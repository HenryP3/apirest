package br.com.cddit.apirest.repository;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

import br.com.cddit.apirest.model.User;
import br.com.cddit.apirest.model.validation.enums.UserValidationMessages;
import br.com.cddit.apirest.util.Maps;
import br.com.six2six.fixturefactory.Fixture;

public class UserRepositoryUTest extends BaseRepositoryUTest<User, UserRepositoryImpl> {

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

	@Override
	UserRepositoryImpl getRepositoryInstance() {
		return new UserRepositoryImpl();
	}

}
