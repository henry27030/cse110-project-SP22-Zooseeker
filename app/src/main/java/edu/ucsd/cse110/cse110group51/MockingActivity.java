package edu.ucsd.cse110.cse110group51;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MockingActivity extends AppCompatActivity {

    private EditText LatText;
    private EditText LngText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mocking);
    }

    public void onMockBackClicked(View view) {
        finish();
    }

    public void onMockClicked(View view) {
        this.LatText = this.findViewById(R.id.lat);
        this.LngText = this.findViewById(R.id.lng);
        MainActivity.UserCoordLiveUpdateEnabled = false;
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
        }
    }
}