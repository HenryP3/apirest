package br.com.cddit.apirest.model.fixture;

import br.com.cddit.apirest.model.Customer;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class CustomerFixture implements TemplateLoader {

	public void load() {
		Fixture.of(Customer.class).addTemplate("validCustomer", new Rule() {
			{
				add("version", 0L);
				add("username", random("user1", "user2"));
				add("password", "encryptar");

				add("name", random("Anderson Parra", "Arthur Hirata"));
				add("email", "${username}@gmail.com");
				add("phone", regex("(\\d{2})-(\\d{4})-(\\d{4})"));
				add("department", random("hr", "it"));
				add("jobRole", random("manager", "helpdesk"));
			}
		});
	}
}
