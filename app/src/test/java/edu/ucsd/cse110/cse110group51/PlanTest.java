package edu.ucsd.cse110.cse110group51;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import android.content.Context;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
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

import org.jgrapht.GraphPath;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.manipulation.Ordering;


import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

@RunWith(AndroidJUnit4.class)
public class PlanTest {
    @Rule
    public ActivityScenarioRule<MainActivity> scenarioRule = new ActivityScenarioRule<>(MainActivity.class);

    private TodoListItemDao dao;
    private TodoDatabase db;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, TodoDatabase.class)
                .allowMainThreadQueries()
                .build();
        dao = db.todoListItemDao();
    }
    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void PlanInitializationTest() {

        MainActivity.exhibitList.add("lions");
        ArrayList<String> Directions = new ArrayList<String>();
        ArrayList<String> exhibitListInFunc = new ArrayList<String>();
        exhibitListInFunc.add("lions");
        String start = "entrance_exit_gate";
    }

    /*public void SinglePlanTest() {

        ActivityScenario<MainActivity> scenario = scenarioRule.getScenario();

        scenario.moveToState(Lifecycle.State.CREATED);

        scenario.onActivity(activity -> {
            ListView listView = activity.findViewById(R.id.list_view);
            int x = listView.getAdapter().getCount();
            AtomicReference<GraphPath<String, IdentifiedWeightedEdge>> path = null;
            ArrayAdapter<String> arrayAdapter = null;

            MainActivity.exhibitList.add("lions");
            ArrayList<String> Directions = new ArrayList<String>();
            ArrayList<String> exhibitListInFunc = new ArrayList<String>();
            exhibitListInFunc.add("lions");
            String start = "entrance_exit_gate";
            int ShortestPath = 0;
            //path.set(DijkstraShortestPath.findPathBetween(MainActivity.g, start, "lions"));
//            //MainActivity.Directions = Directions;
            int count = listView.getAdapter().getCount();
            assertEquals(0, count);
        });
    }*/


}