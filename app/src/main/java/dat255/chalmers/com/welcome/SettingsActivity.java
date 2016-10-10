package dat255.chalmers.com.welcome;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.content.DialogInterface;
import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import java.util.Locale;


public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        
    }

    public static class LanguageDialog extends android.app.DialogFragment{
        AlertDialog levelDialog;

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
                            setConfigLanguage("Sv");
                            updateActivity();
                            break;
                        case 1:
                            setConfigLanguage("En");
                            updateActivity();
                            break;
                        case 2:
                            setConfigLanguage("Pe");
                            updateActivity();
                            break;
                        case 3:
                            setConfigLanguage("Ar");
                            updateActivity();
                            break;
                    }
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

}
