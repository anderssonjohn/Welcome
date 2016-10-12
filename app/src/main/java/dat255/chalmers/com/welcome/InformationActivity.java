package dat255.chalmers.com.welcome;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import static dat255.chalmers.com.welcome.SharedPreferencesKeys.PREFS_NAME;
import static dat255.chalmers.com.welcome.SharedPreferencesKeys.SWEDISH_SPEAKER;

public class InformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        //Set the title at the top of the activity
        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.setTitle(R.string.title_getstarted);
        }

        //Draw our wizard progress indicator
        drawProgressBar();

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

    public void drawProgressBar(){
        WizardManager wizard = WizardManager.getInstance();
        LinearLayout wizardLayout = (LinearLayout) findViewById(R.id.linLayout);

        for (int i = 0; i < wizard.getPageCount(); i++) {
            ImageButton button = new ImageButton(this);
            button.setPadding(20, 0, 20, 0);
            button.setBackgroundColor(Color.TRANSPARENT);
            if (i == wizard.getIndexOf("Info")){
                button.setImageResource(R.drawable.wizardcircle2);
            }else{
                button.setImageResource(R.drawable.wizardcircle1);
            }

            wizardLayout.addView(button);
        }
    }

    public void showGenderAndBirthActivity(View view) {
        Intent intent = new Intent(this, GenderAndBirthActivity.class);
        startActivity(intent);
    }
}
