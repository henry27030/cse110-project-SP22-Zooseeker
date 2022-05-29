package edu.ucsd.cse110.cse110group51;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PlanActivity extends AppCompatActivity {
    private Button ShowHideMock;
    private ListView directionsView;
    private ArrayAdapter<String> arrayAdapter;
    private String destination;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        List<String> Display = null;
        if (!MainActivity.exhibitList.isEmpty()) {
            PlanCalculate planCalculate = new PlanCalculate();
            Display = planCalculate.extracted(MainActivity.UserCoord, MainActivity.exhibitList);
            destination = planCalculate.getDestination();
        }
        else {
            Display = new ArrayList<String>();
        }
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
        if (!input.isEmpty()) {

            // in the case that User is at one of the chosen exhibits.
            if (MainActivity.UserCoord.equals(MainActivity.vInfo.get(destination).coords)) {
                MainActivity.previousExhibits.push(destination);
                MainActivity.exhibitList.remove(destination);
                for(int i = 0; i < MainActivity.viewModel.getCurrentItems().size(); i++){
                    if(MainActivity.viewModel.getCurrentItems().get(i).text.equals(destination)){
                        MainActivity.viewModel.deleteTodo(MainActivity.viewModel.getCurrentItems().get(i));
                        break;
                    }
                }
                PlanCalculate planCalculate = new PlanCalculate();
                List<String> Display = planCalculate.extracted(MainActivity.UserCoord, MainActivity.exhibitList);
                destination = planCalculate.getDestination();
                this.directionsView = this.findViewById(R.id.directions_view);
                arrayAdapter = new ArrayAdapter<String>(
                        this,
                        android.R.layout.simple_list_item_1,
                        Display); //extracted returns an ArrayList
                directionsView.setAdapter(arrayAdapter);
            }

            // in the case that User is not at one of the chosen exhibits.
            else {
                Intent intent = new Intent(this, NextActivity.class);
                intent.putExtra("Key", destination);
                Bundle args = new Bundle();
                args.putSerializable("ArrayList", (Serializable) input);
                intent.putExtra("BUNDLE", args);
                startActivity(intent);
            }
        }
    }

    public void PlanDescriptionToggle(View view) {
        if (!MainActivity.exhibitList.isEmpty()) {
            MainActivity.briefDirections = !MainActivity.briefDirections;
            PlanCalculate planCalculate = new PlanCalculate();
            List<String> Display = planCalculate.extracted(MainActivity.UserCoord, MainActivity.exhibitList);
            setContentView(R.layout.activity_plan);
            this.directionsView = this.findViewById(R.id.directions_view);
            arrayAdapter = new ArrayAdapter<String>(
                    this,
                    android.R.layout.simple_list_item_1,
                    Display); //extracted returns an ArrayList
            directionsView.setAdapter(arrayAdapter);
        }
    }

    public void PlanPrevious(View view) {
        if (!MainActivity.previousExhibits.isEmpty()) {
            Intent intent = new Intent(this, PreviousActivity.class);
            Bundle args = new Bundle();
            ArrayList<String> input = new ArrayList<String>();
            input.add(MainActivity.previousExhibits.peek());
            args.putSerializable("ArrayList", (Serializable) input);
            intent.putExtra("BUNDLE", args);
            startActivity(intent);
        }
    }

    public void PlanSkip(View view) {
        MainActivity.exhibitList.remove(destination);
        for(int i = 0; i < MainActivity.viewModel.getCurrentItems().size(); i++){
            if(MainActivity.viewModel.getCurrentItems().get(i).text.equals(destination)){
                MainActivity.viewModel.deleteTodo(MainActivity.viewModel.getCurrentItems().get(i));
                break;
            }
        }
        if (MainActivity.exhibitList.isEmpty()) {
            finish();
        }
        else {
            PlanCalculate planCalculate = new PlanCalculate();
            List<String> Display = planCalculate.extracted(MainActivity.UserCoord, MainActivity.exhibitList);
            destination = planCalculate.getDestination();
            setContentView(R.layout.activity_plan);
            this.directionsView = this.findViewById(R.id.directions_view);
            arrayAdapter = new ArrayAdapter<String>(
                    this,
                    android.R.layout.simple_list_item_1,
                    Display); //extracted returns an ArrayList
            directionsView.setAdapter(arrayAdapter);
        }
    }
}