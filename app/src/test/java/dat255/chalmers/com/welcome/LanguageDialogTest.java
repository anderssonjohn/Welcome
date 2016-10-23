package dat255.chalmers.com.welcome;

import org.junit.Before;
import org.junit.Test;
import org.mockito.cglib.core.Local;

import android.content.res.Configuration;
import android.os.Bundle;

import java.util.Locale;

import static org.junit.Assert.*;

public class LanguageDialogTest {

    LanguageDialog languageDialog;

    @Before
    public void setUp(){
        languageDialog = new LanguageDialog();

    }

    @Test
    public void onCreateDialog() throws Exception {

    }

    @Test
    public void testSetConfigLanguage() throws Exception {
        /*languageDialog.setConfigLanguage(languageDialog.languageCode);
        String currentLanguage= Locale.getDefault().getLanguage();
        assertEquals("Sv", currentLanguage);*/

    }

    @Test
    public void testUpdateActivity() throws Exception {
    //languageDialog.updateActivity();
        //assertEquals(MainActivity.class, this.getClass());
        //languageDialog.getActivity()

    }

    /*@Test
    public void testUpdateLanguage() throws Exception {
        Locale currentLocale = languageDialog.updateLanguage("Sv");
        assertEquals(Locale.getDefault().getLanguage(), currentLocale.getDefault().getLanguage());
    }*/

    @Test
    public void testchangeTOChosenLanguage(){
        //set language code to null
        languageDialog.languageCode = "null";

        //changeLanguage to Swedish
        languageDialog.changeTOChosenLanguage(0);
        String expectedSwedishCode = "Sv";

        assertEquals(expectedSwedishCode,languageDialog.languageCode);
        //change to English
        languageDialog.changeTOChosenLanguage(1);
        String expectedEnglishCode = "En";

        assertEquals(expectedEnglishCode,languageDialog.languageCode);
        //change to Farsi
        languageDialog.changeTOChosenLanguage(2);
        String expectedFarsiCode = "Fa";

        assertEquals(expectedFarsiCode,languageDialog.languageCode);
        //change to Arabic
        languageDialog.changeTOChosenLanguage(3);
        String expectedArabicCode = "Ar";

        assertEquals(expectedArabicCode,languageDialog.languageCode);

    }

}