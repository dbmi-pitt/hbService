package com.edu.pitt.dbmi.hbService.exceptions;

public class DataNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7326679771867980557L;
	
	public DataNotFoundException(String message) {
		super(message);
	}
}
