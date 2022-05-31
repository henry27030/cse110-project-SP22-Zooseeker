package edu.ucsd.cse110.cse110group51;

import static org.junit.Assert.assertEquals;

import android.content.Context;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.lifecycle.Lifecycle;
import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

@RunWith(AndroidJUnit4.class)
public class MockLocationTest {
    @Rule
    public ActivityScenarioRule<MainActivity> scenarioRule = new ActivityScenarioRule<>(MainActivity.class);

    private TodoDatabase db;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = TodoDatabase.getSingleton(context);
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void MockLocationTest() {

        ActivityScenario<MainActivity> scenario = scenarioRule.getScenario();
        scenario.moveToState(Lifecycle.State.CREATED);

        scenario.onActivity(activity -> {
            ListView listView = activity.findViewById(R.id.list_view);
//          MenuItem menuItem = activity.findViewById(R.id.action_search);
            SearchView searchView = activity.findViewById(R.id.action_search);
            searchView.setQuery("crocodile", true);

            int count = listView.getAdapter().getCount();
            assertEquals(1, count);

        });
    }
}