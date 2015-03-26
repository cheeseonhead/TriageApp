package com.csc207_0898.triageapp;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import android.content.res.Resources;
import nu.xom.*;

/**
 * A file managing class that takes care of loading and saving the information
 * from and to files.
 */
public class FileManager {

	// setup static database variables
	public static final String XML_PATH = "new_visits_db.xml";
	Element totalVisits = new Element("TotalVisits");
	private static Element root;

	/**
	 * Loads patient_records.txt data and populates PatientPool accordingly.
	 * 
	 * @param resources
	 *            Resource/asset context (handler for Android filesystem).
	 * 
	 * @param filePath
	 *            The filename of the patient records file (patient_records.txt
	 *            by default).
	 * 
	 * @throws Exception
	 *             when Patient data is invalid.
	 */
	public static void loadPatientFile(Resources resources, String filePath) throws Exception {

		//destroy the currently loaded Patient & Visit Pools
		PatientPool.initPool();
		
		Scanner bf = new Scanner(resources.getAssets().open(
				"patient_records.txt"));

		while (bf.hasNextLine()) {
			String[] patientMeta = bf.nextLine().split(",");
			String[] names = patientMeta[1].split(" ", 2);
			PatientPool.createPatient(Integer.parseInt(patientMeta[0]),
					names[0], names[1], patientMeta[2]);
		}

		bf.close();
	}

	/**
	 * Takes in an XOM Document (already read from XML_PATH by MainActivity), and populates
	 * ALL visits (so that if we call MainMenuActivity.save() it won't overwrite
	 * with an incomplete visitPool database).
	 * 
	 * @param doc
	 *            An XOM Document loaded from XML_PATH .
	 * 
	 * @throws Exception
	 *             when Visit data is corrupted.
	 */
	public static void addVisitsFromXML(Document doc) throws Exception {

		Element root = doc.getRootElement();

		// Read Visits and populate VisitPool by iterating through XML Tree.
		VisitPool.initPool(Integer.parseInt(root.getAttribute("total").getValue()));
		Elements allVisits = root.getChildElements();
		for (int i = 0; i < allVisits.size(); i++) {
			Element curVisit = allVisits.get(i);
			Elements visitMeta = curVisit.getChildElements();
			// visitMeta = [ID, HCN, arrivalDate, DOB, first name, last name, prescription, vitals]
			
			int ID = Integer.parseInt(visitMeta.get(0).getValue());
			int HCN = Integer.parseInt(visitMeta.get(1).getValue());
			String arrivalDateStr = visitMeta.get(2).getValue();
			Date arrivalDate = new TimeStamp(arrivalDateStr).getDate();

			if (!(PatientPool.hasPatient(HCN))) {
				//add Patient to the pool if it doesn't already exist (i.e., if not in patient_records.txt)
				String dobStr = visitMeta.get(3).getValue();
				String firstName = visitMeta.get(4).getValue();
				String lastName = visitMeta.get(5).getValue();
				PatientPool.createPatient(HCN, firstName, lastName, dobStr);
			}
			
			// create visit and give prescription + vitals
			Visit targetVisit = VisitPool.createVisit(PatientPool
					.searchPatient(HCN), arrivalDate, ID);
			String thisPrescription = visitMeta.get(6).getValue();
			targetVisit.prescription = thisPrescription;
			PatientPool.searchPatient(HCN).visitHistory.add(targetVisit);

			Elements allVitals = visitMeta.get(7).getChildElements();
			for (int j = 0; j < allVitals.size(); j++) {
				Elements vitalMeta = allVitals.get(j).getChildElements();
				String temp = vitalMeta.get(0).getValue();
				String bloodPressure = vitalMeta.get(1).getValue();
				String heartRate = vitalMeta.get(2).getValue();
				String timeStampStr = vitalMeta.get(3).getValue();
				Date timeStamp = new TimeStamp(timeStampStr).getDate();

				targetVisit.updateVital(temp + "," + bloodPressure + ","
						+ heartRate, timeStamp);
			}
		}
	}

