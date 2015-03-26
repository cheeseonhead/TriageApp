package com.csc207_0898.triageapp;


/**
 * A class that stores the blood pressure both systolic and diastolic. 
 *
 */
public class BloodPressure {
	private int diastolic;
	private int systolic;
	
	/**
	 * Creates an instance of BloodPressure with diastolic and systolic ints.
	 * 
	 * @param diastolic
	 * 		An int representation of diastolic blood pressure level.
	 * 
	 * @param systolic
	 * 		An int representation of systolic blood pressure level.
	 */
	public BloodPressure(int diastolic, int systolic) throws Exception {
		setDiastolic(diastolic);
		setSystolic(systolic);
	}
	
	/**
	 * Validator for blood pressure - makes sure either diastolic/systolic is between 0 and 250.
	 * 
	 * @return true if bloodPressure value is valid.
	 * 
	 * @throws InvalidBloodPressureException if value is invalid.
	 */
	public boolean validateBloodPressure(int bloodPressure) throws Exception {
		if (0 < bloodPressure && bloodPressure <= 250) {
			return true;
		}
		else {
			throw new InvalidBloodPressureException();
		}
	}
	
	/**
	 * Set the diastolic blood pressure.
	 * 
	 * @param newDiastolic
	 * 		An int representation of diastolic blood pressure to be set.
	 */
	public void setDiastolic(int newDiastolic) throws Exception {
		if (validateBloodPressure(newDiastolic)) {
			diastolic = newDiastolic;
		}
	}
	
	/**
	 * Set the systolic blood pressure.
	 * 
	 * @param newSystolic
	 * 		An int representation of systolic blood pressure to be set.
	 */
	public void setSystolic(int newSystolic) throws Exception {
		if (validateBloodPressure(newSystolic)) {
			systolic = newSystolic;
		}
	}
	
	/**
	 * Return int representation of diastolic blood pressure.
	 * 
	 * @return diastolic.
	 */
	public int getDiastolic() {
		return diastolic;
	}
	
	/**
	 * Get int representation of systolic blood pressure.
	 * 
	 * @return systolic.
	 */
	public int getSystolic() {
		return systolic;
	}
	
	/**
	 * Formats a BloodPressure tuple as "40/70", for example.
	 * 
	 * @return string representation of BloodPressure.
	 */
	public String toString(){
		return Integer.toString(getDiastolic()) + "/" + Integer.toString(getSystolic()) + " mmHg";
	}
}
