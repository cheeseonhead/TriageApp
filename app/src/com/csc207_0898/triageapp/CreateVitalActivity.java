package com.csc207_0898.triageapp;

import java.util.Date;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class CreateVitalActivity extends Activity {

	private Visit targetVisit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_vital);

		//get the targetVisit from the intent extras
		int id = Integer.parseInt(getIntent().getExtras().get("targetVisitId")
				.toString());
		targetVisit = VisitPool.getVisitByID(id);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * Create a Vital from the corresponding vital fields.
	 * @param view
	 * 		Android interaction/screen handler.
	 */
	public void createVital(View view) {
		try {
			Date timeStamp = TimeStamp.getCurrentDate();
			String temperature = ((TextView) findViewById(R.id.hcn2)).getText().toString();
			String heartrate = ((TextView) findViewById(R.id.heartrate)).getText().toString();
			String diastolic = ((TextView) findViewById(R.id.diastolic)).getText().toString();
			String systolic = ((TextView) findViewById(R.id.systolic)).getText().toString();
			
			// check if the input is blank for each vital, if so use data from previous vital
			// unless this is the first vital of this visit, in which case throw Exception
			// (by trying to access 
			// not sure how to do it with less code
			if (temperature.equals("")) {
				temperature = Float.toString(targetVisit.vitalHistory.getNewest().temperature.get());
			}
			if (heartrate.equals("")) {
				heartrate = Integer.toString(targetVisit.vitalHistory.getNewest().heartRate.get());
			}
			if (diastolic.equals("")) {
				diastolic = Integer.toString(targetVisit.vitalHistory.getNewest().bloodPressure
						.getDiastolic());
			}
			if (systolic.equals("")) {
				systolic = Integer.toString(targetVisit.vitalHistory.getNewest().bloodPressure
						.getSystolic());
			}
				
			//construct the newVitalStr and create the targetVital, then goto that VitalActivity
			String newVitalStr = temperature + "," + diastolic + "," + systolic + "," + heartrate;
			targetVisit.updateVital(newVitalStr, timeStamp);
			
			Intent intent = new Intent(CreateVitalActivity.this, VisitActivity.class);
			intent.putExtra("targetVisitId", targetVisit.getID());
			startActivity(intent);
		}
		catch (Exception e) {
			TextView errorMessageView = (TextView) findViewById(R.id.vitalErrorMessage);
			errorMessageView.setText(e.getMessage());	
			errorMessageView.setVisibility(View.VISIBLE);
		}
	}
}
