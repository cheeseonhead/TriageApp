package com.csc207_0898.triageapp;


/**
 * Class Temperature contains one variable which is a float for the temperate
 * which must be between 10.0 and 50.0 in Celcius.
 * 
 */
public class Temperature {
	private float temperature;

	/**
	 * Public constructor for Temperature class which takes a single argument as
	 * a float and assigns it to the private variable temperature using setTemp.
	 * 
	 * @param temperature
	 * 		The temperature as a float.
	 */
	public Temperature(float temperature) throws Exception {
		set(temperature);
	}

	/**
	 * Checks if the temperature falls into a valid range and then sets the
	 * private variable temperature to this value.
	 * 
	 * @param newTemperature
	 * 		The temperature being validated.
	 * @throws Exception
	 * 		If newTemperature is invalid.
	 */
	public void set(float newTemperature) throws Exception {
		if (validateTemperature(newTemperature)) {
			temperature = newTemperature;
		}

	}

	/**
	 * Returns the variable associated with this object,
	 * 
	 * @return
	 * 		Temperature as a float.
	 */
	public float get() {
		return temperature;
	}

	/**
	 * Returns whether or not the argument temperature, as a float, falls into a
	 * valid range, throws an exception if not.
	 * 
	 * @param temperature
	 * 		The temperature being validated.
	 * @return
	 * 		True, if the temperature is valid.
	 * @throws Exception
	 * 		If the temperature is invalid.
	 */
	public boolean validateTemperature(float temperature) throws Exception {
		if (temperature < 10.0 || temperature > 50.0) {
			throw new InvalidTemperatureException();
		}
		return true;

	}
	
	@Override
	public String toString(){
		return Float.toString(get()) + " degrees";
	}

}
