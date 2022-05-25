package edu.ucsd.cse110.cse110group51;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import android.content.Context;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.lifecycle.Lifecycle;
import androidx.room.Room;
import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.manipulation.Ordering;


import java.io.IOException;

@RunWith(AndroidJUnit4.class)
public class SearchBarTest {
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
    public void searchFilterTest() {

        ActivityScenario<MainActivity> scenario = scenarioRule.getScenario();

        scenario.moveToState(Lifecycle.State.CREATED);

        scenario.onActivity(activity -> {
            ListView listView = activity.findViewById(R.id.list_view);
//          MenuItem menuItem = activity.findViewById(R.id.action_search);
            SearchView searchView = activity.findViewById(R.id.action_search);
            searchView.setQuery("lions", true);

            int count = listView.getAdapter().getCount();
            assertEquals(1, count);
        });
    }

    public void searchCompleteFilterTest() {

        ActivityScenario<MainActivity> scenario = scenarioRule.getScenario();

        scenario.moveToState(Lifecycle.State.CREATED);

        scenario.onActivity(activity -> {
            ListView listView = activity.findViewById(R.id.list_view);
//          MenuItem menuItem = activity.findViewById(R.id.action_search);
            SearchView searchView = activity.findViewById(R.id.action_search);
            searchView.setQuery("lions", true);

            int count = listView.getAdapter().getCount();
            assertEquals(1, count);
        });
    }

    public void searchInvalidFilterTest() {

        ActivityScenario<MainActivity> scenario = scenarioRule.getScenario();

        scenario.moveToState(Lifecycle.State.CREATED);

        scenario.onActivity(activity -> {
            ListView listView = activity.findViewById(R.id.list_view);
//          MenuItem menuItem = activity.findViewById(R.id.action_search);
            SearchView searchView = activity.findViewById(R.id.action_search);
            searchView.setQuery("goose", true);

            int count = listView.getAdapter().getCount();
            assertEquals(0, count);
        });
    }
}

