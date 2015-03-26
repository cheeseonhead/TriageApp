package com.csc207_0898.triageapp;

public class InvalidPasswordException extends InvalidInputException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5655819080789666503L;
	/**
	 * Provides the error message for invalid passwords.
	 */
	public InvalidPasswordException() {
		super("Wrong password!");
	}
}
