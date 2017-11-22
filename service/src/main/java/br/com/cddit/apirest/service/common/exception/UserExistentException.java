package br.com.cddit.apirest.service.common.exception;

public class UserExistentException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5837168823041757218L;

	public UserExistentException(final String message) {
		super(message);
	}
}