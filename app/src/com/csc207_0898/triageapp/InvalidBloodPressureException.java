package com.csc207_0898.triageapp;

/**
 * This is the exception class to handle when invalid blood pressures 
 * are used.
 *
 */

public class InvalidBloodPressureException extends Exception {
	private static final long serialVersionUID = 5555535846352287485L;
	/**
	 * Provides the appropriate error message for invalid blood pressure.
	 */
	public InvalidBloodPressureException() {
		super("Diastolic/systolic blood pressure must be between 0 and 250.");
	}
}
