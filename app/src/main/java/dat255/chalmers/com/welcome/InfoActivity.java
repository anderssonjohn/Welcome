package dat255.chalmers.com.welcome;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import static dat255.chalmers.com.welcome.SharedPreferencesKeys.PREFS_NAME;
import static dat255.chalmers.com.welcome.SharedPreferencesKeys.SWEDISH_SPEAKER;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        //Display different text depending on if the user is a mentor or not
        TextView info = (TextView) findViewById(R.id.infoText);
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, 0);
        Boolean mentor = prefs.getBoolean(SWEDISH_SPEAKER, false);
        if (mentor) {
            info.setText(R.string.info_sv);
        }
        else {
            info.setText(R.string.info_as);
        }
    }

    public void showGenderAndBirthActivity(View view) {
        Intent intent = new Intent(this, GenderAndBirthActivity.class);
        startActivity(intent);
    }
}
