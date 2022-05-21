package edu.ucsd.cse110.cse110group51;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TodoListActivity extends AppCompatActivity {
    //Exposed for testing purposes later...
    public RecyclerView recyclerView;


    private EditText newTodoText;
    private Button addTodoButton;
    public TodoListAdapter adapter = new TodoListAdapter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);

//        viewModel = new ViewModelProvider(this)
//                .get(TodoListViewModel.class);

        adapter.setHasStableIds(true);
        adapter.setOnDeleteClickedHandler(MainActivity.viewModel::deleteTodo);
        MainActivity.viewModel.getTodoListItems().observe(this, adapter::setTodoListItems);

        recyclerView = findViewById(R.id.todo_items);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        this.newTodoText = this.findViewById(R.id.new_todo_text);
        this.addTodoButton = this.findViewById(R.id.add_todo_btn);

        addTodoButton.setOnClickListener(this::onAddTodoClicked);
    }
    void onAddTodoClicked (View view) {
        String text = newTodoText.getText().toString();
        newTodoText.setText("");
        MainActivity.viewModel.createTodo(text);
    }

    public void onBackClicked(View view) {
        Intent intent = new Intent(TodoListActivity.this, MainActivity.class);
        intent.putExtra("num", adapter.getItemCount());
        startActivity(intent);
    }

    public void onPlanCalculateClicked(View view) {
        PlanCalculate planCalculate = new PlanCalculate();
        planCalculate.extracted(MainActivity.start, MainActivity.exhibitList);
    }
/*
    private void extracted() {
        //use exhibitListInFunc as an ArrayList to add and remove without changing exhibitList
        //int instructionCount = 1;

        //add a string of directions to Directions String array
        // set Directions in MainActivity to Directions
        planCalculate.extracted();
    }*/

    public void onPlanDisplayClicked(View view) {
        PlanCalculate planCalculate = new PlanCalculate();
        planCalculate.extracted(MainActivity.start, MainActivity.exhibitList);
        Intent intent = new Intent (this, PlanActivity.class);
        intent.putExtra("Key", planCalculate.getDestination());
        startActivity(intent);
    }
}