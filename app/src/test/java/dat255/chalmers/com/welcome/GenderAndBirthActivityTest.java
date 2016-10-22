package dat255.chalmers.com.welcome;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import org.junit.Test;
import java.util.regex.Pattern;

import static dat255.chalmers.com.welcome.R.string.settings;
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
         //genderAndBirthActivity.onCreate(settings);


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
        Button button;
        button = (Button) genderAndBirthActivity.findViewById(R.id.buttonNext);
        genderAndBirthActivity.enableButtonNext(genderAndBirthActivity.findViewById(R.id.buttonNext));
        assertTrue(button.isEnabled());*/



        /*GenderAndBirthActivity gb = Mockito.mock(GenderAndBirthActivity.class);
        //gb.setContentView(R.layout.activity_gender_and_birth);
        //gb.onCreate(Bundle.EMPTY);

        Button button =new Button(genderAndBirthActivity.getBaseContext());
        button.setEnabled(false);
        //button = (Button) genderAndBirthActivity.findViewById(R.id.buttonNext);
        gb.enableButtonNext(button);
        //assertTrue(button.isClickable());
       assertTrue(!button.isEnabled());
        button.setEnabled(true);
        //Button b = (Button)findViewById(R.id.buttonNext);*/


    }

    @Test
    public void testShowJobActivity() throws Exception {
            //with mock assert error
        /*GenderAndBirthActivity gb = Mockito.mock(GenderAndBirthActivity.class);
        gb.setContentView(R.layout.activity_gender_and_birth);
        JobActivity jobActivity = new JobActivity();

        JobActivity jb = Mockito.mock(JobActivity.class);
        jb.setContentView(R.layout.activity_job);

        gb.showJobActivity(jb.findViewById(R.id.activity_job));
        assertTrue(gb.getJobActivated());*/

            //without mock (nullpointer)
        //JobActivity jobActivity = new JobActivity();

        // the saveinfo() is the problem
       /* JobActivity jb = Mockito.mock(JobActivity.class);

        genderAndBirthActivity.showJobActivity(jb.findViewById(R.id.activity_job));
        assertTrue(genderAndBirthActivity.getJobActivated());*/


    }

}