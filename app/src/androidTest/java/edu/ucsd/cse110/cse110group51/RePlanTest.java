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
public class RePlanTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void UserLocationChangeTest() {
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
        ViewInteraction materialButton21 = onView(
                allOf(withId(R.id.mock_location), withText("Mock Location"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        materialButton21.perform(click());

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

        ViewInteraction materialButton20 = onView(
                allOf(withId(R.id.Mock), withText("Mock User Location"),
                        childAtPosition(
                                allOf(withId(R.id.setCoor),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                3),
                        isDisplayed()));
        materialButton20.perform(click());

        ViewInteraction materialButton17 = onView(
                allOf(withId(R.id.mock_back), withText("Back"),
                        childAtPosition(
                                allOf(withId(R.id.setCoor),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                0),
                        isDisplayed()));
        materialButton17.perform(click());

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.back_btn), withText("Back"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        materialButton3.perform(click());




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

        ViewInteraction materialButton15 = onView(
                allOf(withId(R.id.finish_btn), withText("Back"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        materialButton15.perform(click());

        DataInteraction materialTextView2 = onData(anything())
                .inAdapterView(allOf(withId(R.id.list_view),
                        childAtPosition(
                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                2)))
                .atPosition(12);
        materialTextView2.perform(click());

        ViewInteraction materialCheckBox2 = onView(
                allOf(withId(R.id.completed),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.exhibit_list_items),
                                        1),
                                1),
                        isDisplayed()));
        materialCheckBox2.perform(click());

        ViewInteraction materialCheckBox3 = onView(
                allOf(withId(R.id.completed),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.exhibit_list_items),
                                        2),
                                1),
                        isDisplayed()));
        materialCheckBox3.perform(click());

        ViewInteraction materialCheckBox4 = onView(
                allOf(withId(R.id.completed),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.exhibit_list_items),
                                        3),
                                1),
                        isDisplayed()));
        materialCheckBox4.perform(click());

        ViewInteraction materialCheckBox5 = onView(
                allOf(withId(R.id.completed),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.exhibit_list_items),
                                        0),
                                1),
                        isDisplayed()));
        materialCheckBox5.perform(click());

        ViewInteraction materialButton16 = onView(
                allOf(withId(R.id.finish_btn), withText("Back"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        materialButton16.perform(click());

        ViewInteraction materialButton23 = onView(
                allOf(withId(R.id.plan_btn), withText("Plan"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        materialButton23.perform(click());

        ViewInteraction materialButton4 = onView(
                allOf(withId(R.id.mock_location), withText("Mock Location"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        materialButton4.perform(click());

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.lat),
                        childAtPosition(
                                allOf(withId(R.id.setCoor),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatEditText6.perform(replaceText("32.73459618734685"), closeSoftKeyboard());

        ViewInteraction appCompatEditText7 = onView(
                allOf(withId(R.id.lng),
                        childAtPosition(
                                allOf(withId(R.id.setCoor),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                2),
                        isDisplayed()));
        appCompatEditText7.perform(replaceText("-117.14936"), closeSoftKeyboard());

        ViewInteraction materialButton5 = onView(
                allOf(withId(R.id.Mock), withText("Mock User Location"),
                        childAtPosition(
                                allOf(withId(R.id.setCoor),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                3),
                        isDisplayed()));
        materialButton5.perform(click());

        ViewInteraction materialButton6 = onView(
                allOf(withId(R.id.mock_back), withText("Back"),
                        childAtPosition(
                                allOf(withId(R.id.setCoor),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                0),
                        isDisplayed()));
        materialButton6.perform(click());

        ViewInteraction materialButton7 = onView(
                allOf(withId(R.id.plan_display), withText("Plan Display"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        materialButton7.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(android.R.id.text1), withText("Walk 1200.0 ft along Orangutan Trail from Treetops Way / Orangutan Trail to Siamangs"),
                        withParent(allOf(withId(R.id.directions_view),
                                withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class)))),
                        isDisplayed()));
        textView.check(matches(withText("Walk 1200.0 ft along Orangutan Trail from Treetops Way / Orangutan Trail to Siamangs")));

        ViewInteraction materialButton12 = onView(
                allOf(withId(R.id.plan_back_button), withText("Back"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        materialButton12.perform(click());

        ViewInteraction materialButton13 = onView(
                allOf(withId(R.id.mock_location), withText("Mock Location"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        materialButton13.perform(click());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.lat),
                        childAtPosition(
                                allOf(withId(R.id.setCoor),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatEditText3.perform(replaceText("32.73870593465047"), closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.lng),
                        childAtPosition(
                                allOf(withId(R.id.setCoor),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                2),
                        isDisplayed()));
        appCompatEditText4.perform(replaceText("-117.1695850705821"), closeSoftKeyboard());

        ViewInteraction materialButton14 = onView(
                allOf(withId(R.id.Mock), withText("Mock User Location"),
                        childAtPosition(
                                allOf(withId(R.id.setCoor),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                3),
                        isDisplayed()));
        materialButton14.perform(click());

        ViewInteraction materialButton25 = onView(
                allOf(withId(R.id.mock_back), withText("Back"),
                        childAtPosition(
                                allOf(withId(R.id.setCoor),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                0),
                        isDisplayed()));
        materialButton25.perform(click());

        ViewInteraction materialButton26 = onView(
                allOf(withId(R.id.plan_display), withText("Plan Display"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        materialButton26.perform(click());

        ViewInteraction textView2 = onView(
                allOf(withId(android.R.id.text1), withText("Walk 1300.0 ft along Aviary Trail from Parker Aviary to Owens Aviary and find Emerald Doves inside."),
                        withParent(allOf(withId(R.id.directions_view),
                                withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class)))),
                        isDisplayed()));
        textView2.check(matches(withText("Walk 1300.0 ft along Aviary Trail from Parker Aviary to Owens Aviary and find Emerald Doves inside.")));
    }

    @Test
    public void deleteExhibitTest() {
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
                .atPosition(2);
        materialTextView.perform(click());

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
                .atPosition(12);
        materialTextView2.perform(click());

        ViewInteraction materialCheckBox = onView(
                allOf(withId(R.id.completed),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.exhibit_list_items),
                                        0),
                                1),
                        isDisplayed()));
        materialCheckBox.perform(click());

        ViewInteraction materialCheckBox2 = onView(
                allOf(withId(R.id.completed),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.exhibit_list_items),
                                        1),
                                1),
                        isDisplayed()));
        materialCheckBox2.perform(click());

        ViewInteraction materialCheckBox3 = onView(
                allOf(withId(R.id.completed),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.exhibit_list_items),
                                        2),
                                1),
                        isDisplayed()));
        materialCheckBox3.perform(click());

        ViewInteraction materialCheckBox4 = onView(
                allOf(withId(R.id.completed),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.exhibit_list_items),
                                        3),
                                1),
                        isDisplayed()));
        materialCheckBox4.perform(click());

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
                allOf(withId(android.R.id.text1), withText("Walk 1200.0 ft along Orangutan Trail from Treetops Way / Orangutan Trail to Siamangs"),
                        withParent(allOf(withId(R.id.directions_view),
                                withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class)))),
                        isDisplayed()));
        textView.check(matches(withText("Walk 1200.0 ft along Orangutan Trail from Treetops Way / Orangutan Trail to Siamangs")));

        ViewInteraction materialButton11 = onView(
                allOf(withId(R.id.plan_back_button), withText("Back"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        materialButton11.perform(click());

        ViewInteraction materialTextView3 = onView(
                allOf(withId(R.id.delete_btn), withText("X"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.todo_items),
                                        3),
                                2),
                        isDisplayed()));
        materialTextView3.perform(click());

        ViewInteraction materialButton12 = onView(
                allOf(withId(R.id.plan_display), withText("Plan Display"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        materialButton12.perform(click());

        ViewInteraction textView2 = onView(
                allOf(withId(android.R.id.text1), withText("Walk 1100.0 ft along Orangutan Trail from Siamangs to Orangutans"),
                        withParent(allOf(withId(R.id.directions_view),
                                withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class)))),
                        isDisplayed()));
        textView2.check(matches(withText("Walk 1100.0 ft along Orangutan Trail from Siamangs to Orangutans")));
    }

    @Test
    public void skipExhibitTest() {
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
                allOf(withId(R.id.Clear), withText("Clear"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                6),
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
                .atPosition(12);
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

        ViewInteraction materialCheckBox2 = onView(
                allOf(withId(R.id.completed),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.exhibit_list_items),
                                        1),
                                1),
                        isDisplayed()));
        materialCheckBox2.perform(click());

        ViewInteraction materialCheckBox3 = onView(
                allOf(withId(R.id.completed),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.exhibit_list_items),
                                        2),
                                1),
                        isDisplayed()));
        materialCheckBox3.perform(click());

        ViewInteraction materialCheckBox4 = onView(
                allOf(withId(R.id.completed),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.exhibit_list_items),
                                        3),
                                1),
                        isDisplayed()));
        materialCheckBox4.perform(click());

        ViewInteraction materialButton7 = onView(
                allOf(withId(R.id.finish_btn), withText("Back"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        materialButton7.perform(click());

        ViewInteraction materialButton8 = onView(
                allOf(withId(R.id.plan_btn), withText("Plan"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        materialButton8.perform(click());

        ViewInteraction materialButton9 = onView(
                allOf(withId(R.id.plan_display), withText("Plan Display"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        materialButton9.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(android.R.id.text1), withText("Walk 1200.0 ft along Orangutan Trail from Treetops Way / Orangutan Trail to Siamangs"),
                        withParent(allOf(withId(R.id.directions_view),
                                withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class)))),
                        isDisplayed()));
        textView.check(matches(withText("Walk 1200.0 ft along Orangutan Trail from Treetops Way / Orangutan Trail to Siamangs")));

        ViewInteraction materialButton10 = onView(
                allOf(withId(R.id.plan_next_button), withText("Next"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        materialButton10.perform(click());

        ViewInteraction textView2 = onView(
                allOf(withId(android.R.id.text1), withText("Walk 1100.0 ft along Orangutan Trail from Siamangs to Orangutans"),
                        withParent(allOf(withId(R.id.next_directions_view),
                                withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class)))),
                        isDisplayed()));
        textView2.check(matches(withText("Walk 1100.0 ft along Orangutan Trail from Siamangs to Orangutans")));

        ViewInteraction materialButton11 = onView(
                allOf(withId(R.id.next_next_button), withText("Next"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        materialButton11.perform(click());

        ViewInteraction textView3 = onView(
                allOf(withId(android.R.id.text1), withText("Walk 1300.0 ft along Aviary Trail from Parker Aviary to Owens Aviary and find Emerald Doves inside."),
                        withParent(allOf(withId(R.id.next_directions_view),
                                withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class)))),
                        isDisplayed()));
        textView3.check(matches(withText("Walk 1300.0 ft along Aviary Trail from Parker Aviary to Owens Aviary and find Emerald Doves inside.")));

        ViewInteraction materialButton12 = onView(
                allOf(withId(R.id.next_back_button), withText("Previous"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        materialButton12.perform(click());

        ViewInteraction materialButton13 = onView(
                allOf(withId(R.id.button), withText("Skip"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        materialButton13.perform(click());

        ViewInteraction materialButton14 = onView(
                allOf(withId(R.id.plan_display), withText("Plan Display"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        materialButton14.perform(click());

        ViewInteraction textView4 = onView(
                allOf(withId(android.R.id.text1), withText("Walk 1200.0 ft along Orangutan Trail from Treetops Way / Orangutan Trail to Siamangs"),
                        withParent(allOf(withId(R.id.directions_view),
                                withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class)))),
                        isDisplayed()));
        textView4.check(matches(withText("Walk 1200.0 ft along Orangutan Trail from Treetops Way / Orangutan Trail to Siamangs")));

        ViewInteraction materialButton15 = onView(
                allOf(withId(R.id.plan_next_button), withText("Next"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        materialButton15.perform(click());

        ViewInteraction textView5 = onView(
                allOf(withId(android.R.id.text1), withText("Walk 1300.0 ft along Aviary Trail from Parker Aviary to Owens Aviary and find Emerald Doves inside."),
                        withParent(allOf(withId(R.id.next_directions_view),
                                withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class)))),
                        isDisplayed()));
        textView5.check(matches(withText("Walk 1300.0 ft along Aviary Trail from Parker Aviary to Owens Aviary and find Emerald Doves inside.")));
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
