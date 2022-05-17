package edu.ucsd.cse110.cse110group51;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ExhibitListActivity extends AppCompatActivity {

    public RecyclerView recyclerView;

//    private TodoListViewModel viewModel;
    private EditText newTodoText;
    private Button addTodoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exhibit_list);

        // get position of categories
        Intent intent = getIntent();
        int position = intent.getIntExtra("position", -1);
        String category = MainActivity.arrayOfTagToDisplay.get(position);

        // use Log.v to check if the position I clicked is the right one
//        Log.v("ExhibitListActivity", String.valueOf(position));
//        Log.v("ExhibitListActivity category", MainActivity.arrayOfTagToDisplay.get(position));


//        viewModel = new ViewModelProvider(this)
//                .get(TodoListViewModel.class);

        ExhibitAdapter adapter = new ExhibitAdapter();
        adapter.setHasStableIds(true);
        adapter.setOnCheckBoxClickedHandler(MainActivity.viewModel::toggleCompleted);
        // adapter needs to have todolistItem list
        List<TodoListItem> list = new ArrayList<>();
        int i = 0;
        for(String exhibit : MainActivity.map.get(category)){
            i++;
            list.add(new TodoListItem(exhibit, i));
        }
        adapter.setTodoListItems(list);
        Log.v("ExhibitListActivity:",String.join(",", MainActivity.exhibitList));

        // potential issue with this line, not sure if only can be used on TodoListActivity
//        viewModel.getTodoListItems().observe(this, adapter::setTodoListItems);

        recyclerView = findViewById(R.id.exhibit_list_items);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }

    public void onBackClicked(View view) {
        finish();
    }
}