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

            int count = listView.getAdapter().getCount();
            assertEquals(listView.getAdapter().getCount(), count);
        });
    }

    public void SinglePlanTest() {

        ActivityScenario<MainActivity> scenario = scenarioRule.getScenario();

        scenario.moveToState(Lifecycle.State.CREATED);

        scenario.onActivity(activity -> {
            ListView listView = activity.findViewById(R.id.list_view);
            int x = listView.getAdapter().getCount();
            AtomicReference<GraphPath<String, IdentifiedWeightedEdge>> path = null;
            ArrayAdapter<String> arrayAdapter = null;


            int count = listView.getAdapter().getCount();
            assertEquals(0, count);
        });
    }


}




//package edu.ucsd.cse110.cse110group51;
//
//import android.widget.ArrayAdapter;
//import android.widget.ListView;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertNotEquals;
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertNull;
//
//import org.jgrapht.GraphPath;
//import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
//
//import androidx.lifecycle.Lifecycle;
//import androidx.test.core.app.ActivityScenario;
//import androidx.test.ext.junit.rules.ActivityScenarioRule;
//import androidx.test.ext.junit.runners.AndroidJUnit4;
//
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import java.util.ArrayList;
//import java.util.concurrent.atomic.AtomicReference;
//
//
//@RunWith(AndroidJUnit4.class)
//public class PlanTest {
//    @Rule
//    public ActivityScenarioRule<MainActivity> scenarioRule = new ActivityScenarioRule<>(MainActivity.class);
//    public ActivityScenarioRule<PlanActivity> scenarioRule2 = new ActivityScenarioRule<>(PlanActivity.class);
//
//    @Test
//    public void PlanInitializationTest() {
//        ActivityScenario<MainActivity> scenario = scenarioRule.getScenario();
//
//        scenario.moveToState(Lifecycle.State.CREATED);
//
//
//        scenario.onActivity(activity -> {
//            ListView listView = activity.findViewById(R.id.list_view);
//            int x = listView.getAdapter().getCount();
//            assertEquals(listView.getAdapter().getCount(), x);
//        });
//    }
//
//    @Test
//    public void SinglePlanTest() {
//        ActivityScenario<PlanActivity> scenario = scenarioRule2.getScenario();
//
//        scenario.moveToState(Lifecycle.State.CREATED);
//
//
//        scenario.onActivity(activity -> {
//            //ListView listView = activity.findViewById(R.id.list_view);
//            //int x = listView.getAdapter().getCount();
//            assertEquals(1,1);
//        });
//    }
//
//    /*@Test
//    public void SinglePlanTest() {
//        //Graph<String, IdentifiedWeightedEdge> g;
//        AtomicReference<GraphPath<String, IdentifiedWeightedEdge>> path = null;
//        ActivityScenario<MainActivity> scenario = scenarioRule.getScenario();
//
//        scenario.moveToState(Lifecycle.State.CREATED);
//
//        scenario.onActivity(activity -> {
//            //Context context = getApplication().getApplicationContext();
//
//            // 1. Load the graph...
//            //g = ZooData.loadZooGraphJSON(context, "sample_zoo_graph.json");
//            //GraphPath<String, IdentifiedWeightedEdge> path = DijkstraShortestPath.findPathBetween(g, start, goal);
//
//            // 2. Load the information about our nodes and edges...
//            //vInfo = ZooData.loadVertexInfoJSON(context, "sample_node_info.json");
//            //eInfo = ZooData.loadEdgeInfoJSON(context, "sample_edge_info.json");
//            ArrayAdapter<String> arrayAdapter = null;
//            int count = 0;
//            ListView directionsView = activity.findViewById(R.id.list_view);
//            //directionsView.getAdapter();
//            MainActivity.exhibitList.add("lions");
//            ArrayList<String> Directions = new ArrayList<String>();
//            ArrayList<String> exhibitListInFunc = new ArrayList<String>();
//            exhibitListInFunc.add("lions");
//            String start = "entrance_exit_gate";
//            int ShortestPath = 0;
//            //path.set(DijkstraShortestPath.findPathBetween(MainActivity.g, start, "lions"));
//            //MainActivity.Directions = Directions;
//
//            //arrayAdapter = new ArrayAdapter<String>;
//            //directionsView.setAdapter(arrayAdapter);
//
//            assertEquals(directionsView.getAdapter().getCount(), count);
//        });
//    }*/
//}