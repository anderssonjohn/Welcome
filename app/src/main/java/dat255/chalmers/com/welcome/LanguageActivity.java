package dat255.chalmers.com.welcome;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.Locale;

import static dat255.chalmers.com.welcome.SharedPreferencesKeys.PREFS_NAME;
import static dat255.chalmers.com.welcome.SharedPreferencesKeys.LANGUAGE;

public class LanguageActivity extends AppCompatActivity {

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("terminateWizard")) {
                unregisterReceiver(broadcastReceiver);
                finish();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        //Set the title at the top of the activity
        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.setTitle(R.string.title_getstarted);
        }

        //Draw our wizard progress indicator
        drawProgressBar();

        //Sign up for termination broadcasts
        registerReceiver(broadcastReceiver, new IntentFilter("terminateWizard"));
    }

    public void drawProgressBar() {
        WizardManager wizard = WizardManager.getInstance();
        LinearLayout wizardLayout = (LinearLayout) findViewById(R.id.linLayout);

        for (int i = 0; i < wizard.getPageCount(); i++) {
            ImageButton button = new ImageButton(this);
            button.setPadding(20, 0, 20, 0);
            button.setBackgroundColor(Color.TRANSPARENT);
            if (i == wizard.getIndexOf("Language")){
                button.setImageResource(R.drawable.wizardcircle2);
            }else{
                button.setImageResource(R.drawable.wizardcircle1);
            }
            wizardLayout.addView(button);
        }
    }

    public void showInfoActivity(View view) {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = prefs.edit();
        String tag = view.getTag().toString();
        editor.putString(LANGUAGE, tag);
        editor.commit();

        updateConfigLanguage(tag);

        Intent intent = new Intent(this, MentorChoiceActivity.class);
        startActivity(intent);
    }

    public void updateConfigLanguage(String tag){
        Locale mLocale;
        mLocale = new Locale(tag);
        Locale.setDefault(mLocale);
        Configuration config = new Configuration();
        config.locale = mLocale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
    }

    @Override
    public void onBackPressed() {
        unregisterReceiver(broadcastReceiver);
        finish();
    }
}
