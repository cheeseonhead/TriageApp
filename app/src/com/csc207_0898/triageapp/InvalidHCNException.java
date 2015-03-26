package com.csc207_0898.triageapp;

/**
 * This is the exception class to handle when an invalid HCN 
 * is used.
 *
 */

public class InvalidHCNException extends InvalidInputException {
	private static final long serialVersionUID = -1104874286933892365L;
	/**
	 * Provides the error message for invalid healthcard number.
	 */
	public InvalidHCNException() {
		super("Healthcard numbers must be exactly 6 characters.");
	}
}
