package com.csc207_0898.triageapp;

import java.util.Date;

/**
 * Class vital contains the objects Temperature, Bloodpressure, Heartrate and
 * Date
 * 
 */
public class Vital {
	public Temperature temperature;
	public BloodPressure bloodPressure;
	public HeartRate heartRate;
	public TimeStamp timeStamp;

	/**
	 * Public constructor for Vital which takes two arguments, a String
	 * 'initVitals' and a Date 'timeStamp'. It will use splitEntry to split the
	 * string and then parse the arguments according to the required needs of
	 * the constructor for each variable class. Throws an exception if they are
	 * not in the required range.
	 * 
	 * @param initVitals
	 * @param newDate
	 */
	public Vital(String initVitals, Date date) throws Exception {
		String[] vitalArray = this.splitEntry(initVitals);
		float newTemp = Float.parseFloat(vitalArray[0]);
		int diastolic = Integer.parseInt(vitalArray[1]);
		int systolic = Integer.parseInt(vitalArray[2]);
		int newHeartRate = Integer.parseInt(vitalArray[3]);

		temperature = new Temperature(newTemp);
		bloodPressure = new BloodPressure(diastolic, systolic);
		heartRate = new HeartRate(newHeartRate);
		timeStamp = new TimeStamp(date);
	}

	/**
	 * Splits the string into an array of strings and returns it so they can be
	 * parsed in the constructor.
	 * 
	 * @param initVitals
	 * @return
	 */
	private String[] splitEntry(String initVitals) {
		String[] vitalArray = initVitals.split(",");
		return vitalArray;
	}
	
	/**
	 * Provides a string representation of the Vitals.
	 * e.g. "1995-28-04 13:22:33
	 * 		 30.0 degrees, 40/30 mmHg, 40 bpm"""
	 * @return Vitals formatted as a string.
	 */
	public String toString(){
		String resp = "";
		resp += timeStamp.toString();
		resp += "\n " + temperature.toString() + ",";
		resp += " " + bloodPressure.toString() + ",";
		resp += " " + heartRate.toString();
		return resp;
	}
}
