package com.csc207_0898.triageapp;

import java.io.FileOutputStream;

import nu.xom.Document;
import nu.xom.Serializer;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainMenuActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);

		// populate GUI with buttons based on userType
		User user = UserPool.currentUser;
		if (user.getUserType().equals("nurse")) {
			View addVisitButton = (TextView) findViewById(R.id.addVisitButton);
			addVisitButton.setVisibility(View.VISIBLE);

			View patsByUrgButton = (TextView) findViewById(R.id.patsByUrgButton);
			patsByUrgButton.setVisibility(View.VISIBLE);
		}

		// welcome message
		TextView welcomeMessage = (TextView) findViewById(R.id.welcomeLabel);
		welcomeMessage.setText("Welcome, " + user.getUserName() + " ("
				+ user.getUserType() + ")");

		// actionbar
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
	 * Checks whether a Patient exists based on the healthcard number from hcntext.
	 * If so, goes to the corresponding PatientActivity.
	 * If not, error message is displayed (if physician) or goes to the
	 * CreatePatientActivity (if nurse).
	 * 
	 * @param view
	 * 		Android interaction/screen handler.
	 */
	public void searchPatient(View view) {

		EditText hcnView = (EditText) findViewById(R.id.hcntext);
		String hcn = hcnView.getText().toString();

		// lookup patient to see if PatientPool returns null for this HCN
		// 
		try {
			if (PatientPool.searchPatient(Integer.parseInt(hcn)) == null) {
				// if patient doesn't exist yet, display error message (for physician)
				if (UserPool.currentUser.getUserType().equals("physician")) {
					throw new Exception(
							"This patient doesn't exist, and you must be a nurse to add a patient.");
				} else {
					// or goto CreatePatientActivity (for nurse)
					Intent createPatientActivity = new Intent(this,
							CreatePatientActivity.class);
					createPatientActivity.putExtra("hcn", hcn);
					startActivity(createPatientActivity);
				}
				
			} else {
				// if patient already in PatientPool, goto corresponding PatientActivity
				Intent patientActivity = new Intent(this, PatientActivity.class);
				patientActivity.putExtra("patientHCN", hcn);
				startActivity(patientActivity);
			}
		} catch (Exception e) {
			// blank HCN or otherwise invalid, 
			// or no permission to create patient (for physician)
			TextView errorMessageView = (TextView) findViewById(R.id.mainErrorMessage);
			errorMessageView.setText(e.getMessage());
			errorMessageView.setVisibility(View.VISIBLE);
		}
	}

	/**
	 * Creates a saveDoc from the VisitPool and saves it to the XML_PATH.
	 * @param view
	 * 		Android interaction/screen handler.
	 * @throws Exception
	 * 		If XML_PATH is inaccessible.
	 */
	public void save(View view) throws Exception {
		try {
			Document doc = FileManager.getSaveDoc();
			FileOutputStream fileOutputStream = openFileOutput(
					FileManager.XML_PATH, MODE_PRIVATE);
			Serializer serializer = new Serializer(fileOutputStream,
					"ISO-8859-1");
			serializer.setIndent(4);
			serializer.setMaxLength(64);
			serializer.write(doc);
			View errorMessage = findViewById(R.id.saveSuccessfulMessage);
			errorMessage.setVisibility(View.VISIBLE);
			fileOutputStream.close();
		} catch (Exception e) {
			View errorMessage = findViewById(R.id.fileAccessErrorMessage);
			errorMessage.setVisibility(View.VISIBLE);

		}
	}

	/**
	 * Goes to the CreateVisitActivity.
	 * 
	 * @param view
	 * 		Android interaction/screen handler.
	 */
	public void gotoCreateVisitActivity(View view) {
		Intent gotoCreatePatient = new Intent(this, CreateVisitActivity.class);
		startActivity(gotoCreatePatient);
	}
	
	/**
	 * Goes to the PatientsByUrgencyActivity.
	 * 
	 * @param view
	 * 		Android interaction/screen handler.
	 */
	public void gotoPatsByUrg(View view) {
		Intent gotoPatsByUrg = new Intent(this, PatientsByUrgencyActivity.class);
		startActivity(gotoPatsByUrg);
	}
}
