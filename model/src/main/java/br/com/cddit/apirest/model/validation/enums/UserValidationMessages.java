package br.com.cddit.apirest.model.validation.enums;

public class UserValidationMessages {

	public static final String USERNAME_SIZE_MIN_MAX = "{user.username.size.min.max}";
	public static final String PASSWORD_SIZE_MAX = "{user.password.size.max}";

	private UserValidationMessages() {
		throw new IllegalAccessError();
	}
}
