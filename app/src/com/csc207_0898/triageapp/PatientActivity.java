package com.csc207_0898.triageapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class PatientActivity extends Activity {
	static private String hcn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_patient);

		// get the hcn from intent extras, and populate page based on Patient
		
		TextView patientHCN = (TextView) findViewById(R.id.patientHCN);
		hcn = getIntent().getExtras().get("patientHCN").toString();
		patientHCN.setText(hcn);

		Patient targetPatient = PatientPool
				.searchPatient(Integer.parseInt(hcn));
		((TextView) findViewById(R.id.firstname))
				.setText(targetPatient.firstname.get());
		((TextView) findViewById(R.id.surname)).setText(targetPatient.surname
				.get());
		((TextView) findViewById(R.id.birthdate))
				.setText(targetPatient.birthdate.toString());

		
		// make prescription stuff visible
		if (targetPatient.visitHistory.size() != 0) {
			if (UserPool.currentUser.getUserType().equals("physician")) {
				// make updatePrescriptionButton visible if userType==physician and
				// Patient has Visit(s)
				View updatePrescriptionButton = (TextView) findViewById(R.id.gotoUpdatePrescription);
				updatePrescriptionButton.setVisibility(View.VISIBLE);
			}
			
			// make the prescriptionLabel visible
			TextView prescription = (TextView) findViewById(R.id.prescriptionLabel);
			prescription.setText("Prescription: "
					+ targetPatient.getPrescription());
		}

		// make nurse buttons visible
		if (UserPool.currentUser.getUserType().equals("nurse")) {
			View createVisitButton = (TextView) findViewById(R.id.createVisit);
			createVisitButton.setVisibility(View.VISIBLE);
		} 

		//populate the listview with all of the Patient's Visits
		final Visit[] visitArray = targetPatient.visitHistory.getAllByNewest();
		ArrayAdapter<Visit> visitAdapter = new ArrayAdapter<Visit>(this,
				android.R.layout.simple_list_item_1, visitArray);
		ListView visitList = (ListView) findViewById(R.id.visitList);
		visitList.setAdapter(visitAdapter);
		visitList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View v,
					int position, long arg3) {
				Intent intent = new Intent(PatientActivity.this,
						VisitActivity.class);
				intent.putExtra("targetVisitId", visitArray[position].getID());
				startActivity(intent);
			}
		});

	}

	/**
	 * Adds a new Visit to the Patient's visitHistory,
	 * and goes to the corresponding VisitActivity.
	 * @param view
	 * 		Android interaction/screen handler.
	 */
	public void createVisit(View view) {
		// skips past the CreateVisitActivity and creates a visit based on
		// Patient's HCN
		Visit newVisit = VisitPool.createVisit(
				PatientPool.searchPatient(Integer.parseInt(hcn)),
				TimeStamp.getCurrentDate());
		Patient targetPatient = PatientPool
				.searchPatient(Integer.parseInt(hcn));

		// automatically inherit prescription from last visit and add newVisit
		// to targetPatient.visitHistory
		newVisit.prescription = "";
		if (targetPatient.visitHistory.size() != 0) {
			newVisit.prescription = targetPatient.getPrescription();
		}
		targetPatient.visitHistory.add(newVisit);

		Intent intent = new Intent(PatientActivity.this, VisitActivity.class);
		intent.putExtra("targetVisitId", newVisit.getID());
		startActivity(intent);
	}

	/**
	 * Goes to the UpdatePrescriptionActivity.
	 * 
	 * @param view
	 * 		Android interaction/screen handler.
	 */
	public void gotoUpdatePrescription(View view) {
		Intent intent = new Intent(PatientActivity.this,
				UpdatePrescriptionActivity.class);
		intent.putExtra("hcn", hcn);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
