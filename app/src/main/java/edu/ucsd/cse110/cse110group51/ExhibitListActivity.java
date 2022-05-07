package edu.ucsd.cse110.cse110group51;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ExhibitListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exhibit_list);

        // get position of categories
        Intent intent = getIntent();
        int position = intent.getIntExtra("position", -1);

        

    }

    public void onBackClicked(View view) {
        finish();
    }
}