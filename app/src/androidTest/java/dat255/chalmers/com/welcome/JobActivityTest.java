package dat255.chalmers.com.welcome;

import android.app.Activity;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.core.deps.guava.base.Preconditions.checkNotNull;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class JobActivityTest {

    @Rule
    public ActivityTestRule<JobActivity> mActivityRule = new ActivityTestRule<>(
            JobActivity.class);

    @Test
    public void currentActivityIsInstanceJobActivity() {
        Activity currentActivity = getActivityInstance();
        checkNotNull(currentActivity);
        checkNotNull(currentActivity.getClass());
        assertTrue(getActivityInstance().getClass().equals(JobActivity.class));
    }
    @Test
    public void testOnCreate() throws Exception {
        currentActivityIsInstanceJobActivity();

    }

    @Test
    public void testIntressetChoice(){
        onView(withId(R.id.spinnerInterest)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Arts and entertainment"))).perform(click());
        onView(withId(R.id.spinnerInterest)).check(matches(withSpinnerText(containsString("Arts and entertainment"))));
        // test all intrest
    }

    @Test
    public void testEnableButtonNext(){
        onView(withId(R.id.buttonDone)).check(matches(not(isEnabled())));
        testJobChoice();
        onView(withId(R.id.buttonDone)).check(matches((isEnabled())));
    }

    public void testJobChoice() {
        onView(withId(R.id.spinnerJob)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Administration"))).perform(click());
        onView(withId(R.id.spinnerJob)).check(matches(withSpinnerText(containsString("Administration"))));
        //test all the buttons

    }

    public Activity getActivityInstance() {
        return mActivityRule.getActivity();
    }
}