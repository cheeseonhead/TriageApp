package com.csc207_0898.triageapp;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.List;

/**
 * A database of all visits loaded into this app. 
 *
 */
public class VisitPool {
	private static Map<Integer, Visit> visitPool = new TreeMap<Integer, Visit>();
	private static int totalVisits;

	
	/**
	 * Initiate a new blank visitPool.
	 * @param id
	 * 		A unique id to start the visitPool totalVisits at.
	 */
	public static void initPool(int id) {
		visitPool = new TreeMap<Integer, Visit>();
		totalVisits = id;
	}
	
	/**
	 * Creates a new Visit object and adds it visitPool with the lowest
	 * unused visit ID, lastVisitID.
	 * 
	 * @param person
	 * 		The Person object associated with the Visit.
	 * 
	 * @param arrivalDate
	 * 	 	A Date object of the current time.
	 * 
	 * @return the created Visit object.
	 */

	public static Visit createVisit(Patient patient, Date arrivalDate) {
		Visit newVisit = new Visit(patient, arrivalDate, totalVisits);
		visitPool.put(totalVisits, newVisit);
		totalVisits += 1;
		return newVisit;
	}
	
	/**
	 * An alternative method for creating a Visit with a forced ID. See primary method for full spec.
	 * @param id
	 * 		A unique visit id used as a key in visitPool.
	 */
	public static Visit createVisit(Patient patient, Date arrivalDate, int ID) throws Exception {
		Visit newVisit = new Visit(patient, arrivalDate, ID);
		visitPool.put(ID, newVisit);
		return newVisit;
	}
	
	/**
	 * Returns a List of all the Visit objects in the visitPool.
	 * 
	 * @return The list of all Visits.
	 */
	public static List<Visit> getAllVisits() {
		List<Visit> visitsList = new ArrayList<Visit>(visitPool.values());
		return visitsList;
	}
	
	/**
	 * Gets the Visit associated with the given id.
	 * Note: If id not in visitPool, this will return null.
	 * 
	 * @param id
	 * 		An integer id associated with the particular Visit.
	 *
	 * @return The Visit associated with the given id.
	 */
	public static Visit getVisitByID(int id){
		return visitPool.get(id);
	}
	
	/**
	 * Provides the number of visits.
	 * @return size of visitPool as an int.
	 */
	public static int getSize() {
		return visitPool.size();
	}
	
}
