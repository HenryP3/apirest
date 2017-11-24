package br.com.cddit.integrationtest.utils;

import br.com.cddit.apirest.service.UserService;

//@Path("/DB/users")
public class UserDBResource {

	// @Inject
	UserService userService;

	// @POST
	public void addDataSet() {
		// aki cria a massa de dados
		// userService.saveOrUpdate(user)
	}
}
