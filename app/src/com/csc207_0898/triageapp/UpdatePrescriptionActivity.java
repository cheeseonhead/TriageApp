package com.csc207_0898.triageapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class UpdatePrescriptionActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update_prescription);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.update_prescription, menu);
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
	 * Update the prescription for the Patient's latest Visit.
	 * @param view
	 * 		Android interaction/screen handler.
	 * @throws Exception
	 * 		If the Patient has no Visits yet.
	 */
	public void updatePrescription(View view) throws Exception {
		int hcn = Integer.parseInt(getIntent().getExtras().get("hcn").toString());
		EditText prescriptView = (EditText) findViewById(R.id.prescriptionLabel);
		String prescriptStr = prescriptView.getText().toString();
		PatientPool.searchPatient(hcn).setPrescription(prescriptStr);
		
		Intent intent = new Intent(UpdatePrescriptionActivity.this, PatientActivity.class);
		intent.putExtra("patientHCN", hcn);
		startActivity(intent);
	}
}
