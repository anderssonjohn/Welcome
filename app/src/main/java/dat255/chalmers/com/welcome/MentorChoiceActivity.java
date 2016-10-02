package dat255.chalmers.com.welcome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MentorChoiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor_choice);
    }

    public void showInfoSvActivity(View view) {
        Intent intent = new Intent(this, InfoSvActivity.class);
        startActivity(intent);
    }

    public void showInfoAsActivity(View view) {
        Intent intent = new Intent(this, InfoAsActivity.class);
        startActivity(intent);
    }
}
