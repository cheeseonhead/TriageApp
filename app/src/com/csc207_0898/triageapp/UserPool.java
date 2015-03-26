package com.csc207_0898.triageapp;

import java.util.TreeMap;

public class UserPool {
	private static TreeMap<String, User> userPool;
	protected static User currentUser;

	/**
	 * Creates a new userPool with no objects attached.
	 */
	public static void init() {
		userPool = new TreeMap<String, User>();
		currentUser = null;
	}

	/**
	 * Creates a new User object from the parameters and adds it to the userPool
	 * TreeMap.
	 * 
	 * @param userName
	 *            userName for the User object.
	 * 
	 * @param password
	 *            password for the User object.
	 * 
	 * @param userType
	 *            userType for the User object.
	 */
	public static void addUser(String userName, String password, String userType) {
		User thisUser = new User(userName, password, userType);
		userPool.put(userName, thisUser);
	}

	/**
	 * Adds user object to userPool TreeMap.
	 * 
	 * @param thisUser
	 *            User object to add.
	 */
	public static void addUser(User thisUser) {
		;
		userPool.put(thisUser.getUserName(), thisUser);
	}

	/**
	 * Searches userPool TreeMap for the which has the matching userName and
	 * password then sets the User variable to this object.
	 * 
	 * @param userName
	 *            This is the key we are looking for.
	 * 
	 * @param password
	 *            This is the password associated with this key.
	 * 
	 * @throws Exception
	 *             if the userName does not exist or the password does not match.
	 */
	public static void setCurrentUser(String userName, String password) throws Exception {
		if (userPool.containsKey(userName)) {
			User thisUser = userPool.get(userName);
			if (thisUser.hasPassword(password)) {
				currentUser = thisUser;
			} else
				throw new InvalidPasswordException();
		} else
			throw new InvalidUserNameException();
	}

	/**
	 * Looks through userPool TreeMap to see if it has a key with this userName.
	 * 
	 * @param userName
	 *            This is the key we will look for.
	 * 
	 * @return 
	 * 		True if userName matches User in Pool, False otherwise.
	 */
	public static boolean hasUser(String userName) {
		return userPool.containsKey(userName);
	}

	/**
	 * Look through the userPool TreeMap to see if it contains this value
	 * 
	 * @param user
	 * 		The target User.
	 * 
	 * @return
	 * 		True if User in TreeMap, False otherwise.
	 */

	public static boolean hasValue(User user) {
		return userPool.containsValue(user);
	}

	/**
	 * Returns the user who is logged in.
	 * 
	 * @return
	 * 		The logged-in User object.
	 */

	public static User getCurrentUser() throws Exception {
		if (currentUser == null) {
			//throw new noUserException();
			//uncomment me!!!
		}
		return currentUser;
	}
	
	/**
	 * Takes in a _valid_ usernames and returns the matching User object.
	 * @param username
	 * 		The username being matched.
	 * @return 
	 * 		The User matching username.
	 */
	public static User getUser(String username) {
		return userPool.get(username);
	}
	

}
