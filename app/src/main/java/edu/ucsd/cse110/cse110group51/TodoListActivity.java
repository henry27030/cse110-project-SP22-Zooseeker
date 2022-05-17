package edu.ucsd.cse110.cse110group51;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jgrapht.alg.shortestpath.DijkstraShortestPath;

import java.sql.Array;
import java.util.ArrayList;

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
        ArrayList<String> Directions = new ArrayList<String>();
        //use exhibitListInFunc as an ArrayList to add and remove without changing exhibitList
        ArrayList<String> exhibitListInFunc = new ArrayList<String>();
        for (String exhibit:MainActivity.exhibitList) {
            exhibitListInFunc.add(exhibit);
        }
        String start = "entrance_exit_gate";
        int shortestExhibit = 0;
        float shortestLength = 0;
        float currentLength = 0;
        //int instructionCount = 1;
        while (exhibitListInFunc.size()!=0) {
            shortestExhibit = 0;
            shortestLength = 0;
            for (int i = 0; i < exhibitListInFunc.size(); i++) {
                currentLength = 0;
                MainActivity.path = DijkstraShortestPath.findPathBetween(MainActivity.g, start, exhibitListInFunc.get(i));
                for (IdentifiedWeightedEdge e : MainActivity.path.getEdgeList()) {
                    currentLength += MainActivity.g.getEdgeWeight(e);
                }
                if (shortestLength == 0 || currentLength < shortestLength) {
                    shortestLength = currentLength;
                    shortestExhibit = i;
                }
            }
            MainActivity.path = DijkstraShortestPath.findPathBetween(MainActivity.g, start, exhibitListInFunc.get(shortestExhibit));

            //add a string of directions to Directions String array
            for (IdentifiedWeightedEdge e : MainActivity.path.getEdgeList()) {
                String strToInsert = "Walk "
                        +
                        MainActivity.g.getEdgeWeight(e) +
                        " Of meters along " +
                        MainActivity.eInfo.get(e.getId()).street +
                        " from " +
                        MainActivity.vInfo.get(MainActivity.g.getEdgeSource(e).toString()).name +
                        " to " +
                        MainActivity.vInfo.get(MainActivity.g.getEdgeTarget(e).toString()).name;
                Directions.add(strToInsert);
            }
            start = exhibitListInFunc.get(shortestExhibit);
            exhibitListInFunc.remove(shortestExhibit);
        }
        // set Directions in MainActivity to Directions
        MainActivity.Directions = Directions;
    }

    public void onPlanDisplayClicked(View view) {
        Intent intent = new Intent (this, PlanActivity.class);
        startActivity(intent);
    }
}