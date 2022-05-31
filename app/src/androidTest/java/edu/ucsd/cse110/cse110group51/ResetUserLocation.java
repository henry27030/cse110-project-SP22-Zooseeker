package edu.ucsd.cse110.cse110group51;


import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import static androidx.test.InstrumentationRegistry.getInstrumentation;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;

import edu.ucsd.cse110.cse110group51.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ResetUserLocation {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void resetUserLocation() {
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
allOf(withId(R.id.mock_location), withText("Mock Location"),
childAtPosition(
childAtPosition(
withId(android.R.id.content),
0),
5),
isDisplayed()));
        materialButton2.perform(click());
        
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
        
        ViewInteraction materialButton3 = onView(
allOf(withId(R.id.Mock), withText("Mock User Location"),
childAtPosition(
allOf(withId(R.id.setCoor),
childAtPosition(
withId(android.R.id.content),
0)),
3),
isDisplayed()));
        materialButton3.perform(click());
        
        ViewInteraction materialButton4 = onView(
allOf(withId(R.id.mock_back), withText("Back"),
childAtPosition(
allOf(withId(R.id.setCoor),
childAtPosition(
withId(android.R.id.content),
0)),
0),
isDisplayed()));
        materialButton4.perform(click());
        
        ViewInteraction materialButton5 = onView(
allOf(withId(R.id.back_btn), withText("Back"),
childAtPosition(
childAtPosition(
withId(android.R.id.content),
0),
2),
isDisplayed()));
        materialButton5.perform(click());
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
                        && view.equals(((ViewGroup)parent).getChildAt(position));
            }
        };
    }
    }
