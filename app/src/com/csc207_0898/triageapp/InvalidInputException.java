package com.csc207_0898.triageapp;

public class InvalidInputException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Inherited constructor for when no error message is passed.
	 */
	public InvalidInputException() {
		super();
	}
	/**
	 * Inherited constructor with an error message.
	 * 
	 * @param message
	 * 		The given error message.
	 */
	public InvalidInputException(String message) {
		super(message);
	}
}
