package br.com.cddit.apirest.service;

import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import br.com.cddit.apirest.model.User;
import br.com.cddit.apirest.repository.UserRepositoryImpl;

public class UserServiceImpl implements UserService {

	private Validator validator;
	private UserRepositoryImpl repo;

	public User saveOrUpdate(final User u) {
		final Set<ConstraintViolation<User>> errors = validator.validate(u);
		final Iterator<ConstraintViolation<User>> itErrors = errors.iterator();

		if (itErrors.hasNext()) {
			final ConstraintViolation<User> violation = itErrors.next();
			// throw new FieldNotValidException(violation.getPropertyPath().toString(),
			// violation.getMessage());
		}

		// if (repo.alreadyExists(u)) {
		// throw new UserExistentException("este usuario ja existe, e devmos aplicar
		// I18N");
		// }

		return repo.saveOrUpdate(u);
	}

}
