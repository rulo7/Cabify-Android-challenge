package com.racobos.presentation.ui;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.racobos.domain.R;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    private IdlingResource mIdlingResource;

    private static Matcher<View> childAtPosition(final Matcher<View> parentMatcher, final int position) {
        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent) && view.equals(
                        ((ViewGroup) parent).getChildAt(position));
            }
        };
    }

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
    public void mainActivityTest() {
        ViewInteraction appCompatButton = onView(allOf(withId(android.R.id.button1), withText("Ok"), withParent(
                allOf(withClassName(is("com.android.internal.widget.ButtonBarLayout")),
                        withParent(withClassName(is("android.widget.LinearLayout"))))), isDisplayed()));
        appCompatButton.perform(click());
        ViewInteraction textView = onView(allOf(withId(R.id.submit), withText("You have to select the origin route"),
                childAtPosition(childAtPosition(withId(android.R.id.content), 0), 3), isDisplayed()));
        textView.check(matches(withText("You have to select the origin route")));
        ViewInteraction appCompatAutoCompleteTextView = onView(allOf(withId(R.id.edittext_search), isDisplayed()));
        appCompatAutoCompleteTextView.perform(replaceText("calle alcala"), closeSoftKeyboard());
        ViewInteraction appCompatAutoCompleteTextView2 =
                onView(allOf(withId(R.id.edittext_search), withText("calle alcala"), isDisplayed()));
        appCompatAutoCompleteTextView2.perform(pressImeActionButton());
        ViewInteraction appCompatTextView = onView(allOf(withId(android.R.id.text1), withText("Origin"),
                childAtPosition(allOf(withClassName(is("com.android.internal.app.AlertController$RecycleListView")),
                        withParent(withClassName(is("android.widget.FrameLayout")))), 0), isDisplayed()));
        appCompatTextView.perform(click());
        ViewInteraction appCompatImageView = onView(allOf(withId(R.id.imageview_icon_search), isDisplayed()));
        appCompatImageView.perform(click());
        ViewInteraction appCompatAutoCompleteTextView3 = onView(allOf(withId(R.id.edittext_search), isDisplayed()));
        appCompatAutoCompleteTextView3.perform(click());
        ViewInteraction appCompatAutoCompleteTextView4 = onView(allOf(withId(R.id.edittext_search), isDisplayed()));
        appCompatAutoCompleteTextView4.perform(click());
        ViewInteraction appCompatAutoCompleteTextView5 = onView(allOf(withId(R.id.edittext_search), isDisplayed()));
        appCompatAutoCompleteTextView5.perform(replaceText("alberto "), closeSoftKeyboard());
        pressBack();
        ViewInteraction textView2 =
                onView(allOf(withId(R.id.submit), withText("You have to select the destination route"),
                        childAtPosition(childAtPosition(withId(android.R.id.content), 0), 3), isDisplayed()));
        textView2.check(matches(withText("You have to select the destination route")));
        ViewInteraction appCompatAutoCompleteTextView6 =
                onView(allOf(withId(R.id.edittext_search), withText("alberto "), isDisplayed()));
        appCompatAutoCompleteTextView6.perform(replaceText("alberto aguilera"), closeSoftKeyboard());
        ViewInteraction twoLineListItem =
                onView(allOf(withClassName(is("android.widget.TwoLineListItem")), isDisplayed()));
        twoLineListItem.perform(click());
        ViewInteraction appCompatTextView2 = onView(allOf(withId(android.R.id.text1), withText("Destination"),
                childAtPosition(allOf(withClassName(is("com.android.internal.app.AlertController$RecycleListView")),
                        withParent(withClassName(is("android.widget.FrameLayout")))), 1), isDisplayed()));
        appCompatTextView2.perform(click());
        ViewInteraction textView3 = onView(allOf(withId(R.id.submit), withText("Estimate"),
                childAtPosition(childAtPosition(withId(android.R.id.content), 0), 3), isDisplayed()));
        textView3.check(matches(withText("Estimate")));
        ViewInteraction appCompatTextView3 = onView(allOf(withId(R.id.submit), withText("Estimate"), isDisplayed()));
        appCompatTextView3.perform(click());
        ViewInteraction appCompatButton2 = onView(allOf(withId(android.R.id.button2), withText("As soon as posible"),
                withParent(allOf(withClassName(is("com.android.internal.widget.ButtonBarLayout")),
                        withParent(withClassName(is("android.widget.LinearLayout"))))), isDisplayed()));
        appCompatButton2.perform(click());
    }
}
