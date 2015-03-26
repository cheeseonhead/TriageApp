package com.csc207_0898.triageapp;

import java.util.Date;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class CreateVisitActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_visit);

		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * Create a Visit with the inputed attributes.
	 * @param view
	 * 		Android interaction/screen handler.
	 */
	public void createVisit(View view) {
		int hcn2 = Integer.parseInt(((TextView) findViewById(R.id.hcn2))
				.getText().toString());

		try {
			//create visit, add it to Patient's visitHistory, and start VisitActivity
			Date date = TimeStamp.getCurrentDate();
			Visit newVisit = VisitPool.createVisit(PatientPool.searchPatient(hcn2),
					date);
			Patient targetPatient = PatientPool.searchPatient(hcn2);
			
			
			//automatically inherit prescription from last visit (if it exists) 
			//and add Visit to Patient.visitHistory
			newVisit.prescription = "";
			if (targetPatient.visitHistory.size() != 0) {
				newVisit.prescription = targetPatient.getPrescription();
			}
			targetPatient.visitHistory.add(newVisit);
	
			
			//goto corresponding VisitActivity
			Intent intent = new Intent(CreateVisitActivity.this,
					VisitActivity.class);
	
			intent.putExtra("targetVisitId", newVisit.getID());
			startActivity(intent);
		}
		catch (Exception e) {
			//hcn doesn't exist
			View errorMessage = findViewById(R.id.hcnErrorMessage);
			errorMessage.setVisibility(View.VISIBLE);
		}
	}
}
