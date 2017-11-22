package br.com.cddit.apirest.repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.junit.Test;

import br.com.cddit.apirest.model.User;
import br.com.cddit.apirest.model.validation.enums.UserValidationMessages;
import br.com.cddit.apirest.util.Maps;
import br.com.six2six.fixturefactory.Fixture;

public class UserRepositoryUTest extends BaseRepositoryUTest<User, UserRepositoryImpl> {

	// @Test
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

		try {
			executor.executeCommand(() -> {
				return repository.saveOrUpdate(invalidUser).getId();
			});
			fail("An error should have been thrown");
		} catch (final Exception e) {
			assertThat(e, is(instanceOf(ConstraintViolationException.class)));
			final ConstraintViolationException cve = (ConstraintViolationException) e;
			final Set<ConstraintViolation<?>> constraintViolations = cve.getConstraintViolations();
			assertThat(constraintViolations.size(), is(equalTo(constraintViolationsExpecteds.size())));
			constraintViolations.forEach(cv -> {
				final String prop = cv.getPropertyPath().toString();
				assertThat(constraintViolationsExpecteds, hasKey(prop));

				assertThat(cv.getConstraintDescriptor().getMessageTemplate(),
						is(equalTo(constraintViolationsExpecteds.get(prop))));
			});
		}
	}

	@Override
	UserRepositoryImpl getRepositoryInstance() {
		return new UserRepositoryImpl();
	}

}
