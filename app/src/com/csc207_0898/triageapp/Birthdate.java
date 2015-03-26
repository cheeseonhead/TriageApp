package com.csc207_0898.triageapp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 
 * A class that stores the birthdate. 
 *
 */
public final class Birthdate {

	private Calendar birthdate = Calendar.getInstance();

	/**
	 * Constructs a Birthdate object using a YYYY-MM-DD string.
	 * 
	 * @param bString
	 *            A string of this format YYYY-MM-DD.
	 * @throws Exception
	 *             When bString is of an invalid format.
	 */
	public Birthdate(String bString) throws Exception {
		super();
		set(bString);
	}

	/**
	 * Sets the year, month, and day of this birthday to given values.
	 * 
	 * @param year
	 *            The new year to set to.
	 * @param month
	 *            The new month to set to.
	 * @param day
	 *            The new day to set to.
	 * @throws Exception
	 *             When the given new date is invalid.
	 */
	public void set(int year, int month, int day) throws Exception {
		set(year + "-" + month + "-" + day);
	}

	/**
	 * Sets the year, month, and day according to given string.
	 * 
	 * @param bString
	 *            The string formatted YYYY-MM-DD
	 * @throws Exception
	 *             When the date is invalid.
	 */
	
	/**
	 * Sets the year, month, and day according to given string.
	 * 
	 * @param bString
	 *            The string formatted YYYY-MM-DD.
	 * @throws Exception
	 *             When the date is invalid.
	 */
	public void set(String bString) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
		sdf.setLenient(false);
		birthdate.setTime(sdf.parse(bString));
	}


	/**
	 * Sets the year of this birthdate to a new year.
	 * 
	 * @param year
	 *            The year to set to
	 * @throws Exception
	 *             When the new date is not valid
	 */
	public void setYear(int year) throws Exception {
		set(year, birthdate.get(Calendar.MONTH) + 1,
				birthdate.get(Calendar.DATE));
	}

	/**
	 * Sets the month of this birthdate to a new month.
	 * 
	 * @param month
	 *            The month to set to.
	 * @throws Exception
	 *             When the new date is not valid.
	 */
	public void setMonth(int month) throws Exception {
		set(birthdate.get(Calendar.YEAR), month,
				birthdate.get(Calendar.DATE));
	}

	/**
	 * Sets the day of this birthdate to a new day.
	 * 
	 * @param day
	 *            The day to set to.
	 * @throws Exception
	 *             When the new date is not valid.
	 */
	public void setDay(int day) throws Exception {
		set(birthdate.get(Calendar.YEAR),
				birthdate.get(Calendar.MONTH) + 1, day);
	}

	/**
	 * Gets the year of this birthdate.
	 * @return Int representation of the year.
	 */
	public int getYear() {
		return birthdate.get(Calendar.YEAR);
	}

	/**
	 * Gets the month of this birthdate.
	 * @return Int representation of the month.
	 */
	public int getMonth() {
		return birthdate.get(Calendar.MONTH) + 1;
	}
	
	/**
	 * Gets the day of this birthdate.
	 * @return Int representation of the day.
	 */
	public int getDay(){
		return birthdate.get(Calendar.DATE);
	}
	
	/**
	 * Gets the Date object of this birthdate.
	 * @return The Date object
	 */
	public Date getDATE(){
		return birthdate.getTime();
	}

	/**
	 * Formats a string representation of form YYYY-MM-DD.
	 * @return string representation.
	 */
	public String toString() {
		String resp = String
				.format(Locale.US, "%04d-%02d-%02d", birthdate.get(Calendar.YEAR),
						birthdate.get(Calendar.MONTH) + 1,
						birthdate.get(Calendar.DATE));
		return resp;
	}
}