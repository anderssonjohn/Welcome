package dat255.chalmers.com.welcome;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class JobActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job);

        Spinner spinnerJ = (Spinner) findViewById(R.id.spinnerJob);
        Spinner spinnerI = (Spinner) findViewById(R.id.spinnerInterest);

        //Populate the spinners with options via an ArrayAdapter
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.job_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerJ.setAdapter(adapter);

        adapter = ArrayAdapter.createFromResource(this,
                R.array.interests_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerI.setAdapter(adapter);
    }

    private void saveInfo() {
        Spinner spinnerJ = (Spinner) findViewById(R.id.spinnerJob);
        Spinner spinnerI = (Spinner) findViewById(R.id.spinnerInterest);

        long jobID = spinnerJ.getSelectedItemId();
        long interestID = spinnerI.getSelectedItemId();
        System.out.println(jobID);
        System.out.println(interestID);

        SharedPreferences prefs = getSharedPreferences(MainActivity.PREFS_NAME, 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putLong("JobID", jobID);
        editor.putLong("InterestID", interestID);
        editor.commit();
    }

    public void showMainActivity(View view) {
        //Save all data
        saveInfo();

        //Save that the user has gone through the first time setup
        SharedPreferences prefs = getSharedPreferences(MainActivity.PREFS_NAME, 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("FirstRun", false);
        editor.commit();

        Intent intent = new Intent(JobActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
