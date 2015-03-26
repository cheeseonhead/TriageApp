package com.csc207_0898.triageapp;

public class VitalHistory extends History<Vital> {
	private static final long serialVersionUID = -205982826461270910L;

	/**
	 * Creates a new instance of this VitalHistory.
	 */
	public VitalHistory() {
		super();
	}

	/**
	 * Gets all the Vital of this History, newest first.
	 * 
	 * @return An array containing all the Vital, oldest first.
	 */
	public Vital[] getAll() {
		return this.toArray(new Vital[size()]);
	}

	/**
	 * Gets all the Vital of this History, newest first.
	 * 
	 * @return An array containing all the Vital, newest first.
	 */
	public Vital[] getAllByNewest() throws Exception {
		if (this.size() == 0) {
			throw new Exception("There are no vitals yet!");
		}
		Vital[] resp = new Vital[size()];
		for (int i = size() - 1; i >= 0; i--) {
			resp[size() - i - 1] = get(i);
		}
		return resp;
	}
	
	/**
	 * Get the newest Vital if it exists.
	 * @return
	 * 		The newest Vital object.
	 * @throws Exception
	 * 		When there are no Vitals yet.
	 */
	public Vital getNewest() throws Exception {
		try {
			return getAllByNewest()[0];
		}
		catch (Exception e) {
			throw new Exception("You have to fill out all the fields since this is the "
					+ "first vital report for this visit.");
		}
	}
}
