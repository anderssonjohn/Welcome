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
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.core.deps.guava.base.Preconditions.checkNotNull;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static dat255.chalmers.com.welcome.R.id.radioButtonFemale;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class GenderAndBirthActivityTest {

    @Rule
    public ActivityTestRule<GenderAndBirthActivity> mActivityRule = new ActivityTestRule<>(
            GenderAndBirthActivity.class);

    @Test
    public void currentActivityIsInstanceGenderAndBirthActivity() {
        Activity currentActivity = getActivityInstance();
        checkNotNull(currentActivity);
        checkNotNull(currentActivity.getClass());
        assertTrue(getActivityInstance().getClass().equals(GenderAndBirthActivity.class));
    }

    @Test
    public void testOnCreate() throws Exception {
        currentActivityIsInstanceGenderAndBirthActivity();
    }

    @Test
    public void testEnableButtonNext(){
        onView(withId(R.id.buttonNext)).check(matches(not(isEnabled())));
        testGenderButtonClick();
        onView(withId(R.id.buttonNext)).check(matches((isEnabled())));


    }
    @Test
    public void testGenderButtonEnable(){
        onView(withId(radioButtonFemale)).check(matches(isEnabled()));
        onView(withId(R.id.radioButtonMale)).check(matches(isEnabled()));
        onView(withId(R.id.radioButtonOther)).check(matches(isEnabled()));

    }

    @Test
    public void testGenderButtonClick(){
        onView(withId(radioButtonFemale)).perform(click());
        onView(withId(R.id.radioButtonMale)).perform(click());
        onView(withId(R.id.radioButtonOther)).perform(click());

    }

    public Activity getActivityInstance() {
        return mActivityRule.getActivity();
    }
}