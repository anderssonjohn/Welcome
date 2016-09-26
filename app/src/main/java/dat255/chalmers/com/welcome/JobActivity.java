package dat255.chalmers.com.welcome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
}
