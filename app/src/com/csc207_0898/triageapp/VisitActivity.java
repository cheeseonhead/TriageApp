package com.csc207_0898.triageapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class VisitActivity extends Activity {

	private Visit targetVisit;
	private static int id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_visit);

		// add updateVitalButton for nurses
		if (UserPool.currentUser.getUserType().equals("nurse")) {
			View updateVitalButton = (TextView) findViewById(R.id.updateVital);
			updateVitalButton.setVisibility(View.VISIBLE);
		}
		

		id = Integer.parseInt(getIntent().getExtras().get("targetVisitId").toString());
		try {
			//populate listview with all the Visit's Vitals.
			targetVisit = VisitPool.getVisitByID(id);
			((TextView) findViewById(R.id.hcnField)).setText(Integer
					.toString(targetVisit.patient.healthCN.get()));
			((TextView) findViewById(R.id.arrivalDate))
					.setText(targetVisit.arrivalDate.toString());

			final Vital[] vitalArray = targetVisit.vitalHistory
					.getAllByNewest();
			ArrayAdapter<Vital> vitalAdapter = new ArrayAdapter<Vital>(this,
					android.R.layout.simple_list_item_1, vitalArray);
			ListView vitalList = (ListView) findViewById(R.id.vitalList);
			vitalList.setAdapter(vitalAdapter);

			// add prescript string to prescriptionView
			TextView prescription = (TextView) findViewById(R.id.prescriptionView);
			prescription.setText("Prescription: " + targetVisit.prescription);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (targetVisit.vitalHistory.size() != 0){
			// show doctorVisitButton if Patient has Visit(s)
			View doctorVisitButton = (TextView) findViewById(R.id.doctorVisitButton);
			doctorVisitButton.setVisibility(View.VISIBLE);
		}

		//button shows latest doctorVisit, unless there is none
		TextView doctorVisitButton = (TextView) findViewById(R.id.doctorVisitButton);
		try {
			doctorVisitButton.setText("Last seen: "
					+ targetVisit.getNewestDoctorVisit().toString()
					+ "\n Click to add visit.");
		} catch (Exception e) {
			doctorVisitButton
					.setText("Has not been seen by doctor yet. \n Click to add visit.");
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * Goto the CreateVitalActivity with this Visit's id as the intent extra.
	 * @param view
	 * 		Android interaction/screen handler.
	 */
	public void createVital(View view) {
		Intent intent = new Intent(VisitActivity.this,
				CreateVitalActivity.class);
		intent.putExtra("targetVisitId", targetVisit.getID());
		startActivity(intent);
	}

	/**
	 * Add a doctorVisit to this Patient Visit.
	 * @param view
	 * 		Android interaction/screen handler.
	 * @throws Exception
	 * 		If Patient.urgency cannot be calculated for whatever reason.
	 */		
	public void createDoctorVisit(View view) throws Exception {
		// add doctorVisit and set urgency to 0
		targetVisit.calculateUrgency(targetVisit.vitalHistory.getNewest(), targetVisit.patient);
		targetVisit.addDoctorVisit();
		Intent refresh = new Intent(VisitActivity.this, VisitActivity.class);
		refresh.putExtra("targetVisitId", targetVisit.getID());
		startActivity(refresh);
	}

}
