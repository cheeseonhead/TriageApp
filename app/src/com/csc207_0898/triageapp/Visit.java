package com.csc207_0898.triageapp;

import java.util.ArrayList;
import java.util.Date;

/**
 * A class which records information of a visit by a patient to the facility. 
 *
 */
public class Visit {
	public VitalHistory vitalHistory = new VitalHistory();
	public TimeStamp arrivalDate;
	public Patient patient;
	private int ID;
	protected String prescription;
	protected ArrayList<Date> doctorVisits = new ArrayList();
	
	/**
	 * Creates a Visit with no vitals.
	 * 
	 * @param Patient
	 * 		Patient objected associated with Visit.
	 * 
	 * @param arrivalDate
	 * 		A Date object generated on arrival.
	 */
	public Visit(Patient patient, Date arrivalDate, int ID) {
		this.patient = patient;
		this.ID = ID;
		
		this.arrivalDate = new TimeStamp(arrivalDate);
	}

	
	/**
	 * Creates a new Vital object and adds it to the vitalHistory.
	 * 
	 * @param newVitalStr
	 * 		A string representation of the vitals which a nurse is adding.
	 * 
	 * @param newDate
	 * 		Any Date object.
	 * 
	 * @throws Exception
	 * 		Throws if Vital() rejects newVitalStr.
	 */
	public void updateVital(String newVitalStr, Date newDate) throws Exception {
		Vital newVital = new Vital(newVitalStr, newDate);
		vitalHistory.add(newVital);
		// urgency recalculated with each new vital entry, updated in Patient
		patient.updateUrgency(calculateUrgency(newVital, patient));
	}

	@Override
	public String toString() {
		return ("Arrival Date: " + arrivalDate.toString() + ", Urgency: " + Integer.toString(patient.getUrgency()));
	}
	
	/**
	 * Getter for unique Visit ID.
	 * @return 
	 * 		id as an int.
	 */
	public int getID() {
		return ID;
	}

	/**
	 * 
	 * Calculate the Patient urgency for the most recent Visit and Vital update.
	 * 
	 * @return 
	 * 		The calculated urgency as a short.
	 */
	public short calculateUrgency(Vital vital, Patient patient) throws Exception {
		// the 2 parameters and (patient.GetLatestVisit()) are not necessary
		// if calculateUrgency is only used in updateVital()
		if (patient.getLatestVisit().hasBeenSeenByDoctor()) {
			return -1;
		} else {
			// compare vital markers to the allowed ranges according to hospital policy
			short urgency = 0;
			if (vital.temperature.get() > 39.0) {
				urgency++;
			}
			if ((vital.heartRate.get() <= 50) || (vital.heartRate.get() >= 100)) {
				urgency++;
			}
			if ((vital.bloodPressure.getSystolic() >= 140) || (vital.bloodPressure.getDiastolic() >= 90)) {
				urgency++;
			}
			if (patient.getAge() < 2) {
				urgency++;
			}
			return urgency;
		}
	}

	/**
	 * Add a doctorVisit to this Visit (which uses the current time).
	 */
	public void addDoctorVisit() {
		doctorVisits.add(TimeStamp.getCurrentDate());
	}
	
	/**
	 * Get the newest doctorVisit Date if it exists.
	 * 
	 * @return
	 * 		The Date of the newest doctorVisit.
	 * @throws Exception
	 * 		When there are no doctorVisits for this Visit.
	 */
	public Date getNewestDoctorVisit() throws Exception {
		if (doctorVisits.size() == 0) {
			throw new Exception("There are no doctor visits yet!");
		}
		else {
			return doctorVisits.get(doctorVisits.size() - 1);
		}
		
	}
	
	/**
	 * Checks if the Patient has been seen by a doctor for this Visit.
	 * @return
	 * 		True if Visit has doctorVisit(s), False otherwise.
	 */
	public boolean hasBeenSeenByDoctor() {
		return (doctorVisits.size() != 0);
	}

}

