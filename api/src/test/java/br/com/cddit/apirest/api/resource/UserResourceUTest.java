package br.com.cddit.apirest.api.resource;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import br.com.cddit.apirest.api.resource.HelloResource;
import br.com.cddit.apirest.service.UserService;

public class UserResourceUTest {

	@InjectMocks
	HelloResource userResource;

	@Mock
	UserService userServices;
}
