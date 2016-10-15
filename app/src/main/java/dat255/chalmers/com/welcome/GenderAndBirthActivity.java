package dat255.chalmers.com.welcome;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import static dat255.chalmers.com.welcome.SharedPreferencesKeys.DOB_DAY;
import static dat255.chalmers.com.welcome.SharedPreferencesKeys.DOB_MONTH;
import static dat255.chalmers.com.welcome.SharedPreferencesKeys.DOB_YEAR;
import static dat255.chalmers.com.welcome.SharedPreferencesKeys.GENDER;
import static dat255.chalmers.com.welcome.SharedPreferencesKeys.NAME;
import static dat255.chalmers.com.welcome.SharedPreferencesKeys.PREFS_NAME;

public class GenderAndBirthActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_gender_and_birth);

        //Set the title at the top of the activity
        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.setTitle(R.string.title_getstarted);
        }

        DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
        final long EIGHTEEN_YEARS = 568000000000L;
        datePicker.setMaxDate(System.currentTimeMillis() - EIGHTEEN_YEARS);
        final EditText editTextName = (EditText) findViewById(R.id.nameField);
        editTextName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                RadioButton radioButtonMale = (RadioButton) findViewById(R.id.radioButtonMale);
                RadioButton radioButtonFemale = (RadioButton) findViewById(R.id.radioButtonFemale);
                RadioButton radioButtonOther = (RadioButton) findViewById(R.id.radioButtonOther);
                if (editTextName.getText().toString().trim().length() > 0) {
                    radioButtonMale.setEnabled(true);
                    radioButtonFemale.setEnabled(true);
                    radioButtonOther.setEnabled(true);
                }
                else {
                    radioButtonMale.setEnabled(false);
                    radioButtonFemale.setEnabled(false);
                    radioButtonOther.setEnabled(false);
                }
            }
        });

        //Draw our wizard progress indicator
        drawProgressBar();

        //Sign up for termination broadcasts
        registerReceiver(broadcastReceiver, new IntentFilter("terminateWizard"));
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
