package br.com.cddit.apirest.repository;

import br.com.cddit.apirest.model.Customer;
import br.com.cddit.apirest.repository.common.AbstractBaseRepository;

public class CustomerRepositoryImpl extends AbstractBaseRepository<Customer> implements CustomerRepository {

	@Override
	protected Class<Customer> getDomainClass() {
		return Customer.class;
	}

}
