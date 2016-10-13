package dat255.chalmers.com.welcome;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import static dat255.chalmers.com.welcome.SharedPreferencesKeys.SWEDISH_SPEAKER;
import static dat255.chalmers.com.welcome.SharedPreferencesKeys.PREFS_NAME;

public class MentorChoiceActivity extends AppCompatActivity {

    private static boolean isMentor;

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
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, 0);
        isMentor = prefs.getBoolean(SWEDISH_SPEAKER, true);
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

    public void informationSv(View view) {
        InformationDialog dialog = new InformationDialog();
        dialog.show(getFragmentManager(), "");
    }

    public void mentorSelected(View view) {
        saveMentorState(true);
        showInfoActivity();
    }

    public void asylumSeekerSelected(View view) {
        saveMentorState(false);
        showInfoActivity();
    }

    private void saveMentorState(boolean boo) {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putBoolean(SWEDISH_SPEAKER, boo);
        editor.commit();
    }

    private void showInfoActivity() {
        Intent intent = new Intent(this, InformationActivity.class);
        startActivity(intent);
    }

    //A dialog that will be displayed the first time the user finds a match
    public static class InformationDialog extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle(R.string.first_match_title);
            builder.setMessage(R.string.info_sv);
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
