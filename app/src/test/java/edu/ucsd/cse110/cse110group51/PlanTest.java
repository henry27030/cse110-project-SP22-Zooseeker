package edu.ucsd.cse110.cse110group51;

import static org.junit.Assert.assertEquals;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProvider;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;


@RunWith(AndroidJUnit4.class)
public class PlanTest {
    @Rule
    public ActivityScenarioRule<PlanActivity> scenarioRulePlan = new ActivityScenarioRule<>(PlanActivity.class);
    @Rule
    public ActivityScenarioRule<MainActivity> scenarioRuleMain = new ActivityScenarioRule<>(MainActivity.class);

    @Rule
    public InstantTaskExecutorRule execRule = new InstantTaskExecutorRule();


    private TodoDatabase db;
    private TodoListItemDao dao;


    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = TodoDatabase.getSingleton(context);
        dao = db.todoListItemDao();
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }


    @Test
    public void PlanInitializationTest() {
        ActivityScenario<PlanActivity> scenario = scenarioRulePlan.getScenario();

        scenario.moveToState(Lifecycle.State.CREATED);

        scenario.onActivity(activity -> {
            ListView listView = activity.findViewById(R.id.directions_view);
            assertEquals(listView.getAdapter().getCount(), 0);

        });
    }

//    @Rule
//    public ActivityScenarioRule<TodoListActivity> scenarioRuleTodo = new ActivityScenarioRule<>(TodoListActivity.class);


    @Test
    public void SinglePlanTest() {
        ActivityScenario<MainActivity> scenario = scenarioRuleMain.getScenario();
        scenario.moveToState(Lifecycle.State.CREATED);
        scenario.onActivity(activity -> {
            MainActivity.exhibitList.add("Koi Fish");
//            MainActivity.viewModel = new ViewModelProvider(this.Activity)
//                    .get(TodoListViewModel.class);
            List<TodoListItem> list = MainActivity.viewModel.getCurrentItems();
            for (TodoListItem item : list) {
                if (!MainActivity.exhibitList.contains(item.text)) {
                    MainActivity.exhibitList.add(item.text);
                }
            }
            Button planButton = activity.findViewById(R.id.plan_btn);
            planButton.performClick();

//            Button planDisplay = TodoListActivity.findViewById(R.id.plan_display);
//            planDisplay.performClick();

            //assertEquals(directionsView.getAdapter().getCount(), count);*/
        });

        ActivityScenario<PlanActivity> scenario2 = scenarioRulePlan.getScenario();

        MainActivity.exhibitList.add("Koi Fish");

        scenario2.moveToState(Lifecycle.State.CREATED);

        scenario2.onActivity(activity -> {

            ListView listView = activity.findViewById(R.id.directions_view);
            assertEquals(listView.getAdapter().getCount(), 0);

        });

        //ActivityScenario<TodoListActivity> scenario2 = scenarioRuleTodo.getScenario();
//        scenario2.moveToState(Lifecycle.State.CREATED);
//        scenario2.onActivity(activity ->



//        ActivityScenario<TodoListActivity> scenario3 = scenarioRuleTodo.getScenario();
//        scenario3.moveToState(Lifecycle.State.CREATED);
//        scenario3.onActivity(activity -> {
//            Button planDisplay = activity.findViewById(R.id.plan_display);
//            planDisplay.performClick();
//        });


        //ActivityScenario.close;

//        ActivityScenario<TodoListActivity> scenario2 = scenarioRuleTodo.getScenario();
//        scenario2.moveToState(Lifecycle.State.CREATED);
//        scenario2.onActivity(activity -> {
////            MainActivity.exhibitList.add("Koi Fish");
////            Button planButton = activity.findViewById(R.id.plan_btn);
////            planButton.performClick();
//            Button planDisplay = activity.findViewById(R.id.plan_display);
//            planDisplay.performClick();
//        });
    }

//    @Test
//    public void PlanTest2() {
//        ActivityScenario<PlanActivity> scenario = scenarioRulePlan.getScenario();
//
//        scenario.moveToState(Lifecycle.State.CREATED);
//
//        scenario.onActivity(activity -> {
//            ListView listView = activity.findViewById(R.id.directions_view);
//            assertEquals(listView.getAdapter().getCount(), 0);
//
//        });
//    }


//    @Rule
//    ActivityScenarioRule<TodoListActivity> scenarioRuleTodo = new ActivityScenarioRule<>(TodoListActivity.class);
//
//    @Test
//    public void SinPlanTest() {
//        ActivityScenario<MainActivity> scenario = scenarioRuleMain.getScenario();
//        scenario.moveToState(Lifecycle.State.CREATED);
//        scenario.onActivity(activity -> {
//            Button planDisplay = activity.findViewById(R.id.plan_display);
//            planDisplay.performClick();
//        });
//    }
}