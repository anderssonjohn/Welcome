package dat255.chalmers.com.welcome;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.content.DialogInterface;
import android.app.AlertDialog;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import java.util.Locale;

import static dat255.chalmers.com.welcome.SharedPreferencesKeys.INTEREST_ID;
import static dat255.chalmers.com.welcome.SharedPreferencesKeys.JOB_ID;


public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //Set the title at the top of the activity
        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.setTitle(R.string.settings);
        }
    }

    public static class LanguageDialog extends android.app.DialogFragment{
        AlertDialog levelDialog;
        String languageCode;

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState){


            // Language options in dialog with radio buttons
            final CharSequence[] items = {getString(R.string.lang_sv),getString(R.string.lang_en),
                    getString(R.string.lang_fa),getString(R.string.lang_ar)};

            // Creating and Building the Dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle(R.string.choose_language);
            builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int item) {
                    switch(item)
                    {
                        case 0:
                            languageCode = "Sv" ;
                            break;
                        case 1:
                            languageCode = "En" ;
                            break;
                        case 2:
                            languageCode = "Pe" ;
                            break;
                        case 3:
                            languageCode = "Ar" ;
                            break;
                    }
                    setConfigLanguage(languageCode);
                    updateActivity();
                    levelDialog.dismiss();
                }
            });

            levelDialog = builder.create();
            return levelDialog;
        }

        public void setConfigLanguage(String languageCode){

            Locale mLocale;
            mLocale = new Locale(languageCode);
            Locale.setDefault(mLocale);
            Configuration config = new Configuration();
            config.locale = mLocale;
            this.getResources().updateConfiguration(config,
                   this.getResources().getDisplayMetrics());

        }
        public void updateActivity(){
            Intent intent = new Intent(getActivity(),SettingsActivity.class);
            startActivity(intent);
        }

    }

    public void changeLanguageOnClick(View view){
        LanguageDialog languageDialog = new LanguageDialog();
        languageDialog.show(getFragmentManager(),"");
    }

    public static class JobDialog extends android.app.DialogFragment {
        AlertDialog levelDialog;

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            // Language options in dialog with radio buttons
            final CharSequence[] items = {"", "Pizzabagare", "Astrofysiker",
                    "Arbterslös"};

            // Creating and Building the Dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle(R.string.choose_job);
            builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int item) {
                    SharedPreferences prefs = getActivity().getPreferences(MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();

                    switch (item) {
                        case 0:
                            editor.putString(JOB_ID ,"");
                            break;
                        case 1:
                            editor.putString(JOB_ID ,"Pizzabagare");
                            break;
                        case 2:
                            editor.putString(JOB_ID ,"Astrofysiker");
                            break;
                        case 3:
                            editor.putString(JOB_ID ,"Arbterslös");
                            break;
                    }
                    editor.apply();
                    levelDialog.dismiss();
                }
            });

            levelDialog = builder.create();
            return levelDialog;
        }
    }

    public void changeJobOnClick(View view){
        JobDialog jobDialog = new JobDialog();
        jobDialog.show(getFragmentManager(),"");
    }

    public static class InterestsDialog extends android.app.DialogFragment {
        AlertDialog levelDialog;

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            // Language options in dialog with radio buttons
            final CharSequence[] items = {"", "IT", "Sport",
                    "Musik"};

            // Creating and Building the Dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle(R.string.choose_interest);
            builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int item) {
                    SharedPreferences prefs = getActivity().getPreferences(MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();

                    switch (item) {
                        case 0:
                            editor.putString(INTEREST_ID ,"");
                            break;
                        case 1:
                            editor.putString(INTEREST_ID ,"IT");
                            break;
                        case 2:
                            editor.putString(INTEREST_ID ,"Sport");
                            break;
                        case 3:
                            editor.putString(INTEREST_ID ,"Musik");
                            break;
                    }
                    editor.apply();
                    levelDialog.dismiss();
                }
            });

            levelDialog = builder.create();
            return levelDialog;
        }
    }

    public void changeInterestsOnClick(View view){
        InterestsDialog interestsDialog = new InterestsDialog();
        interestsDialog.show(getFragmentManager(),"");
    }

}
