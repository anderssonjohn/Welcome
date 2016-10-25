package dat255.chalmers.com.welcome;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class GenderAndBirthActivityTest {

    @Rule
    public ActivityTestRule<GenderAndBirthActivity> mActivityRule = new ActivityTestRule<>(
            GenderAndBirthActivity.class);

    @Test
    public void enableButtonNext(){
        onView(withId(R.id.buttonNext)).check(matches(not(isEnabled())));
    }

}