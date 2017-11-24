package br.com.cddit.integrationtest.utils;

//@Path("/DB")
public class DBResource {

	// @Inject
	private TestRepositoryEJB testRepositoryEJB;

	// @DELETE
	public void deleteAll() {
		testRepositoryEJB.deleteAll();
		// EntityManager em = entityManagerFactory.createEntityManager();
		// em.getTransaction().begin();
		// em.createNativeQuery("truncate table person").executeUpdate();
		// em.createNativeQuery("truncate table preferences").executeUpdate();
		// em.getTransaction().commit();
	}

}