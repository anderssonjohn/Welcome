package dat255.chalmers.com.welcome;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import android.preference.ListPreference;
import android.preference.PreferenceFragment;

public class SharedPreferencesKeysTest {

    private static final String TEST_NAME = "name";

    private SharedPreferencesKeys mSharedPreferenceKeys;
    private SharedPreferencesKeysTest mMockedSharedPreferencesKeysTest;

    @Mock
    SharedPreferencesKeys mMockedSharedPrefrenceKeys;

    @Mock
    PreferenceFragment prefs;

    @Before
    public void initMocks(){
        prefs= new ProfileActivity.PrefsFragment();
        //String name = prefs.getString(SharedPreferencesKeysTest.TEST_NAME, "");

    }





}