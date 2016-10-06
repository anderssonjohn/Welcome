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

    public void mentorSelected(View view) {
        saveMentorState(true);
        showInfoActivity();
    }

    public void asylumSeekerSelected(View view) {
        saveMentorState(false);
        showInfoActivity();
    }

    private void saveMentorState(boolean boo) {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putBoolean(SWEDISH_SPEAKER, boo);
        editor.commit();
    }

    private void showInfoActivity() {
        Intent intent = new Intent(this, InfoActivity.class);
        startActivity(intent);
    }
}
