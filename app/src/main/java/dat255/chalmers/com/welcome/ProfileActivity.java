package dat255.chalmers.com.welcome;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;

import java.util.Calendar;
import java.util.StringTokenizer;

import dat255.chalmers.com.welcome.BackendInterfaces.BackendConnection;

import static dat255.chalmers.com.welcome.SharedPreferencesKeys.AUTH_TOKEN;
import static dat255.chalmers.com.welcome.SharedPreferencesKeys.JOB_ID;
import static dat255.chalmers.com.welcome.SharedPreferencesKeys.PREFS_NAME;

public class ProfileActivity extends AppCompatActivity {
//hej
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getFragmentManager().beginTransaction().replace(R.id.activity_profile, new PrefsFragment()).commit();
    }

    public static class PrefsFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            //Set the proper SharedPreferences
            getPreferenceManager().setSharedPreferencesName(PREFS_NAME);
            SharedPreferences prefs = getPreferenceManager().getSharedPreferences();

            addPreferencesFromResource(R.xml.profile_prefs);

            //Display the user's name
            displayName(prefs);

            //Display the user's age in years
            displayAge(prefs);

            //Display the user's gender
            displayGender();

            //Display the job
            //displayJob();

            //Display the interest
            //displayInterest();
        }

        private void displayGender() {
            ListPreference genderPref = (ListPreference) findPreference(SharedPreferencesKeys.GENDER);
            genderPref.setTitle(genderPref.getEntry());
        }

        private void displayAge(SharedPreferences prefs) {
            //Get the user's DOB
            int year = prefs.getInt(SharedPreferencesKeys.DOB_YEAR, 1900);
            int month = prefs.getInt(SharedPreferencesKeys.DOB_MONTH, 1);
            int day = prefs.getInt(SharedPreferencesKeys.DOB_DAY, 1);

            //Subtract the DOB from he current date
            Calendar current = Calendar.getInstance();
            current.add(Calendar.YEAR, (-1*year));
            current.add(Calendar.MONTH, (-1*month));
            current.add(Calendar.DATE, (-1*day));
            int age = current.get(Calendar.YEAR);

            EditTextPreference agePref = (EditTextPreference) findPreference("date");
            String ageText = Integer.toString(age) + " " + agePref.getTitle().toString();
            agePref.setTitle(ageText);
        }

        private void displayName(SharedPreferences prefs) {
            EditTextPreference namePref = (EditTextPreference) findPreference(SharedPreferencesKeys.NAME);
            String name = prefs.getString(SharedPreferencesKeys.NAME, "");
            namePref.setTitle(name);
        }

        //This can be used to display job and interest, the problem is to update the page immedietly.
        /**private void displayJob(){
            ListPreference genderPref = (ListPreference) findPreference(SharedPreferencesKeys.JOB_ID);
            genderPref.setTitle("Jobb: " + genderPref.getEntry());
        }

        private void displayInterest(){
            ListPreference genderPref = (ListPreference) findPreference(SharedPreferencesKeys.INTEREST_ID);
            genderPref.setTitle("Intresse: " + genderPref.getEntry());
        }*/
    }

    @Override
    protected void onPause() {
        super.onPause();
        new UpdateProfession().execute();
    }

    private class UpdateProfession extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            SharedPreferences prefs = getSharedPreferences(PREFS_NAME, 0);

            String urlParameters = "profession=" + prefs.getString(JOB_ID, "");
            String subPath = "update_profession";
            String authToken = prefs.getString(AUTH_TOKEN, "");

            BackendConnection.sendPost(subPath, urlParameters, authToken);
            return null;
        }
    }
}
