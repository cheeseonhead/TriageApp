package com.csc207_0898.triageapp;

import java.util.regex.Pattern;

/**
 * A class that stores the name. 
 *
 */
public class Name {
	private String name;

	/**
	 * Creates a Name instance with given name.
	 * 
	 * @param name The name to declare with, it can only contain English letters,
	 * hyphen, and spaces.
	 * 
	 * @throws Exception
	 * 		When the name is invalid.
	 */
	public Name(String name) throws Exception {
		set(name);
	}

	/**
	 * Sets the name to the given newName.
	 * 
	 * @param newName
	 *            The new name to set to.
	 * @throws Exception
	 *             When the newName doesn't fit the format.
	 */
	public void set(String newName) throws Exception {
		newName = cleanName(newName);
		validateName(newName);
		this.name = newName;
	}

	/**
	 * Gets the name of this Name object.
	 * 
	 * @return The name as a string.
	 */
	public String get() {
		return name;
	}

	/**
	 * Validates if the given string is in name format.
	 * 
	 * @param newName
	 *            The new name to set to
	 * @throws Exception
	 *             When the name contains invalid characters.
	 */
	private void validateName(String newName) throws Exception {
		if (Pattern.matches("[A-Za-z-\\s']+", newName) == false) {
			throw new InvalidInputException();
		}
	}
	
	/**
	 * Removes duplicate whitespaces, heading or trailing whitespaces.
	 * @param newName The name to be cleaned.
	 * @return newName The cleaned version of given name.
	 */
	private String cleanName(String newName) {
		newName = newName.trim();
		newName = newName.replaceAll("\\s+", " ");
		return newName;
	}

	
	@Override
	public String toString(){
		return this.name;
	}
}
