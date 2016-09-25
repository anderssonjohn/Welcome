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

        Spinner jobspinner = (Spinner) findViewById(R.id.jobspinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterJ = ArrayAdapter.createFromResource(this,
                R.array.job_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterJ.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        jobspinner.setAdapter(adapterJ);

        Spinner interestspinner = (Spinner) findViewById(R.id.spinnerInterest);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterI = ArrayAdapter.createFromResource(this,
                R.array.interests_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterI.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        interestspinner.setAdapter(adapterI);
    }
}
