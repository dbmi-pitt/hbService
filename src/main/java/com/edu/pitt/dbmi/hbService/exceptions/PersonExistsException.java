package com.edu.pitt.dbmi.hbService.exceptions;

public class PersonExistsException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7952791399189145027L;

	public PersonExistsException(String message) {
		super(message);
	}
}
