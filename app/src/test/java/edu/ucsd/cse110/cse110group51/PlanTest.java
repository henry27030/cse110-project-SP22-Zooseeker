package edu.ucsd.cse110.cse110group51;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

@RunWith(AndroidJUnit4.class)
public class PlanTest {
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
    public void PlanTest1() {
        TodoListItem item1 = new TodoListItem( "Pizza time", 0);
        TodoListItem item2 = new TodoListItem( "Photos of Spider-Man", 1);

        long id1 = dao.insert(item1);
        long id2 = dao.insert(item2);

        // check that these have all been inserted with unique IDs.
        assertNotEquals(id1, id2);
    }

    @Test
    public void PlanTest2() {
        TodoListItem insertedItem = new TodoListItem( "Pizza time", 0);
        long id = dao.insert(insertedItem);

        TodoListItem item = dao.get(id);
        assertEquals(id, item.id);
        assertEquals(insertedItem.text, item.text);
        assertEquals(insertedItem.order, item.order);
    }

    @Test
    public void PlanTest3() {
        TodoListItem item = new TodoListItem("Pizza time", 0);
        long id = dao.insert(item);

        item = dao.get(id);
        int itemsDeleted = dao.delete(item);
        assertEquals(1, itemsDeleted);
        assertNull(dao.get(id));
    }
}



/*package edu.ucsd.cse110.cse110group51;

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
import java.util.concurrent.atomic.AtomicReference;*/

/*
@RunWith(AndroidJUnit4.class)
public class PlanTest {
    @Rule
    public ActivityScenarioRule<MainActivity> scenarioRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void PlanInitializationTest() {
        ActivityScenario<MainActivity> scenario = scenarioRule.getScenario();

        scenario.moveToState(Lifecycle.State.CREATED);

        scenario.onActivity(activity -> {
            //ListView listView = activity.findViewById(R.id.directions_view);
            //assertEquals(listView.getAdapter().getCount(), 0);
            assertEquals(0,0);
        });
    }

    @Test
    public void SinglePlanTest() {
        //Graph<String, IdentifiedWeightedEdge> g;
        //AtomicReference<GraphPath<String, IdentifiedWeightedEdge>> path = null;
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
            //ListView directionsView = activity.findViewById(R.id.action_search);
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

            assertEquals(0, count);
        });
    }
}*/
