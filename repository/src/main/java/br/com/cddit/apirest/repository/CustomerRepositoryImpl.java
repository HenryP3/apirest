package br.com.cddit.apirest.repository;

import javax.inject.Named;

import br.com.cddit.apirest.model.Customer;
import br.com.cddit.apirest.model.filter.CustomerFilter;
import br.com.cddit.apirest.repository.common.AbstractBaseRepository;

@Named
public class CustomerRepositoryImpl extends AbstractBaseRepository<Customer, CustomerFilter>
		implements CustomerRepository {

	@Override
	public Class<Customer> getDomainClass() {
		return Customer.class;
	}
}
