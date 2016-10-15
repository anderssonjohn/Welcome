package dat255.chalmers.com.welcome;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import dat255.chalmers.com.welcome.BackendInterfaces.CreateUser;

import static dat255.chalmers.com.welcome.SharedPreferencesKeys.PREFS_NAME;
import static dat255.chalmers.com.welcome.SharedPreferencesKeys.JOB_ID;
import static dat255.chalmers.com.welcome.SharedPreferencesKeys.INTEREST_ID;
import static dat255.chalmers.com.welcome.SharedPreferencesKeys.FIRST_RUN;
import static dat255.chalmers.com.welcome.SharedPreferencesKeys.SWEDISH_SPEAKER;
import static dat255.chalmers.com.welcome.SharedPreferencesKeys.VIEWED_MAIN;

public class JobActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_job);

        //Set the title at the top of the activity
        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.setTitle(R.string.title_getstarted);
        }

        //Display different strings depending on if the user is a mentor or not
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, 0);
        Boolean mentor = prefs.getBoolean(SWEDISH_SPEAKER, false);
        TextView jobPrompt = (TextView) findViewById(R.id.textViewjob);

        Button mButton;
        mButton = (Button) this.findViewById(R.id.buttonDone);

        if (mentor) {
            jobPrompt.setText(R.string.job_sv_prompt);
            mButton.setText(R.string.first_match_button_mentor);
        }
        else {
            jobPrompt.setText(R.string.job_as_prompt);
        }

        Spinner spinnerJ = (Spinner) findViewById(R.id.spinnerJob);
        Spinner spinnerI = (Spinner) findViewById(R.id.spinnerInterest);

        //Define our own listener that makes sure we can't progress to the next activity
        //while the empty first item is selected
        AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Button button = (Button) findViewById(R.id.buttonDone);
                if (id == 0) {
                    button.setEnabled(false);
                }
                else {
                    button.setEnabled(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };

        spinnerJ.setOnItemSelectedListener(listener);
        spinnerI.setOnItemSelectedListener(listener);

        //Populate the spinners with options via an ArrayAdapter
        ArrayAdapter<CharSequence> adapter;

        adapter = ArrayAdapter.createFromResource(this,
                R.array.job_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerJ.setAdapter(adapter);

        adapter = ArrayAdapter.createFromResource(this,
                R.array.interests_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerI.setAdapter(adapter);

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
            if (i == wizard.getIndexOf("Job")){
                button.setImageResource(R.drawable.wizardcircle2);
            }else{
                button.setImageResource(R.drawable.wizardcircle1);
            }
            wizardLayout.addView(button);
        }
    }

    private void saveInfo() {
        Spinner spinnerJ = (Spinner) findViewById(R.id.spinnerJob);
        Spinner spinnerI = (Spinner) findViewById(R.id.spinnerInterest);

        int jobID = (int) spinnerJ.getSelectedItemId();
        int interestID = (int) spinnerI.getSelectedItemId();

        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(JOB_ID, Integer.toString(jobID));
        editor.putString(INTEREST_ID, Integer.toString(interestID));
        editor.commit();
    }

    public void showMainActivity(View view) {
        //Save all data
        saveInfo();

        //makes sure that the user matches with someone directly
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(VIEWED_MAIN, true);
        editor.commit();

        new SendCreateUser().execute();

        //Save that the user has gone through the first time setup
        prefs = getSharedPreferences(PREFS_NAME, 0);
        editor = prefs.edit();
        editor.putBoolean(FIRST_RUN, false);
        editor.commit();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

        //terminates all the wizard activities when we're done with it.
        Intent terminateIntent = new Intent("terminateWizard");
        sendBroadcast(terminateIntent);
    }

    private class SendCreateUser extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            CreateUser createUser = new CreateUser(JobActivity.this);
            createUser.createUser();
            return null;
        }
    }
}
