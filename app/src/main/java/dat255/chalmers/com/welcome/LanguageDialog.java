package dat255.chalmers.com.welcome;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import java.util.Locale;

public class LanguageDialog extends android.app.DialogFragment{
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
                changeTOChosenLanguage(item);
                setConfigLanguage(languageCode);
                updateActivity();
                levelDialog.dismiss();
            }
        });

        levelDialog = builder.create();
        return levelDialog;
    }

    public void changeTOChosenLanguage(int item){
        switch(item)
        {
            case 0:
                languageCode = "Sv" ;
                break;
            case 1:
                languageCode = "En" ;
                break;
            case 2:
                languageCode = "Fa" ;
                break;
            case 3:
                languageCode = "Ar" ;
                break;
            default:
                break;
        }

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
        Intent intent = new Intent(getActivity(),MainActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
}
