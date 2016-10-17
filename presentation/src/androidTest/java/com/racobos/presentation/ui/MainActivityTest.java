package com.racobos.presentation.ui;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.racobos.domain.R;
import com.racobos.presentation.ui.ratecalculator.RateCalculatorActivity;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<RateCalculatorActivity> mActivityTestRule =
            new ActivityTestRule<>(RateCalculatorActivity.class);

    private IdlingResource mIdlingResource;

    @Before
    public void setUp() {
        mIdlingResource = mActivityTestRule.getActivity().getIdlingResource();
        Espresso.registerIdlingResources(mIdlingResource);
    }

    @After
    public void unregisterIdlingResource() {
        if (mIdlingResource != null) {
            Espresso.unregisterIdlingResources(mIdlingResource);
        }
    }

    @Test
    public void checkIfOriginAndDestinationSubmitMessageTest() {
        onView(withText(R.string.undestood)).perform(click());
        onView(withId(R.id.submit)).check(matches(withText(R.string.origin_can_not_be_empty)));
        onView(withId(R.id.edittext_search)).perform(typeText("alberto aguilera"));
        onView(withId(R.id.edittext_search)).perform(pressImeActionButton());
        onView(withText(R.string.origin)).perform(click());
        onView(withId(R.id.submit)).check(matches(withText(R.string.destination_can_not_be_empty)));
        onView(withId(R.id.edittext_search)).perform(typeText("cuatro caminos"));
        onView(withId(R.id.edittext_search)).perform(pressImeActionButton());
        onView(withText(R.string.destination)).perform(click());
        onView(withId(R.id.submit)).check(matches(withText(R.string.estimate)));
    }
}
