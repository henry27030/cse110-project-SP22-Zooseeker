package edu.ucsd.cse110.cse110group51;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PlanActivity extends AppCompatActivity {
    private ListView directionsView;
    private ArrayAdapter<String> arrayAdapter;
    private String destination;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        PlanCalculate planCalculate = new PlanCalculate();
        List<String> Display = planCalculate.extracted(MainActivity.UserCoord, MainActivity.exhibitList);
        //destination = getIntent().getStringExtra("Key");
        destination = planCalculate.getDestination();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);
        this.directionsView = this.findViewById(R.id.directions_view);
        arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                Display); //extracted returns an ArrayList
        directionsView.setAdapter(arrayAdapter);
    }

    public void PlanBackButton(View view) {
        finish();
    }

    public void PlanNextButton(View view) {

        ArrayList<String> input = new ArrayList<String>();
        for (String string :MainActivity.exhibitList) {
            if (!string.equals(destination)){
                input.add(string);
            }
        }
        //PlanCalculate planCalculate = new PlanCalculate();
        //planCalculate.extracted(MainActivity.start, input);

        if (!input.isEmpty()) {
            Intent intent = new Intent(this, NextActivity.class);
            intent.putExtra("Key", destination);
            Bundle args = new Bundle();
            args.putSerializable("ArrayList", (Serializable) input);
            intent.putExtra("BUNDLE", args);
            startActivity(intent);
        }
        /*
        MainActivity.Directions.add(MainActivity.exhibitList.get(0));
        MainActivity.Directions.add(destination);

         */
/*

        PlanCalculate planCalculate = new PlanCalculate();
        planCalculate.extracted(destination, input);


        arrayAdapter.notifyDataSetChanged();

 */

    }
}