package br.com.cddit.apirest.service.common.exception;

public class FieldNotValidException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5837168823041757218L;
	private final String fieldName;

	public FieldNotValidException(final String fieldName, final String message) {
		super(message);
		this.fieldName = fieldName;
	}
}
