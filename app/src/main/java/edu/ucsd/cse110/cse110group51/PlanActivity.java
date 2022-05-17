package edu.ucsd.cse110.cse110group51;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class PlanActivity extends AppCompatActivity {
    private ListView directionsView;
    private ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);
        this.directionsView = this.findViewById(R.id.directions_view);
        arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                MainActivity.Directions);
        directionsView.setAdapter(arrayAdapter);
    }

    public void PlanBackButton(View view) {
        finish();
    }
}