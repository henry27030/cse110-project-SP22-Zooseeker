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
//public class NextTest {
//    @Rule
//    public ActivityScenarioRule<PlanActivity> scenarioRule = new ActivityScenarioRule<>(PlanActivity.class);
//
//    @Test
//    public void NextSuccessfulTest() {
//        ActivityScenario<PlanActivity> scenario = scenarioRule.getScenario();
//
//        scenario.moveToState(Lifecycle.State.CREATED);
//
//        scenario.onActivity(activity -> {
//            ListView listView = activity.findViewById(R.id.directions_view);
//            assertEquals(listView.getAdapter().getCount(), 0);
//        });
//    }
//
//    @Test
//    public void NextUnSuccessfulTest() {
//        //Graph<String, IdentifiedWeightedEdge> g;
//        AtomicReference<GraphPath<String, IdentifiedWeightedEdge>> path = null;
//        ActivityScenario<PlanActivity> scenario = scenarioRule.getScenario();
//
//        scenario.moveToState(Lifecycle.State.CREATED);
//
//        scenario.onActivity(activity -> {
//            //Context context = getApplication().getApplicationContext();
//
//
//            ArrayAdapter<String> arrayAdapter = null;
//            int count = 0;
//            ListView directionsView = activity.findViewById(R.id.directions_view);
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
//    }
//}