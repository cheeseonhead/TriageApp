package com.csc207_0898.triageapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class CreatePatientActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_patient);
		
		// set hcn field with value from extras
		((EditText) findViewById(R.id.hcnField)).setText(getIntent()
				.getExtras().getString("hcn"));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	/**
	 * Add a Patient object with the inputted attributes.
	 * @param view
	 * 		Android interaction/screen handler.
	 * @throws Exception
	 * 		If hcnField is blank.
	 */
	public void createPatient(View view) throws Exception {
		try {
			// get patient info, ensure hcn not blank, and create Patient object
			String hcnString = (((TextView) findViewById(R.id.hcnField))
					.getText().toString());
			if (hcnString.matches("")) {
			    throw new InvalidHCNException();
			}
			int hcn = Integer.parseInt(hcnString);
			
			String dob = ((TextView) findViewById(R.id.dobField)).getText().toString();
			String name = ((TextView) findViewById(R.id.nameField)).getText().toString();
			String[] names = name.split(" ", 2);
			String firstname = names[0];
			String lastname = names[1];
			
			Patient newPatient = PatientPool.createPatient(hcn, firstname, lastname, dob);
			
			//add initial visit
			Visit newVisit = VisitPool.createVisit(newPatient, TimeStamp.getCurrentDate());
			newVisit.prescription = "";
			newPatient.visitHistory.add(newVisit);
	
			
			//goto the corresponding PatientActivity
			Intent intent = new Intent(CreatePatientActivity.this,
					PatientActivity.class);
	
			intent.putExtra("patientHCN", hcn);
			startActivity(intent);
		}
		catch (Exception e) {
			TextView errorMessage = (TextView) findViewById(R.id.createPatientErrorMessage);
			errorMessage.setText("Please fill in all the fields with valid inputs.");
			errorMessage.setVisibility(View.VISIBLE);
		}
	}
}
