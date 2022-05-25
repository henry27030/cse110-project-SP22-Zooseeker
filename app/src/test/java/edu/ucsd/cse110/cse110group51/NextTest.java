package edu.ucsd.cse110.cse110group51;

import static org.junit.Assert.assertEquals;

import android.content.Context;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.lifecycle.Lifecycle;
import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.google.common.base.Supplier;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

@RunWith(AndroidJUnit4.class)
public class NextTest {
    @Rule
    public ActivityScenarioRule<MainActivity> scenarioRule = new ActivityScenarioRule<>(MainActivity.class);
    //public ActivityScenarioRule<NextActivity> scenarioRule2 = new ActivityScenarioRule<>(NextActivity.class);
    //
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
    public void NextSuccessful() {

        ActivityScenario<MainActivity> scenario = scenarioRule.getScenario();
        scenario.moveToState(Lifecycle.State.CREATED);
        //ActivityScenario<NextActivity> scenario2 = scenarioRule2.getScenario();


        scenario.onActivity(activity -> {
            ListView listView = activity.findViewById(R.id.directions_view);
            int s = PlanActivity.BIND_AUTO_CREATE;
            Supplier<Class<NextActivity>> classSupplier = () -> NextActivity.class;
            {
            }
            ListView directionsView = activity.findViewById(R.id.list_view);

            int x = directionsView.getAdapter().getCount();

            //ListView listView1 = activity.findViewById(R.id.list_view);
            Supplier<Class<PlanActivity>> classSupplier2 = () -> PlanActivity.class;
            {
                int w = PlanActivity.RECEIVER_VISIBLE_TO_INSTANT_APPS;
            }

            //TextView textView = activity.findViewById(R.id.next_next_btn);
            //button.performClick();
            //ListView directionsView = activity.findViewById(R.id.directions_view);

            assertEquals(directionsView.getAdapter().getCount(), x);

            //int count = listView.getAdapter().getCount();
        });

    }
}