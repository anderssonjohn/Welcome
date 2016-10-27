package dat255.chalmers.com.welcome;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

import static org.junit.Assert.*;


public class WizardManagerTest {
    WizardManager wizardManager;

    @Before
    public void setUp(){
        wizardManager = WizardManager.getInstance();

    }

    @Test
    public void testGetInstance() throws Exception {
        WizardManager instance = WizardManager.getInstance();
        if( instance.getClass().equals(WizardManager.class)){
            assertTrue(true);
        }else{
            assertTrue(false);
        }
    }

    @Test
    public void testGetIndexOf() throws Exception {

        int mentorChoiceIndex = wizardManager.getIndexOf("MentorChoice");
        int genderAndBirthIndex = wizardManager.getIndexOf("GenderAndBirth");
        int jobIndex = wizardManager.getIndexOf("job");

        assertEquals(0,mentorChoiceIndex);
        assertEquals(1,genderAndBirthIndex);
        assertEquals(-1,jobIndex);
    }

    @Test
    public void testGetPageCount() throws Exception {
        ArrayList<String> stepList = wizardManager.getStepsList();
        stepList.add(0,"zero");
        stepList.add(1,"one");
        stepList.add(2,"two");
        int expectedSize = stepList.size();
        int actualSize = wizardManager.getPageCount();
        assertEquals(expectedSize, actualSize);

    }

}