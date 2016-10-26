package dat255.chalmers.com.welcome;

import android.app.Activity;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.core.deps.guava.base.Preconditions.checkNotNull;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)

@LargeTest
public class MentorChoiceActivityTest {
    private String mStringToBetyped;

    @Rule
    public ActivityTestRule<MentorChoiceActivity> mActivityRule = new ActivityTestRule<>(
            MentorChoiceActivity.class);
    @Before
    public void initValidString() {
        mStringToBetyped = "FirstName LastName";
    }

    @Test
    public void CurrentActivityIsInstanceMentorChoiceActivity() {
        Activity currentActivity = getActivityInstance();
        checkNotNull(currentActivity);
        checkNotNull(currentActivity.getClass());
        assertTrue(getActivityInstance().getClass().equals(MentorChoiceActivity.class));
    }
    @Test
    public void testOnCreate() throws Exception {
        CurrentActivityIsInstanceMentorChoiceActivity();
    }

    @Test
    public void testEnableButtonNext(){
        onView(withId(R.id.buttonIWantMentor)).check(matches(not(isEnabled())));
        onView(withId(R.id.buttonIWannaBeMentor)).check(matches(not(isEnabled())));
        onView(withId(R.id.infoButtonSv)).check(matches((isEnabled())));
        onView(withId(R.id.infoButtonAs)).check(matches((isEnabled())));

        changeText_nameField();
        //check buttons are enabled after name is written
        onView(withId(R.id.buttonIWantMentor)).check(matches((isEnabled())));
        onView(withId(R.id.buttonIWannaBeMentor)).check(matches((isEnabled())));
        onView(withId(R.id.infoButtonSv)).check(matches((isEnabled())));
        onView(withId(R.id.infoButtonAs)).check(matches((isEnabled())));


    }

    @Test
   public void changeText_nameField() {
       // Press namefield, type name
        onView(withId(R.id.nameField)).perform(click());
        onView(withId(R.id.nameField))
               .perform(typeText(mStringToBetyped), closeSoftKeyboard());


       // Check that the text in namefield was changed.
       onView(withId(R.id.nameField))
               .check(matches(withText(mStringToBetyped)));
   }


    @Test
    public void testMentorSelected() throws Exception {
        onView(withId(R.id.buttonIWannaBeMentor)).perform(click());
        //change intent
    }

    @Test
    public void testAsylumSeekerSelected() throws Exception {
        onView(withId(R.id.buttonIWantMentor)).perform(click());
        //change intent
    }

    @Test
    public void showInformationDialogAs() throws Exception {
        onView(withId(R.id.infoButtonAs)).perform(click());
    }

    @Test
    public void showInformationDialogSv() throws Exception {
        onView(withId(R.id.infoButtonSv)).perform(click());

    }
    
    public Activity getActivityInstance() {
        return mActivityRule.getActivity();
    }
}