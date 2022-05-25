package edu.ucsd.cse110.cse110group51;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;

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
            assertNull(listView);
        });
    }

    @Test
    public void SinglePlanTest() {
        //Graph<String, IdentifiedWeightedEdge> g;
        AtomicReference<GraphPath<String, IdentifiedWeightedEdge>> path = null;
        ActivityScenario<PlanActivity> scenario = scenarioRule.getScenario();

        scenario.moveToState(Lifecycle.State.CREATED);

        scenario.onActivity(activity -> {
            //Context context = getApplication().getApplicationContext();

            // 1. Load the graph...
            //g = ZooData.loadZooGraphJSON(context, "sample_zoo_graph.json");
            //GraphPath<String, IdentifiedWeightedEdge> path = DijkstraShortestPath.findPathBetween(g, start, goal);

            // 2. Load the information about our nodes and edges...
            //vInfo = ZooData.loadVertexInfoJSON(context, "sample_node_info.json");
            //eInfo = ZooData.loadEdgeInfoJSON(context, "sample_edge_info.json");
            ArrayAdapter<String> arrayAdapter = null;
            int count = 0;
            ListView directionsView = activity.findViewById(R.id.directions_view);
            //directionsView.getAdapter();
            MainActivity.exhibitList.add("lions");
            ArrayList<String> Directions = new ArrayList<String>();
            ArrayList<String> exhibitListInFunc = new ArrayList<String>();
            exhibitListInFunc.add("lions");
            String start = "entrance_exit_gate";
            int ShortestPath = 0;
            //path.set(DijkstraShortestPath.findPathBetween(MainActivity.g, start, "lions"));
            //MainActivity.Directions = Directions;

            //arrayAdapter = new ArrayAdapter<String>;
            //directionsView.setAdapter(arrayAdapter);

            assertEquals(directionsView.getAdapter().getCount(), count);
        });
    }
}