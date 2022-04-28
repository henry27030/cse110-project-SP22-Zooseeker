package edu.ucsd.cse110.cse110group51;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText searchAnimalText;
    private Button searchButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.searchButton = this.findViewById(R.id.search_btn);
        this.searchAnimalText = this.findViewById(R.id.searchbar);
        searchButton.setOnClickListener(this::onSearchClicked);
    }

    public void onSearchClicked(View view){
        TextView results = this.findViewById(R.id.results);
        results.setText("not implemented");
        String text = searchAnimalText.getText().toString();
        if(AnimalData.getText()==text){
            TextView result = this.findViewById(R.id.results);
            result.setText(text);
        }
    }

    public void onCategoryClicked(View view) {

    }

    public void onMemberClicked(View view) {

    }

    public void onListClicked(View view) {

    }
}