package br.com.cddit.apirest.service;

import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.Before;

public class UserServiceTest {

	UserService service;
	Validator validator;

	@Before
	public void init() {
		validator = Validation.buildDefaultValidatorFactory().getValidator();
		service = new UserServiceImpl();
	}
}
