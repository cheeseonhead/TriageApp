package com.csc207_0898.triageapp;

import java.util.Calendar;

/**
 * A class that stores the information of a single patient.
 * 
 */
public class Patient implements Comparable<Object> {
	public HealthCardNumber healthCN;
	public Name firstname;
	public Name surname;
	public Birthdate birthdate;
	public VisitHistory visitHistory = new VisitHistory();
	private short urgency;

	/**
	 * Creates a patient instance using given information.
	 * 
	 * @param HCN
	 *            Health Card Number, a string of only numbers.
	 * @param firstname
	 *            A string of characters including English characters, hyphen,
	 *            and spaces.
	 * @param surname
	 *            A string, same as firstname.
	 * @param bString
	 *            A string of 8 numbers representing a date in this format:
	 *            YYYYMMDD
	 * @throws Exception
	 *             When a given info is not valid.
	 */
	public Patient(int HCN, String firstname, String surname, String bString)
			throws Exception {
		this.healthCN = new HealthCardNumber(HCN);
		this.firstname = new Name(firstname);
		this.surname = new Name(surname);
		this.birthdate = new Birthdate(bString);
		this.urgency = -1;
	}

	/**
	 * Gets the age this patient.
	 * 
	 * @return The age of this patient.
	 */
	public int getAge() {
		Calendar curDate = Calendar.getInstance();
		int yearDiff = curDate.get(Calendar.YEAR) - birthdate.getYear();
		int monthDiff = curDate.get(Calendar.MONTH) + 1 - birthdate.getMonth();
		int dayDiff = curDate.get(Calendar.DATE) - birthdate.getDay();
		if (monthDiff < 0) {
			yearDiff--;
		} else if (monthDiff == 0 && dayDiff < 0) {
			yearDiff--;
		}
		return yearDiff;
	}

	/**
	 * Get the prescription for this patient.
	 * 
	 * @return The prescription associated with the latest visit.
	 */
	public String getPrescription() {
		try {
			return getLatestVisit().prescription;
		} catch (Exception e) {
			return "None";
		}
	}

	
	/**
	 * Set the prescription for the Patient's latest visit.
	 * 
	 * @param prescriptStr
	 * 		The prescription string given by the physician.
	 * @throws Exception
	 * 		When the patient has no visits yet (should never be thrown).
	 */
	public void setPrescription(String prescriptStr) throws Exception {
		getLatestVisit().prescription = prescriptStr;
	}

	/**
	 * Manually set the urgency rating for the patient.
	 * 
	 * @param urgency
	 * 		The urgency as a short.
	 */
	public void updateUrgency(short urgency) {
		this.urgency = urgency;
	}

	/**
	 * Get the urgency as a short.
	 * @return
	 * 		Patient.urgency as a short.
	 */
	public short getUrgency() {
		return urgency;
	}

	/**
	 * Get the latest visit if it exists.
	 * 
	 * @return
	 * 		The Patient's newest visit.
	 * @throws Exception
	 * 		If the Patient has no visits.
	 */
	public Visit getLatestVisit() throws Exception {
		return visitHistory.getAllByNewest()[0];
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Patient))
			return false;
		return ((Patient) obj).urgency == this.urgency;
	}

	@Override
	public int compareTo(Object obj) {
		if (((Patient) obj).urgency == this.urgency)
			return 0;
		else if (((Patient) obj).urgency > this.urgency)
			return -1;
		else
			return 1;
	}

	@Override
	public String toString() {
		return (surname.get() + ", " + firstname.get() + " - " + Integer
				.toString(healthCN.get()) + ", " + "Urgency: " + Integer.toString(urgency));
	}
}
