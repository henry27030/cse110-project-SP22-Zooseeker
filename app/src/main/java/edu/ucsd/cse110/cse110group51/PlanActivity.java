package edu.ucsd.cse110.cse110group51;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class PlanActivity extends AppCompatActivity {
    private ListView directionsView;
    private ArrayAdapter<String> arrayAdapter;
    private String destination;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        PlanCalculate planCalculate = new PlanCalculate();
        destination = getIntent().getStringExtra("Key");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);
        this.directionsView = this.findViewById(R.id.directions_view);
        arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                planCalculate.extracted(MainActivity.start, MainActivity.exhibitList)); //extracted returns an ArrayList
        directionsView.setAdapter(arrayAdapter);
    }

    public void PlanBackButton(View view) {
        finish();
    }

    public void PlanNextButton(View view) {
        /*
        MainActivity.Directions.add(MainActivity.exhibitList.get(0));
        MainActivity.Directions.add(destination);

         */
/*
        ArrayList<String> input = new ArrayList<String>();
        for (String string :MainActivity.exhibitList) {
            if (!string.equals(destination)){
                input.add(string);
            }
        }
        PlanCalculate planCalculate = new PlanCalculate();
        planCalculate.extracted(destination, input);


        arrayAdapter.notifyDataSetChanged();

 */

    }
}