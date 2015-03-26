package com.csc207_0898.triageapp;

import java.util.List;
import java.util.TreeSet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class PatientsByUrgencyActivity extends Activity {
	
	@Override
	protected void onResume() {

	   super.onResume();
	   this.onCreate(null);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_patients_by_urgency);
		
		List<Patient> patientSet = PatientPool.getSortedPatients();
		final Patient[] patientSortedArray = patientSet.toArray(new Patient[patientSet.size()]);
		
		if (patientSortedArray.length == 0) {
			//show emptyPatientListMessage
			View noPatientsMessage = (TextView) findViewById(R.id.emptyPatientListMessage);
			noPatientsMessage.setVisibility(View.VISIBLE);
		} else {
			// populate listview with all Patients w/ urgency >= 0
			ArrayAdapter<Patient> patientAdapter = new ArrayAdapter<Patient>(this,
					android.R.layout.simple_list_item_1,
					patientSortedArray);
				ListView patientSortedList = (ListView) findViewById(R.id.patientSortedList);
				patientSortedList.setAdapter(patientAdapter);
				patientSortedList.setOnItemClickListener(new OnItemClickListener(){
				@Override
				public void onItemClick(AdapterView<?>adapter, View v, int position, long arg3){
					Intent intent = new Intent(PatientsByUrgencyActivity.this, PatientActivity.class);
					intent.putExtra("patientHCN", patientSortedArray[position].healthCN.get());
					startActivity(intent);
				}
			});
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.patients_by_urgency, menu);
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
}
