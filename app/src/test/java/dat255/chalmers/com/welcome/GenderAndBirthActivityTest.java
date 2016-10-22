package dat255.chalmers.com.welcome;

import android.view.View;
import android.widget.Button;
import org.junit.Test;
import java.util.regex.Pattern;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static dat255.chalmers.com.welcome.R.string.gender;
import static org.junit.Assert.*;


public class GenderAndBirthActivityTest {
    //@RunWith(AndroidJUnit4.class)

    GenderAndBirthActivity genderAndBirthActivity;
    @Before
    public void setUp(){
         genderAndBirthActivity = new GenderAndBirthActivity();
        //genderAndBirthActivity.onCreate(null);


    }

    @Test
    public void onCreate() throws Exception {

    }

    @Test
    public void drawProgressBar() throws Exception {

    }

    @Test
    public void testEnableButtonNext() throws Exception {
        /*genderAndBirthActivity.findViewById(R.id.buttonNext).setEnabled(false);
        genderAndBirthActivity.enableButtonNext(genderAndBirthActivity.findViewById(R.id.buttonNext));
        assertTrue();*/
        GenderAndBirthActivity gb = Mockito.mock(GenderAndBirthActivity.class);
        //gb.onCreate(null);
        Button button;
        button = (Button) gb.findViewById(R.id.buttonNext);
        //button.setEnabled(true);
        /*if(button.isEnabled()){
            System.out.print("OH YAY ITS NOT ENABLED");
        }*/
        
        gb.enableButtonNext(button);
        assertTrue(button.isClickable());
        //assertEquals(button.isEnabled(),true);
        //Button b = (Button)findViewById(R.id.buttonNext);*/


    }

    @Test
    public void showJobActivity() throws Exception {

    }

}