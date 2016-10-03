package dat255.chalmers.com.welcome;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.PreferenceFragment;
import android.support.annotation.Dimension;
import android.support.v7.app.AppCompatActivity;

public class PreferencesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

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
            EditTextPreference namePref = (EditTextPreference) findPreference(SharedPreferencesKeys.NAME);
            String name = prefs.getString(SharedPreferencesKeys.NAME, "");
            namePref.setTitle(name);
        }
    }
}
