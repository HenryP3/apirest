package br.com.cddit.apirest.repository;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import br.com.cddit.apirest.model.Customer;
import br.com.six2six.fixturefactory.Fixture;

public class CustomerRepositoryUTest extends BaseRepositoryUTest<Customer, CustomerRepositoryImpl> {

	CustomerRepositoryImpl repo;

	// @Test
	public void addCategoryAndFindIt() {
		final Customer c = Fixture.from(Customer.class).gimme("validCustomer");
		System.out.println(c);
		repo = new CustomerRepositoryImpl();
		final Long customerAddedId = repo.saveOrUpdate(c).getId();
		assertThat(customerAddedId, is(notNullValue()));
		// c.get
		// 9 9109 9564
	}

	@Override
	CustomerRepositoryImpl getRepositoryInstance() {
		return new CustomerRepositoryImpl();
	}
}
