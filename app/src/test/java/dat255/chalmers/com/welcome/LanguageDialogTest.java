package dat255.chalmers.com.welcome;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LanguageDialogTest {

    LanguageDialog languageDialog;

    @Before
    public void setUp(){
        languageDialog = new LanguageDialog();

    }

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