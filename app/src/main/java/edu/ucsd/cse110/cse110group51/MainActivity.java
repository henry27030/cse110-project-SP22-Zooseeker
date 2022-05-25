package edu.ucsd.cse110.cse110group51;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

//import androidx.appcompat.widget.SearchView;
//
//

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private TextView List_btn;

    public static ArrayList<String> exhibitList = new ArrayList<String>();
    public static Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
    public static ArrayList<String> arrayOfTagToDisplay = new ArrayList<String>();
    public static ArrayList<String> Directions = new ArrayList<String>();
    // 1. Load the graph...
    public static Graph<String, IdentifiedWeightedEdge> g;
    public static GraphPath<String, IdentifiedWeightedEdge> path;

    // 2. Load the information about our nodes and edges...
    public static Map<String, ZooData.VertexInfo> vInfo;
    public static Map<String, ZooData.EdgeInfo> eInfo;
    private ArrayAdapter<String> arrayAdapter;

    // ViewModel
    public static TodoListViewModel viewModel;

    private static final int REQUEST_CODE_SPEECH_INPUT = 1000;
    ImageButton mVoiceBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mVoiceBtn = findViewById(R.id.VoiceBtn);
        mVoiceBtn.setOnClickListener(new View.OnClickListener())
        {
            @Override
            public void onClick (View v){
            speak();
        }
        }

        private void speak () {
            //intent to show speech to text dialog
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, value:"Hi speak something");

            //start intent
            try {
                //in there was no error
                //show dialog
                startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT);
            } catch (Exception e) {
                //If there was some error
                //get message of error and show
                Toast.makeText(context:this, text:"" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        //recieve voice input and handle it
        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            switch (requestCode){
                case REQUEST_CODE_SPEECH_INPUT:{
                    if (resultCode == RESULT_OK && null!= data){
                        //get text array from voice intent
                        ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                        //set to text view
                        mTextTv.setText(result.get(0));
                    }
                    break;
                }
            }

    }
        Intent intent = getIntent();
        int Num = intent.getIntExtra("num", 0);
        //this.List_btn =this.findViewById(R.id.list_btn);
        //List_btn.setText("List("+Num+")");

        // initialize viewModel and exhibitlist
        viewModel = new ViewModelProvider(this)
                .get(TodoListViewModel.class);
        List<TodoListItem> list = viewModel.getCurrentItems();
        Log.v("TodolistItemsSize", String.valueOf(list.size()));
        for(TodoListItem item : list){
            if(!MainActivity.exhibitList.contains(item.text)){
                MainActivity.exhibitList.add(item.text);
            }
//            Log.v("GetTodoListItem:", String.join(",", item.text));
        }

        Context context = getApplication().getApplicationContext();

        // 1. Load the graph...
        g = ZooData.loadZooGraphJSON(context, "sample_zoo_graph.json");
        //GraphPath<String, IdentifiedWeightedEdge> path = DijkstraShortestPath.findPathBetween(g, start, goal);

        // 2. Load the information about our nodes and edges...
        vInfo = ZooData.loadVertexInfoJSON(context, "sample_node_info.json");
        eInfo = ZooData.loadEdgeInfoJSON(context, "sample_edge_info.json");

        this.listView = this.findViewById(R.id.list_view);

        //Display onto searchbar tags as keys to value of Nodes
        ArrayList<String> arr = new ArrayList<String>();
        Set<String> keys=vInfo.keySet();
        for (String Nodes: keys) {
            /*
            if(Nodes.equals("entrance_exit_gate") || Nodes.equals("entrance_plaza")){
                continue;
            }

             */
            if (!vInfo.get(Nodes).kind.equals(vInfo.get(Nodes).kind.EXHIBIT)) {
                continue;
            }
            for (String tag:vInfo.get(Nodes).tags) { //vInfo.get(Nodes) returns VertexInfo, .tags has array
                if (map.containsKey(tag) && !map.get(tag).contains(Nodes)) {
                    map.get(tag).add(Nodes);
                }
                else{
                    if(!arrayOfTagToDisplay.contains(tag)){
                        arrayOfTagToDisplay.add(tag);
                    }
                    ArrayList<String> tagNodes = new ArrayList<String>();
                    tagNodes.add(Nodes);
                    map.put(tag, tagNodes);
                }
            }
        }
        arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                arrayOfTagToDisplay);
        listView.setAdapter(arrayAdapter);


        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Intent intent = new Intent(MainActivity.this, ExhibitListActivity.class);
//                intent.putExtra("position", position);
                intent.putExtra("category", arrayAdapter.getItem(position));
//                Log.v("The category name:", arrayAdapter.getItem(position));
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Type here to search");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                arrayAdapter.getFilter().filter(s);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }


    public void onCategoryClicked(View view) {

    }

    public void onMemberClicked(View view) {

    }
    //this method
    public void onPlanButtonClicked(View view) {
        SearchView searchView = findViewById(R.id.action_search);
        searchView.setQuery("", true);
        Intent intent = new Intent (this, TodoListActivity.class);
        startActivity(intent);
    }
}