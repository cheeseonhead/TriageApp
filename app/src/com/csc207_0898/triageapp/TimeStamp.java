package com.csc207_0898.triageapp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * A class which keeps track of a time in desired form.
 *
 */
public class TimeStamp {

	private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
	private Date timestamp;
	
	/**
	 * Creates a primitive value timestamp consisting of date.
	 * @param date
	 * 		Any Date object.
	 */
	public TimeStamp(Date date) {
		df.setLenient(false);
		timestamp = date;
	}
	
	/**
	 * Creates a primitive value timestamp consisting of date.
	 * @param date
	 * 		A String of form "yyyy-mm-dd hh:mm:ss".
	 */
	public TimeStamp(String timeString) throws Exception {
		df.setLenient(false);
		timestamp = df.parse(timeString);
	}
	
	/**
	 * Returns the timestamp as the format "YYYY-MM-DD HH:MM:SS".
	 */
	public String toString() {
		df.setLenient(false);
		String dateStr = df.format(timestamp);  

		return dateStr;
	}
	
	/**
	 * Returns the timestamp as a Date object.
	 */
	public Date getDate() {
		return timestamp;
	}
	
	
	/**
	 * Provides a string representation of the current time.
	 * 
	 * @return a string of the form "yyyy-mm-dd hh:mm:ss".
	 */
	public static String getCurrentDateStr() {
		Calendar cal = Calendar.getInstance();
		return df.format(cal.getTime());
	}

	/**
	 * Provides a Date of the current time.
	 * 
	 * @return a Date object.
	 */
	public static Date getCurrentDate() {
		return Calendar.getInstance().getTime();
	}
}
