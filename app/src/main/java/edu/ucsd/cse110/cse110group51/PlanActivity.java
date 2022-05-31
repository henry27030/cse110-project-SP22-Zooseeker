package edu.ucsd.cse110.cse110group51;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PlanActivity extends AppCompatActivity {
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
        Log.v("count", String.valueOf(directionsView.getAdapter().getCount()));
    }

    public void PlanBackButton(View view) {
        finish();
    }


    // The button either shows a preview of the next exhibit or shifts the main set
    // of directions to the next exhibit based on the User's coordinates.
    public void PlanNextButton(View view) {
        ArrayList<String> input = new ArrayList<String>();
//        Log.v("planNextButton", String.join(",", MainActivity.exhibitList));
//        Log.v("planNextButton size", String.valueOf(MainActivity.exhibitList.size()));
        for (String string :MainActivity.exhibitList) {
            if (!string.equals(destination)){
                input.add(string);
            }
        }
        if (!input.isEmpty()) {
            // in the special case that User is at one of the chosen exhibits.
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

    // This button uses arrayAdapter to change
    // the format of directions shown to the User
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

    // This button shifts to a layout that displays the previous exhibit to the user
    // that is from the stack of previousExhibits
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

    // button either skips and deletes the exhibit from our list of exhibits to visit
    // or does nothing based on the number of exhibits in our list of exhibits
    public void PlanSkip(View view) {
        // remove the next exhibit from the exhibitList
        MainActivity.exhibitList.remove(destination);
        for(int i = 0; i < MainActivity.viewModel.getCurrentItems().size(); i++){
            if(MainActivity.viewModel.getCurrentItems().get(i).text.equals(destination)){
                MainActivity.viewModel.deleteTodo(MainActivity.viewModel.getCurrentItems().get(i));
                break;
            }
        }

        // do not skip in an empty exhibitList
        if (MainActivity.exhibitList.isEmpty()) {
            finish();
        }

        // shows display of next exhibit
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