package com.racobos.presentation.ui;

import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.racobos.domain.R;
import com.racobos.presentation.TestTools;
import com.racobos.presentation.ui.journeyslist.JourneysListActivity;
import com.racobos.presentation.ui.ratecalculator.RateCalculatorActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtraWithKey;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.racobos.presentation.ui.journeyslist.JourneysListActivity.JOURNEYS_KEY;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;

@RunWith(AndroidJUnit4.class)
public class EstimateRouteActivityTest {

    @Rule
    public ActivityTestRule<RateCalculatorActivity> activityTestRule =
            new ActivityTestRule<>(RateCalculatorActivity.class, true, false);

    private IdlingResource mIdlingResource;

    @Before
    public void setUp() {
        mIdlingResource = activityTestRule.getActivity().getIdlingResource();
        Espresso.registerIdlingResources(mIdlingResource);
    }

    @Before
    public void acceptLocationPermissions() throws IOException {
        TestTools.acceptPermissions("android.permission.ACCESS_COARSE_LOCATION");
        TestTools.acceptPermissions("android.permission.ACCESS_FINE_LOCATION");
        Intents.init();
        activityTestRule.launchActivity(new Intent());
    }

    @After
    public void unregisterIdlingResource() {
        if (mIdlingResource != null) {
            Espresso.unregisterIdlingResources(mIdlingResource);
        }
        Intents.release();
    }

    @Test
    public void checkIfOriginAndDestinationSubmitMessageAreCorrectTest() {
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

    @Test
    public void checkIfJourneyListActivityHasBeenLaunchedInHappyCaseTest() {
        onView(withText(R.string.undestood)).perform(click());
        onView(withId(R.id.submit)).check(matches(withText(R.string.origin_can_not_be_empty)));
        onView(withId(R.id.edittext_search)).perform(typeText("alberto aguilera"));
        onView(withId(R.id.edittext_search)).perform(pressImeActionButton());
        onView(withText(R.string.origin)).perform(click());
        onView(withId(R.id.edittext_search)).perform(typeText("cuatro caminos"));
        onView(withId(R.id.edittext_search)).perform(pressImeActionButton());
        onView(withText(R.string.destination)).perform(click());
        onView(withId(R.id.submit)).perform(click());
        onView(withText(R.string.set_start_time)).check(matches(isDisplayed()));
        onView(withText(R.string.asap)).perform(click());
        intended(allOf(hasComponent(JourneysListActivity.class.getName()), hasExtraWithKey(JOURNEYS_KEY)));
    }

    @Test
    public void checkIfJourneyListActivityHasBeenLaunchedWhenInvalidRouteCaseTest() {
        onView(withText(R.string.undestood)).perform(click());
        onView(withId(R.id.submit)).check(matches(withText(R.string.origin_can_not_be_empty)));
        onView(withId(R.id.edittext_search)).perform(typeText("unusual destination"));
        onView(withId(R.id.edittext_search)).perform(pressImeActionButton());
        onView(withText(R.string.origin)).perform(click());
        onView(withId(R.id.edittext_search)).perform(typeText("cuatro caminos"));
        onView(withId(R.id.edittext_search)).perform(pressImeActionButton());
        onView(withText(R.string.destination)).perform(click());
        onView(withId(R.id.submit)).perform(click());
        onView(withText(R.string.set_start_time)).check(matches(isDisplayed()));
        onView(withText(R.string.asap)).perform(click());
        onView(withText(R.string.unavalaible_service_under_selected_places)).inRoot(withDecorView(not(is(activityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }
}
