package edu.ucsd.cse110.cse110group51;

import static org.junit.Assert.assertEquals;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.content.Context;
import android.widget.Button;
import android.widget.ListView;

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
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class ExhibitListTest {
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
    public void AddExhibitTest() {
        ActivityScenario<MainActivity> scenario = scenarioRuleMain.getScenario();
        scenario.moveToState(Lifecycle.State.CREATED);
        scenario.onActivity(activity -> {
            MainActivity.exhibitList.add("Koi Fish");
            List<TodoListItem> list = MainActivity.viewModel.getCurrentItems();
            for (TodoListItem item : list) {
                if (!MainActivity.exhibitList.contains(item.text)) {
                    MainActivity.exhibitList.add(item.text);
                }
            }
            Button planButton = activity.findViewById(R.id.plan_btn);
            planButton.performClick();

            assertEquals(MainActivity.exhibitList.size(),1);
        });

    }

    @Test
    public void DeleteExhibitTest() {
        ActivityScenario<MainActivity> scenario = scenarioRuleMain.getScenario();
        scenario.moveToState(Lifecycle.State.CREATED);
        scenario.onActivity(activity -> {
            //MainActivity.exhibitList.add("Koi Fish");
            List<TodoListItem> list = MainActivity.viewModel.getCurrentItems();
            for (TodoListItem item : list) {
                if (!MainActivity.exhibitList.contains(item.text)) {
                    MainActivity.exhibitList.add(item.text);
                }
            }
            Button planButton = activity.findViewById(R.id.plan_btn);
            planButton.performClick();

            MainActivity.exhibitList.remove("Koi Fish");
            assertEquals(MainActivity.exhibitList.size(),0);

        });

    }
}
