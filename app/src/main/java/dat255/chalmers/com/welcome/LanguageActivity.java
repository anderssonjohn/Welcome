package dat255.chalmers.com.welcome;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import static dat255.chalmers.com.welcome.SharedPreferencesKeys.PREFS_NAME;
import static dat255.chalmers.com.welcome.SharedPreferencesKeys.LANGUAGE;

public class LanguageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
    }

    public void showInfoActivity(View view) {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = prefs.edit();
        String tag = view.getTag().toString();
        editor.putString(LANGUAGE, tag);
        editor.commit();
        Intent intent = new Intent(this, MentorChoiceActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        //Do nothing
    }
}
