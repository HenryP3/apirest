package br.com.cddit.apirest.service;

import static org.mockito.Mockito.*;

import javax.persistence.EntityNotFoundException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.cddit.apirest.repository.UserRepository;

public class UserServiceUTest {

	@InjectMocks
	UserService service = new UserServiceImpl();

	@Mock
	UserRepository userRepository;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test(expected = EntityNotFoundException.class)
	public void findUserByIdNotFound() {
		when(userRepository.findById(1L)).thenReturn(null);

		service.findById(1L);
	}
}
