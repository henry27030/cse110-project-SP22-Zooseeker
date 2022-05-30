package edu.ucsd.cse110.cse110group51;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MockingActivity extends AppCompatActivity {

    private EditText LatText;
    private EditText LngText;

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
        this.LatText = this.findViewById(R.id.lat);
        this.LngText = this.findViewById(R.id.lng);
        //Check if coordinates are filled
        if(LatText.getText().toString().trim().length() != 0 & LngText.getText().toString().trim().length() != 0) {
            double lat = Double.parseDouble(LatText.getText().toString());
            double lng = Double.parseDouble(LngText.getText().toString());
            MainActivity.UserCoord = Coord.of(lat, lng);
            //Store user location
            MainActivity.sp = getSharedPreferences("PrefFile", MODE_PRIVATE);
            SharedPreferences.Editor editor = MainActivity.sp.edit();
            editor.putLong("lat", Double.doubleToRawLongBits(MainActivity.UserCoord.lat));
            editor.putLong("lng", Double.doubleToRawLongBits(MainActivity.UserCoord.lng));
            editor.commit();
            //latitude, latLng.longitude
        }
    }
}