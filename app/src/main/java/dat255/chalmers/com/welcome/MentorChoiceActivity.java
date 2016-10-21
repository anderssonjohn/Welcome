package dat255.chalmers.com.welcome;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import static dat255.chalmers.com.welcome.SharedPreferencesKeys.NAME;
import static dat255.chalmers.com.welcome.SharedPreferencesKeys.SWEDISH_SPEAKER;
import static dat255.chalmers.com.welcome.SharedPreferencesKeys.PREFS_NAME;

public class MentorChoiceActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_mentor_choice);

        //Set the title at the top of the activity
        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.setTitle(R.string.title_getstarted);
        }

        //Draw our wizard progress indicator
        drawProgressBar();

        //Sign up for termination broadcasts
        registerReceiver(broadcastReceiver, new IntentFilter("terminateWizard"));

        final EditText editTextName = (EditText) findViewById(R.id.nameField);
        editTextName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                Button buttonIWannaBeMentor = (Button) findViewById(R.id.buttonIWannaBeMentor);
                Button buttonIWantMentor = (Button) findViewById(R.id.buttonIWantMentor);
                if (editTextName.getText().toString().trim().length() > 0) {
                    buttonIWannaBeMentor.setEnabled(true);
                    buttonIWantMentor.setEnabled(true);
                }
                else {
                    buttonIWannaBeMentor.setEnabled(false);
                    buttonIWantMentor.setEnabled(false);
                }
            }
        });
    }

    public void drawProgressBar() {
        WizardManager wizard = WizardManager.getInstance();
        LinearLayout wizardLayout = (LinearLayout) findViewById(R.id.linLayout);

        for (int i = 0; i < wizard.getPageCount(); i++) {
            ImageButton button = new ImageButton(this);
            button.setPadding(20, 0, 20, 0);
            button.setBackgroundColor(Color.TRANSPARENT);
            if (i == wizard.getIndexOf("MentorChoice")){
                button.setImageResource(R.drawable.wizardcircle2);
            }else{
                button.setImageResource(R.drawable.wizardcircle1);
            }
            wizardLayout.addView(button);
        }
    }

    public void mentorSelected(View view) {
        saveInfo(true);
        showGenderAndBirthActivity();
    }

    public void asylumSeekerSelected(View view) {
        saveInfo(false);
        showGenderAndBirthActivity();
    }

    private void saveInfo(boolean isMentor) {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = prefs.edit();

        //Save the user's name
        EditText editText = (EditText) findViewById(R.id.nameField);
        String text = editText.getText().toString();
        editor.putString(NAME, text);
        editor.apply();

        //save mentor choice
        editor.putBoolean(SWEDISH_SPEAKER, isMentor);
        editor.commit();
    }

    private void showGenderAndBirthActivity() {
        Intent intent = new Intent(this, GenderAndBirthActivity.class);
        startActivity(intent);
    }

    public void showInformationDialogAs(View view) {
        InformationDialog dialog = new InformationDialog();
        Bundle bunbun = new Bundle();
        bunbun.putBoolean("mentorQuestion", false);
        dialog.setArguments(bunbun);
        dialog.show(getFragmentManager(), "");
    }
    
    public void showInformationDialogSv(View view) {
        InformationDialog dialog = new InformationDialog();
        Bundle bunbun = new Bundle();
        bunbun.putBoolean("mentorQuestion", true);
        dialog.setArguments(bunbun);
        dialog.show(getFragmentManager(), "");
    }

    //A dialog that will be displayed the first time the user finds a match
    public static class InformationDialog extends DialogFragment {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            if(getArguments().getBoolean("mentorQuestion")) {
                builder.setTitle(R.string.info_sv_title);
                builder.setMessage(R.string.info_sv);
            } else {
                builder.setTitle(R.string.info_as_title);
                builder.setMessage(R.string.info_as);
            }
            builder.setNegativeButton(R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //Back out
                }
            });

            return builder.create();
        }
    }
}
