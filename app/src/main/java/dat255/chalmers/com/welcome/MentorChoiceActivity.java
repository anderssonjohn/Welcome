package dat255.chalmers.com.welcome;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import static dat255.chalmers.com.welcome.SharedPreferencesKeys.SWEDISH_SPEAKER;
import static dat255.chalmers.com.welcome.SharedPreferencesKeys.PREFS_NAME;

public class MentorChoiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor_choice);
    }

    public void showInfoSvActivity(View view) {
        saveInfo(true);
        Intent intent = new Intent(this, InfoSvActivity.class);
        startActivity(intent);
    }

    public void showInfoAsActivity(View view) {
        saveInfo(false);
        Intent intent = new Intent(this, InfoAsActivity.class);
        startActivity(intent);
    }

    private void saveInfo(boolean boo) {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putBoolean(SWEDISH_SPEAKER, boo);
        editor.commit();
    }
}
