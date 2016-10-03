package dat255.chalmers.com.welcome;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import dat255.chalmers.com.welcome.BackendInterfaces.CreateUser;

import dat255.chalmers.com.welcome.BackendInterfaces.CreateUser;

import static dat255.chalmers.com.welcome.SharedPreferencesKeys.PREFS_NAME;
import static dat255.chalmers.com.welcome.SharedPreferencesKeys.JOB_ID;
import static dat255.chalmers.com.welcome.SharedPreferencesKeys.INTEREST_ID;
import static dat255.chalmers.com.welcome.SharedPreferencesKeys.FIRST_RUN;

public class JobAsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_as);

        Spinner spinnerJ = (Spinner) findViewById(R.id.spinnerJob);
        Spinner spinnerI = (Spinner) findViewById(R.id.spinnerInterest);

        //Define our own listener that makes sure we can't progress to the next activity
        //while the empty first item is selected
        AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Button button = (Button) findViewById(R.id.buttonDone);
                if (id == 0) {
                    button.setEnabled(false);
                }
                else {
                    button.setEnabled(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };

        spinnerJ.setOnItemSelectedListener(listener);
        spinnerI.setOnItemSelectedListener(listener);

        //Populate the spinners with options via an ArrayAdapter
        ArrayAdapter<CharSequence> adapter;

        adapter = ArrayAdapter.createFromResource(this,
                R.array.job_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerJ.setAdapter(adapter);

        adapter = ArrayAdapter.createFromResource(this,
                R.array.interests_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerI.setAdapter(adapter);
    }

    private void saveInfo() {
        Spinner spinnerJ = (Spinner) findViewById(R.id.spinnerJob);
        Spinner spinnerI = (Spinner) findViewById(R.id.spinnerInterest);

        long jobID = spinnerJ.getSelectedItemId();
        long interestID = spinnerI.getSelectedItemId();
        System.out.println(jobID);
        System.out.println(interestID);

        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putLong(JOB_ID, jobID);
        editor.putLong(INTEREST_ID, interestID);
        editor.commit();
    }

    public void showMainActivity(View view) {
        //Save all data
        saveInfo();

        new SendCreateUser().execute();

        //Save that the user has gone through the first time setup
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(FIRST_RUN, false);
        editor.commit();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private class SendCreateUser extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            CreateUser createUser = new CreateUser(JobAsActivity.this);
            createUser.createUser();
            return null;
        }
    }
}
