package edu.ucsd.cse110.cse110group51;


import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class NextTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void nextTest() {
        ViewInteraction materialButton = onView(
                allOf(withId(R.id.plan_btn), withText("Plan"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        materialButton.perform(click());

        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.Clear), withText("Clear"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                6),
                        isDisplayed()));
        materialButton2.perform(click());

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.mock_location), withText("Mock Location"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        materialButton3.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.lat),
                        childAtPosition(
                                allOf(withId(R.id.setCoor),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("32.73459618734685"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.lng),
                        childAtPosition(
                                allOf(withId(R.id.setCoor),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                2),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("-117.14936"), closeSoftKeyboard());

        ViewInteraction materialButton4 = onView(
                allOf(withId(R.id.Mock), withText("Mock User Location"),
                        childAtPosition(
                                allOf(withId(R.id.setCoor),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                3),
                        isDisplayed()));
        materialButton4.perform(click());

        ViewInteraction materialButton5 = onView(
                allOf(withId(R.id.mock_back), withText("Back"),
                        childAtPosition(
                                allOf(withId(R.id.setCoor),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                0),
                        isDisplayed()));
        materialButton5.perform(click());

        ViewInteraction materialButton6 = onView(
                allOf(withId(R.id.back_btn), withText("Back"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        materialButton6.perform(click());

        DataInteraction materialTextView = onData(anything())
                .inAdapterView(allOf(withId(R.id.list_view),
                        childAtPosition(
                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                2)))
                .atPosition(1);
        materialTextView.perform(click());

        ViewInteraction materialCheckBox = onView(
                allOf(withId(R.id.completed),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.exhibit_list_items),
                                        0),
                                1),
                        isDisplayed()));
        materialCheckBox.perform(click());

        ViewInteraction materialButton7 = onView(
                allOf(withId(R.id.finish_btn), withText("Back"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        materialButton7.perform(click());

        DataInteraction materialTextView2 = onData(anything())
                .inAdapterView(allOf(withId(R.id.list_view),
                        childAtPosition(
                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                2)))
                .atPosition(10);
        materialTextView2.perform(click());

        ViewInteraction materialCheckBox2 = onView(
                allOf(withId(R.id.completed),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.exhibit_list_items),
                                        0),
                                1),
                        isDisplayed()));
        materialCheckBox2.perform(click());

        ViewInteraction materialButton8 = onView(
                allOf(withId(R.id.finish_btn), withText("Back"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        materialButton8.perform(click());

        ViewInteraction materialButton9 = onView(
                allOf(withId(R.id.plan_btn), withText("Plan"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        materialButton9.perform(click());

        ViewInteraction materialButton10 = onView(
                allOf(withId(R.id.plan_display), withText("Plan Display"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        materialButton10.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(android.R.id.text1), withText("Walk 2200.0 ft along Terrace Lagoon Loop from Front Street / Terrace Lagoon Loop (South) to Koi Fish"),
                        withParent(allOf(withId(R.id.directions_view),
                                withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class)))),
                        isDisplayed()));
        textView.check(matches(withText("Walk 2200.0 ft along Terrace Lagoon Loop from Front Street / Terrace Lagoon Loop (South) to Koi Fish")));

        ViewInteraction materialButton11 = onView(
                allOf(withId(R.id.plan_next_button), withText("Next"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        materialButton11.perform(click());

        ViewInteraction textView2 = onView(
                allOf(withId(android.R.id.text1), withText("Walk 1100.0 ft along Hippo Trail from Hippos to Crocodiles"),
                        withParent(allOf(withId(R.id.next_directions_view),
                                withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class)))),
                        isDisplayed()));
        textView2.check(matches(withText("Walk 1100.0 ft along Hippo Trail from Hippos to Crocodiles")));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
