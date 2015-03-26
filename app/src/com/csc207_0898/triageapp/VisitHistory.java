package com.csc207_0898.triageapp;

/**
 * A class which stores a list of visits.
 * 
 */
public class VisitHistory extends History<Visit> {
	private static final long serialVersionUID = -205982826461270910L;

	/**
	 * Creates a new instance of this VisitHistory
	 */
	public VisitHistory() {
		super();
	}

	/**
	 * Gets all the Visit of this History.
	 * 
	 * @return An array containing all the Visit, with oldest first.
	 */
	public Visit[] getAll() {
		return this.toArray(new Visit[size()]);
	}

	/**
	 * Gets all the Visit of this History.
	 * 
	 * @return An array containing all the Visit, with newest first.
	 */
	public Visit[] getAllByNewest() {
		Visit[] resp = new Visit[size()];
		for (int i = size() - 1; i >= 0; i--) {
			resp[size() - i - 1] = get(i);
		}
		return resp;
	}

	
	/**
	 * Preserve the prescription from the previous Visit for the newest Visit.
	 */
	public void updatePrescription() {
		Visit[] visits = this.getAllByNewest();
		String oldPrescription = visits[1].prescription;
		visits[0].prescription = oldPrescription;

	}

}
