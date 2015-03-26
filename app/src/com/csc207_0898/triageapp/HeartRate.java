package com.csc207_0898.triageapp;

/**
 * A class that stores the heartrate. 
 *
 */
public class HeartRate {
	private int heartRate;

	/**
	 * Automatically set the value of heartRate (the primitive value).
	 * @param heartRate
	 * 		An int representation of the heartRate.
	 * @throws Exception
	 * 		When validator rejects heartRate.
	 */
	public HeartRate(int heartRate) throws Exception {
		set(heartRate);
	}

	/**
	 * A setter for heartRate.
	 * @param newHeartRate
	 * 		Int of heartRate value being set.
	 * @throws Exception
	 * 		When validator rejects newHeartRate.
	 */
	public void set(int newHeartRate) throws Exception {
		if (validateHeartRate(newHeartRate)) {
			heartRate = newHeartRate;
		}
	}

	/**
	 * Getter for primitive heartRate int.
	 * @return heartRate
	 */
	public int get() {
		return heartRate;
	}

	/**
	 * A validator to make sure that heartRate is valid.
	 * @param heartRate
	 * 		Primitive value of class.
	 * @return true if heartRate is in valid range.
	 * @throws Exception
	 * 		When heartRate is invalid.
	 */
	private boolean validateHeartRate(int heartRate) throws Exception {
		if (heartRate < 40 || heartRate > 250) {
			throw new InvalidHeartRateException();
		}
		return true;
	}

	/** 
	 * Formats heartRate as "60 bpm", for example.
	 * @return a human-readable string representation of heartRate.
	 */
	public String toString() {
		return Integer.toString(heartRate) + " bpm";
	}
}
