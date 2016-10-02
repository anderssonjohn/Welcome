package dat255.chalmers.com.welcome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class InfoSvActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_sv);
    }

    public void showGenderAndBirthActivity(View view) {
        Intent intent = new Intent(this, GenderAndBirthActivity.class);
        startActivity(intent);
    }
}
