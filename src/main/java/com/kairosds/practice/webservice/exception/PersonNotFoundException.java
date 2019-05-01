package com.kairosds.practice.webservice.exception;


/**
 * 
 * This class is used to be received by the PersonExceptionHandler class
 * 
 * @author jsvruiz
 * 
 */

public class PersonNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PersonNotFoundException(int id) {
		super("Person id not Found: " + id);
	}

}