	/**
	 * Generates an XOM Document of the VisitPool data.
	 * 
	 * @return an XOM Document populated with VisitPool data.
	 * 
	 */
	public static Document getSaveDoc() {
		root = new Element("Visits");
		root.addAttribute(new Attribute("total", Integer.toString(VisitPool
				.getSize())));
		List<Visit> allVisits = VisitPool.getAllVisits();
		for (int i = 0; i < VisitPool.getSize(); i++) {

			Element visit = new Element("Visit");

			Element id = new Element("ID");
			int theID = allVisits.get(i).getID();
			id.appendChild(Integer.toString(theID));
			visit.appendChild(id);

			Element HCN = new Element("HCN");
			int theHCN = allVisits.get(i).patient.healthCN.get();
			HCN.appendChild(Integer.toString(theHCN));
			visit.appendChild(HCN);

			Element arrivalDate = new Element("ArrivalTime");
			String theArrivalDate = allVisits.get(i).arrivalDate.toString();
			arrivalDate.appendChild(theArrivalDate);
			visit.appendChild(arrivalDate);
			
			Element dob = new Element("DateOfBirth");
			dob.appendChild(PatientPool.searchPatient(theHCN).birthdate.toString());
			visit.appendChild(dob);
			
			Element firstName = new Element("FirstName");
			firstName.appendChild(PatientPool.searchPatient(theHCN).firstname.get());
			visit.appendChild(firstName);

			Element lastName = new Element("LastName");
			lastName.appendChild(PatientPool.searchPatient(theHCN).surname.get());
			visit.appendChild(lastName);
			
			Element prescription = new Element("Prescription");
			String thePrescription = allVisits.get(i).prescription;
			prescription.appendChild(thePrescription);
			visit.appendChild(prescription);

			Element vitalHistory = new Element("VitalHistory");
			Vital[] allVital = allVisits.get(i).vitalHistory.getAll();
			for (int j = 0; j < allVital.length; j++) {
				Element vital = new Element("Vital");
				Element heartrate = new Element("Heartrate");
				Element temperature = new Element("Temperature");
				Element bloodPressure = new Element("BloodPressure");
				Element timeStamp = new Element("TimeStamp");

				float theTemperature = allVital[j].temperature.get();
				temperature.appendChild(Float.toString(theTemperature));
				vital.appendChild(temperature);

				int diastolicHeartrate = allVital[j].bloodPressure
						.getDiastolic();
				int systolicHeartrate = allVital[j].bloodPressure.getSystolic();
				bloodPressure.appendChild(Integer.toString(diastolicHeartrate)
						+ "," + Integer.toString(systolicHeartrate));
				vital.appendChild(bloodPressure);

				int theHeartRate = allVital[j].heartRate.get();
				heartrate.appendChild(Integer.toString(theHeartRate));
				vital.appendChild(heartrate);

				String theTimeStamp = allVital[j].timeStamp.toString();
				timeStamp.appendChild(theTimeStamp);
				vital.appendChild(timeStamp);

				vitalHistory.appendChild(vital);
			}

			visit.appendChild(vitalHistory);
			

			root.appendChild(visit);

		}

		Document doc = new Document(root);
		return doc;

	}

	/**
	 * Creates a UserPool instance and populates it from the passwords file.
	 * 
	 * @param resources
	 * 		Filesystem handler for Android.
	 * @param filePath
	 * 		Filepath for passwords/user text file.
	 * 	
	 * @throws Exception
	 * 		When file doesn't exist.
	 */
	public static void loadUserRecords(Resources resources, String filePath)
			throws Exception {
		UserPool.init();
		Scanner bf = new Scanner(resources.getAssets().open(filePath));
		while (bf.hasNextLine()) {
			String[] userInfo = bf.nextLine().split(",");
			UserPool.addUser(userInfo[0], userInfo[1], userInfo[2]);
		}

		bf.close();
	}

}
