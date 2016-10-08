package dat255.chalmers.com.welcome;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import static dat255.chalmers.com.welcome.SharedPreferencesKeys.DOB_DAY;
import static dat255.chalmers.com.welcome.SharedPreferencesKeys.DOB_MONTH;
import static dat255.chalmers.com.welcome.SharedPreferencesKeys.DOB_YEAR;
import static dat255.chalmers.com.welcome.SharedPreferencesKeys.GENDER;
import static dat255.chalmers.com.welcome.SharedPreferencesKeys.NAME;
import static dat255.chalmers.com.welcome.SharedPreferencesKeys.PREFS_NAME;

public class GenderAndBirthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gender_and_birth);
        DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
        long eighteenYears = 568000000000L;
        datePicker.setMaxDate(System.currentTimeMillis() - eighteenYears);
        EditText editTextName = (EditText) findViewById(R.id.nameField);
        editTextName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (v.getText().toString().trim().length() > 0) {
                    RadioButton radioButtonMale = (RadioButton) findViewById(R.id.radioButtonMale);
                    RadioButton radioButtonFemale = (RadioButton) findViewById(R.id.radioButtonFemale);
                    RadioButton radioButtonOther = (RadioButton) findViewById(R.id.radioButtonOther);
                    radioButtonMale.setEnabled(true);
                    radioButtonFemale.setEnabled(true);
                    radioButtonOther.setEnabled(true);
                }
                return false;
            }
        });

        drawProgressBar();
    }

    public void drawProgressBar(){
        WizardManager wizard = WizardManager.getInstance();
        LinearLayout wizardLayout = (LinearLayout)findViewById(R.id.linLayout);

        for (int i = 0; i < wizard.getPageCount(); i++) {
            ImageButton button = new ImageButton(this);
            button.setPadding(20, 0, 20, 0);
            button.setBackgroundColor(Color.TRANSPARENT);
            if (i == wizard.getIndexOf("GenderAndBirth")){
                button.setImageResource(R.drawable.wizardcircle2);
            }else{
                button.setImageResource(R.drawable.wizardcircle1);
            }

            wizardLayout.addView(button);
        }
    }

    public void enableButtonNext(View view) {
        Button b = (Button)findViewById(R.id.buttonNext);
        b.setEnabled(true);
    }

    private void saveInfo() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = prefs.edit();

        //Save the user's name
        EditText editText = (EditText) findViewById(R.id.nameField);
        String text = editText.getText().toString();

        editor.putString(NAME, text);
        editor.apply();

        //Save the user's selected gender
        RadioGroup group = (RadioGroup) findViewById(R.id.radioGroupGender);
        RadioButton pressedButton = (RadioButton) findViewById(group.getCheckedRadioButtonId());

        editor.putString(GENDER, pressedButton.getTag().toString());
        editor.apply();

        //Save the user's selected birth date
        DatePicker date = (DatePicker) findViewById(R.id.datePicker);
        int year = date.getYear();
        int month = date.getMonth();
        int day = date.getDayOfMonth();

        editor.putInt(DOB_YEAR, year);
        editor.putInt(DOB_MONTH, month);
        editor.putInt(DOB_DAY, day);
        editor.commit();
    }

    public void showJobActivity(View view) {
        //Save all data
        saveInfo();

        //Move on the next activity
        Intent intent = new Intent(this, JobActivity.class);
        startActivity(intent);
    }
}
