package com.csc207_0898.triageapp;

/**
 * This is the exception class to handle when an invalid name
 * is used
 *
 */

public class InvalidNameException extends InvalidInputException{

	private static final long serialVersionUID = -1912308773999622044L;
	/**
	 * Provides the error message for generic invalid input.
	 */
	public InvalidNameException() {
		super("Please enter a valid name.");
	}
}
