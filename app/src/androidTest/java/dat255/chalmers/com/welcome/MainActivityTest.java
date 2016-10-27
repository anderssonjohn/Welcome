package dat255.chalmers.com.welcome;

import android.app.Activity;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.core.deps.guava.base.Preconditions.checkNotNull;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static dat255.chalmers.com.welcome.R.id.button;
import static dat255.chalmers.com.welcome.R.id.listView;
import static dat255.chalmers.com.welcome.R.id.list_item;
import static dat255.chalmers.com.welcome.R.id.radioButtonFemale;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.*;
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void currentActivityIsInstanceMainActivity() {
        Activity currentActivity = getActivityInstance();
        checkNotNull(currentActivity);
        checkNotNull(currentActivity.getClass());
        assertTrue(getActivityInstance().getClass().equals(MainActivity.class));
    }


    @Test
    public void onCreate() throws Exception {
        currentActivityIsInstanceMainActivity();
    }

    @Test
    public void testFindMentorOrStudentButton(){
        //onView(withId(button)).perform(click());
    }

    @Test
    public void deleteContactAtIndex() throws Exception {
        //onView(withId(listView)).perform(click());
    }

    @Test
    public void showProfile() throws Exception {
        //click profile icon
    }

    @Test
    public void showLanguageDialog() throws Exception {
        //click language icon
    }

    public Activity getActivityInstance() {
        return mActivityRule.getActivity();
    }

}