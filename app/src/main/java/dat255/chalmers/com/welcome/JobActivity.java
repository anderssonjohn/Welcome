package dat255.chalmers.com.welcome;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static dat255.chalmers.com.welcome.SharedPreferencesKeys.DOB_DAY;
import static dat255.chalmers.com.welcome.SharedPreferencesKeys.DOB_MONTH;
import static dat255.chalmers.com.welcome.SharedPreferencesKeys.DOB_YEAR;
import static dat255.chalmers.com.welcome.SharedPreferencesKeys.GENDER;
import static dat255.chalmers.com.welcome.SharedPreferencesKeys.LANGUAGE;
import static dat255.chalmers.com.welcome.SharedPreferencesKeys.PREFS_NAME;
import static dat255.chalmers.com.welcome.SharedPreferencesKeys.JOB_ID;
import static dat255.chalmers.com.welcome.SharedPreferencesKeys.INTEREST_ID;
import static dat255.chalmers.com.welcome.SharedPreferencesKeys.FIRST_RUN;

public class JobActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job);

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
        String first_name = "first_name=name";
        String last_name = "last_name=test";
        String profession = "proffesion=whaaa";

        CreateUser createUser = new CreateUser();
        createUser.execute(first_name, last_name, profession);

        //Save that the user has gone through the first time setup
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(FIRST_RUN, false);
        editor.commit();

        Intent intent = new Intent(JobActivity.this, MainActivity.class);
        startActivity(intent);
    }
    public class CreateUser extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... strings) {

            String dataUrl = "http://johnandersson.me:3030/user";
            SharedPreferences preferences = getSharedPreferences(PREFS_NAME, 0);

            String gender = preferences.getString(GENDER, "null");
            Long profession = preferences.getLong(JOB_ID,0);
            Long interest = preferences.getLong(INTEREST_ID,0);

            int DateOfBirth = preferences.getInt(DOB_DAY,0);
            int MonthOfBirth = preferences.getInt(DOB_MONTH,0);
            int YearOfBirth = preferences.getInt(DOB_YEAR, 0);




            String dataUrlParameters = "name=" + "Johny Awesome"
                    + "&gender=" + gender
                    +"&profession=" + profession
                    + "&interest=" + interest
                    + "&date=" + YearOfBirth + "-" + MonthOfBirth + "-" + DateOfBirth
                    + "&swedish_speaker=" + "true";


            URL url;
            HttpURLConnection connection = null;
            try {
                // Create connection
                url = new URL(dataUrl);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                connection.setUseCaches(false);
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestProperty("Accept","*/*");

                // Send request
                DataOutputStream wr = new DataOutputStream(
                        connection.getOutputStream());
                wr.writeBytes(dataUrlParameters);
                wr.flush();
                wr.close();
                // Get Response
                InputStream is = connection.getInputStream();
                BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                String line;
                StringBuffer response = new StringBuffer();
                while ((line = rd.readLine()) != null) {
                    response.append(line);
                    response.append('\r');
                }
                rd.close();
                String responseStr = response.toString();
                Log.d("Server response", responseStr);

            } catch (Exception e) {

                e.printStackTrace();

            } finally {

                if (connection != null) {
                    connection.disconnect();
                }
            }

            return null;
        }
    }


}
