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
public class GenderAndBirthActivityTest {

    @Rule
    public ActivityTestRule<GenderAndBirthActivity> mActivityRule = new ActivityTestRule<>(
            GenderAndBirthActivity.class);



   /* @Test
    public void changeText_nameField() {
        // Type text and then press the button.
        onView(withId(R.id.editTextUserInput))
                .perform(typeText(mStringToBetyped), closeSoftKeyboard());
        onView(withId(R.id.changeTextBt)).perform(click());

        // Check that the text was changed.
        onView(withId(R.id.textToBeChanged))
                .check(matches(withText(mStringToBetyped)));
    }*/



    @Test
    public void enableButtonNext(){
        onView(withId(R.id.buttonNext)).check(matches(not(isEnabled())));
        onView(withId(R.id.buttonNext)).check(matches(not(isEnabled())));

    }
    @Test
    public void CurrentActivityIsInstanceGenderAndBirthActivity() {
        Activity currentActivity = getActivityInstance();
        checkNotNull(currentActivity);
        checkNotNull(currentActivity.getClass());
        assertTrue(getActivityInstance().getClass().equals(GenderAndBirthActivity.class));
    }
    @Test
    public void testOnCreate() throws Exception {
        CurrentActivityIsInstanceGenderAndBirthActivity();
    }

    public Activity getActivityInstance() {
        return mActivityRule.getActivity();
    }
}