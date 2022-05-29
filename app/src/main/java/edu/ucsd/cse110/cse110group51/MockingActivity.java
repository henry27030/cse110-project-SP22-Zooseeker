package edu.ucsd.cse110.cse110group51;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MockingActivity extends AppCompatActivity {

    private EditText xCoorText;
    private EditText yCoorText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mocking);
    }

    public void onMockBackClicked(View view) {
        Intent intent = new Intent (this, TodoListActivity.class);
        startActivity(intent);
    }

    public void onMockClicked(View view) {
        this.xCoorText = this.findViewById(R.id.x_coor);
        this.yCoorText = this.findViewById(R.id.y_coor);
        //Check if coordinates are filled
        if(xCoorText.getText().toString().trim().length() != 0 & yCoorText.getText().toString().trim().length() != 0) {
            double xCoor = Double.parseDouble(xCoorText.getText().toString());
            double yCoor = Double.parseDouble(yCoorText.getText().toString());
            MainActivity.UserCoord = Coord.of(xCoor, yCoor);
        }
    }
}