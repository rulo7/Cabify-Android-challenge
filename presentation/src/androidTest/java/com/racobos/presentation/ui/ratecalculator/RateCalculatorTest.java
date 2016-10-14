package com.racobos.presentation.ui.ratecalculator;

import android.os.Build;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import com.racobos.domain.R;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import timber.log.Timber;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by raulcobos on 14/10/16.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class RateCalculatorTest {
    @Rule
    public ActivityTestRule<RateCalculatorActivity> mActivityRule =
            new ActivityTestRule<>(RateCalculatorActivity.class);

    private static void allowPermissionsIfNeeded() {
        if (Build.VERSION.SDK_INT >= 23) {
            UiDevice device = UiDevice.getInstance(getInstrumentation());
            UiObject allowPermissions = device.findObject(new UiSelector().text("Allow"));
            if (allowPermissions.exists()) {
                try {
                    allowPermissions.click();
                } catch (UiObjectNotFoundException e) {
                    Timber.e(e, "There is no permissions dialog to interact with ");
                }
            }
        }
    }

    @Test
    public void testCalculateRightRoute() {
        allowPermissionsIfNeeded();
        onView(withText(R.string.undestood)).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.submit)).check(matches(withText(R.string.origin_can_not_be_empty)));
        // TODO : set origin (Alberto Aguilera)
        onView(withId(R.id.submit)).check(matches(withText(R.string.destination_can_not_be_empty)));
        // TODO : set destination (Calle Witerico)
        onView(withId(R.id.submit)).check(matches(withText(R.string.estimate))).perform(click());
        onView(withText(R.string.asap)).check(matches(isDisplayed())).perform(click());
        // TODO:check if go to JourneyListActiviy
    }
}
