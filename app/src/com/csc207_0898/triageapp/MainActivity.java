package com.csc207_0898.triageapp;

import java.io.FileInputStream;
import nu.xom.Builder;
import nu.xom.Document;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// load the passwords/users and populate UserPool
		try {
			FileManager.loadUserRecords(getResources(), "passwords.txt");
		} catch (Exception e) {
			// passwords.txt doesn't exist
			e.printStackTrace();
		}
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
	 * Verifies that the username and password are valid, sets the corresponding User
	 * as the UserPool.currentUser, and goes to MainMenuActivity.
	 * 
	 * @param view
	 * 		Android interaction/screen handler.
	 * @throws Exception
	 * 		If username not in system or password doesn't match username.
	 */
	public void logIn(View view) throws Exception {
		EditText userView = (EditText) findViewById(R.id.userField);
		String username = userView.getText().toString();

		EditText pwView = (EditText) findViewById(R.id.pwField);
		String pw = pwView.getText().toString();

		try {
			// login
			UserPool.setCurrentUser(username, pw);

			// load patient records
			FileManager.loadPatientFile(getResources(), "patient_records.txt");

			if (!(getFileStreamPath(FileManager.XML_PATH).exists())) {
				// create new visitpool if database can't be found
				VisitPool.initPool(0);
			} else {
				// populate VisitPool from database
				Builder builder = new Builder();
				FileInputStream fileInput = openFileInput(FileManager.XML_PATH);
				Document doc = builder.build(fileInput);
				FileManager.addVisitsFromXML(doc);
				fileInput.close();
			}

			// goto mainMenu (logged in)
			Intent mainMenu = new Intent(this, MainMenuActivity.class);
			startActivity(mainMenu);

		} catch (InvalidUserNameException e) {
			View userErrorMessage = findViewById(R.id.userErrorMessage);
			userErrorMessage.setVisibility(View.VISIBLE);
		}

		catch (InvalidPasswordException e) {
			View pwErrorMessage = findViewById(R.id.pwErrorMessage);
			pwErrorMessage.setVisibility(View.VISIBLE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}