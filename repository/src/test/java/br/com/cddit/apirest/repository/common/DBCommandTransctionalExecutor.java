package br.com.cddit.apirest.repository.common;

import javax.persistence.EntityManager;

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
}
