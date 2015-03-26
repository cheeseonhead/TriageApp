package com.csc207_0898.triageapp;

/**
 * This is the exception class to handle when invalid heart rates 
 * are used.
 */
public class InvalidHeartRateException extends InvalidInputException {
	private static final long serialVersionUID = -6225482947269560787L;
	/**
	 * Provides the error message for invalid heartrates.
	 */
	public InvalidHeartRateException() {
		super("Heart rate must be between 40 and 250.");
	}
}
