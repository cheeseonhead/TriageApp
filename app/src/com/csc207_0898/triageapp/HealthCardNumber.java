package com.csc207_0898.triageapp;

/**
 * A class that stores the health card number. 
 *
 */
public class HealthCardNumber {
	private int HCN;

	/**
	 * Creates an instance of HealthCardNumber object, given a HCN value.
	 * 
	 * @param HCN
	 *            The initial HCN
	 * @throws Exception
	 *             When given HCN contains not only numbers.
	 */
	public HealthCardNumber(String HCN) throws InvalidHCNException {
		set(Integer.parseInt(HCN));
	}

	/**
	 * Creates an instance of HealthCardNumber object, given a integer as HCN.
	 * 
	 * @param HCN
	 *            The initial HCN as an int.
	 *            
	 * @throws Exception
	 *             When given HCN is not valid.
	 */
	public HealthCardNumber(int HCN) throws Exception {
		set(HCN);
	}

	/**
	 * Sets the HCN to a new HCN.
	 * 
	 * @param newHCN
	 *            The new HCN being set.
	 *            
	 * @throws Exception
	 *             When the new HCN doesn't only contain numbers.
	 */
	public void set(int newHCN) throws InvalidHCNException {
		if (validate(newHCN)) {
			this.HCN = newHCN;
		}
	}

	/**
	 * Sets the HCN to a new HCN from String.
	 * 
	 * @param newHCN
	 *            The string of the new HCN.
	 * @throws Exception
	 *             When it's not a valid number.
	 */
	public void set(String newHCN) throws Exception {
		if (validate(Integer.parseInt(newHCN)))
			this.HCN = Integer.parseInt(newHCN);
	}

	/**
	 * The validator which ensures that HCN is valid.
	 * (In this case, that it's a 6-digit natural number.)
	 * 
	 * @param HCN
	 * 		The healthcard number.
	 * 
	 * @return
	 * 		True, if HCN is a valid healthcard number.
	 * 
	 * @throws InvalidHCNException
	 * 		When HCN is not a 6-digit natural number.
	 */
	public boolean validate(int HCN) throws InvalidHCNException {
		String HCN2 = Integer.toString(HCN);
		if (HCN2.length() != 6 ) {
			throw new InvalidHCNException();
		}
		return true;

	}

	/**
	 * Gets the health card number.
	 * 
	 * @return The health card number as an integer.
	 */
	public int get() {
		return this.HCN;
	}
	
	@Override
	public String toString(){
		return Integer.toString(this.HCN);
	}
}
