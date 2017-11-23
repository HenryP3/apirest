package br.com.cddit.apirest.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;

import br.com.cddit.apirest.model.BaseEntity;
import br.com.cddit.apirest.model.common.GenericFilter;
import br.com.cddit.apirest.repository.common.AbstractBaseRepository;
import br.com.cddit.apirest.repository.common.DBCommandTransctionalExecutor;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

public abstract class BaseRepositoryUTest<E extends BaseEntity, F extends GenericFilter, R extends AbstractBaseRepository<E, F>> {

	EntityManagerFactory emf;
	EntityManager em;
	DBCommandTransctionalExecutor executor;

	R repository;

	@BeforeClass
	public static void setUp() {
		FixtureFactoryLoader.loadTemplates("br.com.cddit.apirest.model.fixture");
	}

	@Before
	public void iniTestCase() {
		initializeTestDB();
		repository = getRepositoryInstance();
		repository.setEm(em);
	}

	abstract R getRepositoryInstance();

	@After
	public void closeEntityManager() {
		em.close();
		emf.close();
	}

	protected void initializeTestDB() {
		emf = Persistence.createEntityManagerFactory("cdditPU");
		em = emf.createEntityManager();
		executor = new DBCommandTransctionalExecutor(em);
	}
}
