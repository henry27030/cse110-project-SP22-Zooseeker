package edu.ucsd.cse110.cse110group51;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jgrapht.alg.shortestpath.DijkstraShortestPath;

public class TodoListActivity extends AppCompatActivity {
    //Exposed for testing purposes later...
    public RecyclerView recyclerView;
    private TodoListViewModel viewModel;

    private EditText newTodoText;
    private Button addTodoButton;
    public TodoListAdapter adapter = new TodoListAdapter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);

        viewModel = new ViewModelProvider(this)
                .get(TodoListViewModel.class);

        adapter.setHasStableIds(true);
        adapter.setOnDeleteClickedHandler(viewModel::deleteTodo);
        viewModel.getTodoListItems().observe(this, adapter::setTodoListItems);

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
        viewModel.createTodo(text);
    }

    public void onBackClicked(View view) {
        Intent intent = new Intent(TodoListActivity.this, MainActivity.class);
        intent.putExtra("num", adapter.getItemCount());
        startActivity(intent);
    }

    public void onPlanCalculateClicked(View view) {
        String start = "entrance_exit_gate";
        MainActivity.exhibitList.add("elephant_odyssey");
        MainActivity.exhibitList.add("arctic_foxes");
        int shortestExhibit = 0;
        float shortestLength = 0;
        float currentLength = 0;
        //int instructionCount = 1;
        while (MainActivity.exhibitList.size()!=0) {
            shortestExhibit = 0;
            shortestLength = 0;
            for (int i = 0; i < MainActivity.exhibitList.size(); i++) {
                currentLength = 0;
                MainActivity.path = DijkstraShortestPath.findPathBetween(MainActivity.g, start, MainActivity.exhibitList.get(i));
                for (IdentifiedWeightedEdge e : MainActivity.path.getEdgeList()) {
                    currentLength += MainActivity.g.getEdgeWeight(e);
                }
                if (shortestLength == 0 || currentLength < shortestLength) {
                    shortestLength = currentLength;
                    shortestExhibit = i;
                }
            }
            MainActivity.path = DijkstraShortestPath.findPathBetween(MainActivity.g, start, MainActivity.exhibitList.get(shortestExhibit));
            start = MainActivity.exhibitList.get(shortestExhibit);
            MainActivity.exhibitList.remove(shortestExhibit);
        }
    }

    public void onPlanDisplayClicked(View view) {
    }
}