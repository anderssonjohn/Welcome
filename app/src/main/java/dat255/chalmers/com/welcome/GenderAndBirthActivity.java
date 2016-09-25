package dat255.chalmers.com.welcome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GenderAndBirthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gender_and_birth);
    }

    public void enableButtonNext(View view) {
        Button b = (Button)findViewById(R.id.buttonNext);
        b.setClickable(true);
    }
}
