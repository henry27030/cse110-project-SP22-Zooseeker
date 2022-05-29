package edu.ucsd.cse110.cse110group51;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MockingActivity extends AppCompatActivity {

    private EditText xCoorText;  // longitude
    private EditText yCoorText;  // latitude

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
        this.xCoorText = this.findViewById(R.id.x_coor);        // longitude
        this.yCoorText = this.findViewById(R.id.y_coor);        // latitude
        //Check if coordinates are filled
        if(xCoorText.getText().toString().trim().length() != 0 & yCoorText.getText().toString().trim().length() != 0) {
            double xCoor = Double.parseDouble(xCoorText.getText().toString());
            double yCoor = Double.parseDouble(yCoorText.getText().toString());
            MainActivity.UserCoord = Coord.of(yCoor, xCoor);
            //Store user location
            MainActivity.sp = getSharedPreferences("PrefFile", MODE_PRIVATE);
            SharedPreferences.Editor editor = MainActivity.sp.edit();
            editor.putLong("Xcoor", Double.doubleToRawLongBits(MainActivity.UserCoord.lng));
            editor.putLong("Ycoor", Double.doubleToRawLongBits(MainActivity.UserCoord.lat));
            editor.commit();
        }
    }
}