package br.com.cddit.apirest.api.resource;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import api.br.com.cddit.apirest.api.resource.UserResource;
import br.com.cddit.apirest.service.UserService;

public class UserResourceUTest {

	@InjectMocks
	UserResource userResource;

	@Mock
	UserService userServices;
}
