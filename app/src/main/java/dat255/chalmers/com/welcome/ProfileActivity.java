package dat255.chalmers.com.welcome;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;

import java.util.Calendar;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getFragmentManager().beginTransaction().replace(R.id.activity_preferences, new PrefsFragment()).commit();
    }

    public static class PrefsFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            //Set the proper SharedPreferences
            getPreferenceManager().setSharedPreferencesName(SharedPreferencesKeys.PREFS_NAME);
            SharedPreferences prefs = getPreferenceManager().getSharedPreferences();

            addPreferencesFromResource(R.xml.profile_prefs);

            //Display the user's name
            displayName(prefs);

            //Display the user's age in years
            displayAge(prefs);

            //Display the user's gender
            displayGender();
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
    }
}
