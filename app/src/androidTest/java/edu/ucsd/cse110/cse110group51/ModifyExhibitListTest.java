package edu.ucsd.cse110.cse110group51;


import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
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
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ModifyExhibitListTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void AddAndDeleteExhibitTest() {
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

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.finish_btn), withText("Back"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        materialButton.perform(click());

        DataInteraction materialTextView2 = onData(anything())
                .inAdapterView(allOf(withId(R.id.list_view),
                        childAtPosition(
                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                2)))
                .atPosition(6);
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

        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.finish_btn), withText("Back"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        materialButton2.perform(click());

        ViewInteraction appCompatImageView = onView(
                allOf(withClassName(is("androidx.appcompat.widget.AppCompatImageView")), withContentDescription("Search"),
                        childAtPosition(
                                allOf(withClassName(is("android.widget.LinearLayout")),
                                        childAtPosition(
                                                withId(R.id.action_search),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageView.perform(click());

        ViewInteraction searchAutoComplete = onView(
                allOf(withClassName(is("android.widget.SearchView$SearchAutoComplete")),
                        childAtPosition(
                                allOf(withClassName(is("android.widget.LinearLayout")),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                0),
                        isDisplayed()));
        searchAutoComplete.perform(replaceText("siamang"), closeSoftKeyboard());

        DataInteraction materialTextView3 = onData(anything())
                .inAdapterView(allOf(withId(R.id.list_view),
                        childAtPosition(
                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                2)))
                .atPosition(0);
        materialTextView3.perform(click());

        ViewInteraction materialCheckBox3 = onView(
                allOf(withId(R.id.completed),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.exhibit_list_items),
                                        0),
                                1),
                        isDisplayed()));
        materialCheckBox3.perform(click());

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.finish_btn), withText("Back"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        materialButton3.perform(click());

        ViewInteraction materialButton4 = onView(
                allOf(withId(R.id.plan_btn), withText("Plan"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        materialButton4.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.todo_item_text), withText("crocodile"),
                        withParent(withParent(withId(R.id.todo_items))),
                        isDisplayed()));
        textView.check(matches(withText("crocodile")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.todo_item_text), withText("koi"),
                        withParent(withParent(withId(R.id.todo_items))),
                        isDisplayed()));
        textView2.check(matches(withText("koi")));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.todo_item_text), withText("siamang"),
                        withParent(withParent(withId(R.id.todo_items))),
                        isDisplayed()));
        textView3.check(matches(withText("siamang")));

        ViewInteraction materialTextView4 = onView(
                allOf(withId(R.id.delete_btn), withText("X"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.todo_items),
                                        0),
                                2),
                        isDisplayed()));
        materialTextView4.perform(click());

        ViewInteraction materialTextView5 = onView(
                allOf(withId(R.id.delete_btn), withText("X"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.todo_items),
                                        0),
                                2),
                        isDisplayed()));
        materialTextView5.perform(click());

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.todo_item_text), withText("siamang"),
                        withParent(withParent(withId(R.id.todo_items))),
                        isDisplayed()));
        textView4.check(matches(withText("siamang")));
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
