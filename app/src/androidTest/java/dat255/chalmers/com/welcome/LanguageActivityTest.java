package dat255.chalmers.com.welcome;

import org.junit.Test;

import android.app.Activity;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.KeyEvent;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressKey;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.core.deps.guava.base.Preconditions.checkNotNull;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LanguageActivityTest {

    @Rule
    public ActivityTestRule<LanguageActivity> mActivityRule = new ActivityTestRule<>(
            LanguageActivity.class);

    @Test
    public void testLanguageButtons(){
        onView(withId(R.id.buttonSv)).check(matches(isEnabled()));
        onView(withId(R.id.buttonEn)).check(matches(isEnabled()));
        onView(withId(R.id.buttonPe)).check(matches(isEnabled()));
        onView(withId(R.id.buttonAr)).check(matches(isEnabled()));
    }

    @Test
    public void CurrentActivityIsInstanceLanguageActivity() {
        Activity currentActivity = getActivityInstance();
        checkNotNull(currentActivity);
        checkNotNull(currentActivity.getClass());
        assertTrue(getActivityInstance().getClass().equals(LanguageActivity.class));

    }

    @Test
    public void testOnCreate() throws Exception {
       CurrentActivityIsInstanceLanguageActivity();
    }


    @Test
    public void showInfoActivity() throws Exception {
        //onView(withId(R.id.buttonSv)).perform(click());
    }

    @Test
    public void updateConfigLanguage() throws Exception {
        //onView(withId(R.id.buttonSv)).perform(click());
      //mActivityRule.getClass();
    }

    @Test
    public void onBackPressed() throws Exception {
        //onView(withId(KeyEvent.KEYCODE_BACK)).perform(click());
        //assertTrue( getActivityInstance().equals(null));
    }

    public Activity getActivityInstance() {
        return mActivityRule.getActivity();
    }
}