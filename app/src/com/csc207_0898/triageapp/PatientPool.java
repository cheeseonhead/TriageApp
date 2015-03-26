package com.csc207_0898.triageapp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

/**
 * 
 * A database of Patients.
 * 
 */
public class PatientPool {
	// data members
	private static TreeMap<Integer, Patient> patientPool = new TreeMap<Integer, Patient>();

	/**
	 * Construct an empty TreeMap mapping ints to Patients.
	 */
	public static void initPool() {
		patientPool = new TreeMap<Integer, Patient>();
	}

	/**
	 * 
	 * Create a Patient uniquely identified by health card number, first name,
	 * last name, and birth date.
	 * 
	 * @param hcn
	 *            Patient health card number.
	 * @param firstName
	 *            Patient first name
	 * @param lastName
	 *            Patient last name
	 * @param birthDate
	 *            Patient birth date
	 * @return - Patient The Patient object.
	 * @throws Exception
	 *             If hcn, firsstName, lastName, or birthDate are invalid.
	 */
	public static Patient createPatient(int hcn, String firstName,
			String lastName, String birthDate) throws Exception {
		Patient p = new Patient(hcn, firstName, lastName, birthDate);
		patientPool.put(hcn, p);

		return p;
	}

	/**
	 * 
	 * Search for a Patient in the patient pool by health card number.
	 * 
	 * @param hcn
	 *            The HCN of the requested Patient object.
	 * @return Patient object.
	 */
	public static Patient searchPatient(int hcn) {
		return patientPool.get(hcn);
	}

	/**
	 * Check whether the Patient is in the PatientPool.
	 * 
	 * @param hcn
	 *            The healthcard number of the target Patient.
	 * @return True if Patient in PatientPool, False if not.
	 */
	public static boolean hasPatient(int hcn) {
		return patientPool.get(hcn) != null;
	}

	/**
	 * 
	 * Returns a tree set of "naturally ordered" Patients according to their
	 * compareTo() definition.
	 * 
	 * @return TreeSet<Patient> sorted by descending urgency.
	 */
	public static List<Patient> getSortedPatients() {
		Collection<Patient> patientCollection = patientPool.values();
		List<Patient> patientList = new ArrayList<Patient>(patientCollection);

		Iterator<Patient> itr = patientList.iterator();
		while (itr.hasNext()) {
			if ((itr.next().getUrgency()) == -1) {
				itr.remove();
			}
		}
		Collections.sort(patientList, Collections.reverseOrder());

		return patientList;
	}

	/**
	 * Add a Patient to the PatientPool.
	 * 
	 * @param patient
	 *            The Patient object being added to PatientPool.
	 */
	public static void addPatient(Patient patient) {
		patientPool.put(patient.healthCN.get(), patient);
	}
}
