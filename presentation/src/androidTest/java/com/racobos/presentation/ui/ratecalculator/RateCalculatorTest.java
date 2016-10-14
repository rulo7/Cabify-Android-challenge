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
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
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
    public void testLoginWrong() {
        allowPermissionsIfNeeded();
        onView(withText("OK")).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.submit)).check(withText(R.string.)).perform(typeText("email@dominio.com"));
        onView(withId(R.id.password)).perform(replaceText("contraseña"));
        onView(withId(R.id.email_sign_in_button)).perform(click());
        onView(withId(R.id.password)).check(
                matches(hasErrorText(activityRule.getActivity().getString(R.string.error_incorrect_password))));
    }
}
