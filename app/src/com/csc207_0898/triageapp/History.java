package com.csc207_0898.triageapp;

import java.util.ArrayList;

/**
 * A class that can store an history of one type of object. 
 *
 * @param <T> The type of the objects this history keeps track of.
 */
public abstract class History<T> extends ArrayList<T> {
	private static final long serialVersionUID = 1820854769325178663L;

	/**
	 * Creates a History instance.
	 */
	public History() {
		super();
	}

	/**
	 * Gets all the T in this History.
	 * 
	 * @return An array of T, starting with oldest.
	 */
	public abstract T[] getAll();



}
