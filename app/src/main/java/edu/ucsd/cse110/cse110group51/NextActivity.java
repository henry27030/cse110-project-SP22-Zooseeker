package edu.ucsd.cse110.cse110group51;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class NextActivity extends AppCompatActivity {
    private ListView directionsView;
    private ArrayAdapter<String> arrayAdapter;
    private String destination;
    private ArrayList<String> stringArrList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        PlanCalculate planCalculate = new PlanCalculate();
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        String source = intent.getStringExtra("Key");
        stringArrList = (ArrayList<String>) args.getSerializable("ArrayList");
        List<String> Display = planCalculate.extracted(MainActivity.vInfo.get(source).coords, stringArrList);
        destination = planCalculate.getDestination();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        this.directionsView = this.findViewById(R.id.next_directions_view);
        arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                Display); //extracted returns an ArrayList
        directionsView.setAdapter(arrayAdapter);

        TextView text = (TextView)findViewById(R.id.TotalDistance);
        text.setText("Path length: " + String.valueOf(planCalculate.totalDistance) + " ft.");
    }

    public void NextNextButton(View view) {
        ArrayList<String> input = new ArrayList<String>();
        for (String string :stringArrList) {
            if (!string.equals(destination)){
                input.add(string);
            }
        }
        if (!input.isEmpty()) {
            Intent intent = new Intent(this, NextActivity.class);
            intent.putExtra("Key", destination);
            Bundle args = new Bundle();
            args.putSerializable("ArrayList", (Serializable) input);
            intent.putExtra("BUNDLE", args);
            startActivity(intent);
        }
    }

    public void NextSkipButton(View view) {
        /*
        ArrayList<String> input = new ArrayList<String>();
        for (String string: MainActivity.exhibitList) {
            if (!string.equals(destination)){
                input.add(string);
            }
        }
        MainActivity.exhibitList=input;

         */
        MainActivity.exhibitList.remove(destination);
        for(int i = 0; i < MainActivity.viewModel.getCurrentItems().size(); i++){
            if(MainActivity.viewModel.getCurrentItems().get(i).text.equals(destination)){
                MainActivity.viewModel.deleteTodo(MainActivity.viewModel.getCurrentItems().get(i));
                break;
            }
        }
        finish();
    }
    public void NextBackButton(View view) {
        finish();
    }

    public void NextDescriptionToggle(View view) {
        MainActivity.briefDirections=!MainActivity.briefDirections;
        PlanCalculate planCalculate = new PlanCalculate();
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        String source = intent.getStringExtra("Key");
        stringArrList = (ArrayList<String>) args.getSerializable("ArrayList");
        List<String> Display = planCalculate.extracted(MainActivity.vInfo.get(source).coords, stringArrList);
        this.directionsView = this.findViewById(R.id.next_directions_view);
        arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                Display); //extracted returns an ArrayList
        directionsView.setAdapter(arrayAdapter);

    }
}