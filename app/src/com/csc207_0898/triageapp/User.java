package com.csc207_0898.triageapp;

public class User {
	private String userName;
	private String password;
	private String userType;

	/**
	 * Creates a user with these parameters.
	 * 
	 * @param userName
	 *            The username taken as a String.
	 * 
	 * @param password
	 *            The associated password taken as a String.
	 * 
	 * @param userType
	 *            The associated userType taken as a String.
	 */

	public User(String userName, String password, String userType) {
		this.userName = userName;
		this.password = password;
		this.userType = userType;
	}

	/**
	 * Gets the UserType variable.
	 * 
	 * @return userType as a String.
	 */

	public String getUserType() {
		return userType;
	}
	
	/**
	 * Gets the userName.
	 * 
	 * @return userName as a String.
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Gets the password.
	 * 
	 * @return password as a String.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Checks that the username and password given as parameters match those
	 * already associated with the User.
	 * 
	 * @param userName
	 * 		Local variable to compare with this.userName.
	 * 
	 * @param password
	 * 		Local variable to compare with this.password.
	 * 
	 * @return 
	 * 		True if the given password matches the User's password, false otherwise.
	 * 
	 * @exception Raises
	 * 		If User doesn't exist..
	 */
	public boolean hasPassword(String password) {
		return (this.password.equals(password));
	}

}
