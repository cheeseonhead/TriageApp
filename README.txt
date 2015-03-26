To compile in Eclipse, Import the app/ directory as an Android Project. If there are errors, right click on TriageApp, and Configure Build Path.
From there, make sure that in the Order & Export tab that the xom-1.2_10 is enabled. 
You might also have to disable Android Private Libraries (in Order & Export) and add JRE (in the Libraries tab).

We have also included TriageApp.apk, which you can install on a phone (if Third-party sources are enabled), in case you can't compile.

---------- HOW TO USE ---------------------------------------------------------------
As you will see when you load up the app, the MainActivity is used to login: so use a valid username/password (like 'kokos', 'ooooo') and click login
On the main menu, you can type in a health card number to search that patient, if the patient exists, then you can see it on the next screen. From there you can create a visit for this patient.
If you're logged in as a nurse, searching for a healthcard number that doesn't much any existing patient will take you to the Create Patient Activity.

When creating a visit, you have to input all the fields. And if some fields are not valid, then you will be prompted. Same with Vitals.

In order to update/add a Prescription as a physician, the Patient needs to have at least 1 visit!

After then end of this using session, don't forget to go back to the main page to save the current data for next use.
Otherwise when you log out, all the changes you made will be erased!

----------- BONUS --------------------------------------------------------------------
We think you should consider these features when marking:
* we implemented up buttons
* we used Android resource handling to make the app work on actual devices (not just emulators)
* we write our data to an correctly-formatted XML database, making it both computer AND human-readable
