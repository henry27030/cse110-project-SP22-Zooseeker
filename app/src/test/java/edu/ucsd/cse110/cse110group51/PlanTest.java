package edu.ucsd.cse110.cse110group51;

import static org.junit.Assert.assertEquals;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.lifecycle.Lifecycle;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;


@RunWith(AndroidJUnit4.class)
public class PlanTest {
    @Rule
    public ActivityScenarioRule<PlanActivity> scenarioRule = new ActivityScenarioRule<>(PlanActivity.class);

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
    public void PlanInitializationTest() {
        ActivityScenario<PlanActivity> scenario = scenarioRule.getScenario();

        scenario.moveToState(Lifecycle.State.CREATED);

        scenario.onActivity(activity -> {
            ListView listView = activity.findViewById(R.id.directions_view);
            assertEquals(listView.getAdapter().getCount(), 0);
        });
    }

    @Test
    public void SinglePlanTest() {
        //Graph<String, IdentifiedWeightedEdge> g;
        //AtomicReference<GraphPath<String, IdentifiedWeightedEdge>> path = null;
        ActivityScenario<PlanActivity> scenario = scenarioRule.getScenario();

        scenario.moveToState(Lifecycle.State.CREATED);
        MainActivity.exhibitList.add("Koi Fish");
        //MainActivity.exhibitList.add("Flamingos");
        //MainActivity.exhibitList.add("Capuchin Monkeys");

        scenario.onActivity(activity -> {
            ListView listView = activity.findViewById(R.id.directions_view);
            assertEquals(listView.getAdapter().getCount(), 0);

            //assertEquals(directionsView.getAdapter().getCount(), count);
        });
    }
}