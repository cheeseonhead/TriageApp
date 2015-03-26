package com.csc207_0898.triageapp;

/**
 * This is the exception class to handle for invalid temperatures.
 * 
 */
public class InvalidTemperatureException extends InvalidInputException {
	private static final long serialVersionUID = 4969512056351492919L;
	/**
	 * Provides the error message for invalid temperatures.
	 */
	public InvalidTemperatureException() {
		super("Temperature (in celsius) must be between 10 and 50 degrees.");
	}
}
