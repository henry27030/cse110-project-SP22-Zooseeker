package edu.ucsd.cse110.cse110group51;

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
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;


@RunWith(AndroidJUnit4.class)
public class PlanTest {
    @Rule
    public ActivityScenarioRule<MainActivity> scenarioRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void PlanInitializationTest() {
        ActivityScenario<MainActivity> scenario = scenarioRule.getScenario();

        scenario.moveToState(Lifecycle.State.CREATED);

        scenario.onActivity(activity -> {
            ListView listView = activity.findViewById(R.id.list_view);
            assertEquals(listView.getAdapter().getCount(), 0);
        });
    }

    @Test
    public void SinglePlanTest() {
        //Graph<String, IdentifiedWeightedEdge> g;
        AtomicReference<GraphPath<String, IdentifiedWeightedEdge>> path = null;
        ActivityScenario<MainActivity> scenario = scenarioRule.getScenario();

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
            ListView directionsView = activity.findViewById(R.id.list_view);
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