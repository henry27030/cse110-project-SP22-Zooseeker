package edu.ucsd.cse110.cse110group51;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PreviousActivity extends AppCompatActivity {
    private ListView directionsView;
    private ArrayAdapter<String> arrayAdapter;
    private String destination;
    private ArrayList<String> stringArrList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        PlanCalculate planCalculate = new PlanCalculate();
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        stringArrList = (ArrayList<String>) args.getSerializable("ArrayList");
        List<String> Display = planCalculate.extracted(MainActivity.UserCoord, stringArrList);
        destination = planCalculate.getDestination();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous);

        this.directionsView = this.findViewById(R.id.previous_directions_view);
        arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                Display); //extracted returns an ArrayList
        directionsView.setAdapter(arrayAdapter);

        TextView text = (TextView)findViewById(R.id.PreviousTotalDistance);
        text.setText("Path length: " + String.valueOf(planCalculate.totalDistance) + " ft.");
    }

    public void PreviousDescriptionToggle(View view) {
        MainActivity.briefDirections = !MainActivity.briefDirections;
        PlanCalculate planCalculate = new PlanCalculate();
        List<String> Display = planCalculate.extracted(MainActivity.UserCoord, stringArrList);
        setContentView(R.layout.activity_previous);
        this.directionsView = this.findViewById(R.id.previous_directions_view);
        arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                Display); //extracted returns an ArrayList
        directionsView.setAdapter(arrayAdapter);
        TextView text = (TextView)findViewById(R.id.PreviousTotalDistance);
        text.setText("Path length: " + String.valueOf(planCalculate.totalDistance) + " ft.");
    }

    public void PreviousBack(View view) {
        finish();
    }
}