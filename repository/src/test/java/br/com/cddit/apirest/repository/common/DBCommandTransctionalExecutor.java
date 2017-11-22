package br.com.cddit.apirest.repository.common;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.junit.Ignore;

@Ignore
public class DBCommandTransctionalExecutor {

	EntityManager em;

	public DBCommandTransctionalExecutor(final EntityManager em) {
		super();
		this.em = em;
	}

	public <T> T executeCommand(final DBCommand<T> dbCommand) {

		try {
			em.getTransaction().begin();
			final T toReturn = dbCommand.execute();
			em.getTransaction().commit();
			em.clear();
			return toReturn;
		} catch (final Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			throw e;
		}
	}

	public <T> void executeCommandAndCheckViolations(final DBCommand<T> dbCommand,
			final Map<String, String> constraintViolationsExpecteds) {

		try {
			em.getTransaction().begin();
			dbCommand.execute();
			em.getTransaction().commit();
			em.clear();
			fail("An error should have been thrown");
		} catch (final Exception e) {
			em.getTransaction().rollback();
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

}
