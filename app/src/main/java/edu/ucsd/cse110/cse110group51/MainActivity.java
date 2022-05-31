package edu.ucsd.cse110.cse110group51;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    public static SharedPreferences sp; // Used for saving UserCoord after exiting app
    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;

    // data structures to store exhibits and their information
    public static ArrayList<String> exhibitList = new ArrayList<String>(); // stores list of exhibits to visit
    public static Stack<String> previousExhibits = new Stack<String>(); // saves previous exhibits
    public static Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>(); // (Node id, array of node tags)
    public static ArrayList<String> arrayOfTagToDisplay = new ArrayList<String>(); // saves tags to display in dropdown list
    public static Map<String , Pair<Double, Double>> edgeSlopeBInfo; // save (String edge, (slope, b)) for each edge

    // UserCoordinates and directions for user
    public static boolean briefDirections;// = false; // boolean for determining brief/descriptive directions
    public static Coord UserCoord; // saves User's coordinates
    public static boolean UserCoordLiveUpdateEnabled = false; // when true, periodically refresh User's live location

    // 1. Load the graph...
    public static Graph<String, IdentifiedWeightedEdge> g;
    public static GraphPath<String, IdentifiedWeightedEdge> path;
    // 2. Load the information about our nodes and edges...
    public static Map<String, ZooData.VertexInfo> vInfo;
    public static Map<String, ZooData.EdgeInfo> eInfo;
    // ViewModel
    public static TodoListViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        briefDirections = false;

        // initialize viewModel and exhibitList
        viewModel = new ViewModelProvider(this)
                .get(TodoListViewModel.class);
        List<TodoListItem> list = viewModel.getCurrentItems();
        Log.v("TodolistItemsSize", String.valueOf(list.size()));
        for (TodoListItem item : list) {
            if (!MainActivity.exhibitList.contains(item.text)) {
                MainActivity.exhibitList.add(item.text);
            }
        }

        // initialize the graph, edges, and vertices
        Context context = getApplication().getApplicationContext();

        // 1. Load the graph...
        g = ZooData.loadZooGraphJSON(context, "zoo_graph.json");

        // 2. Load the information about our nodes and edges...
        vInfo = ZooData.loadVertexInfoJSON(context, "exhibit_info.json");
        eInfo = ZooData.loadEdgeInfoJSON(context, "trail_info.json");
        edgeSlopeBInfo = new HashMap<String, Pair<Double, Double>>();
        this.listView = this.findViewById(R.id.list_view);

        //Display onto searchbar tags as keys to value of Nodes
        Set<String> keys = vInfo.keySet();
        for (String Nodes : keys) {
            // initializing LatLng coords for each node
            if (vInfo.get(Nodes).coords == null) {

                if (vInfo.get(Nodes).lat == 0 && vInfo.get(Nodes).lng == 0) {
                    vInfo.get(Nodes).lat = vInfo.get(vInfo.get(Nodes).group_id).lat;
                    vInfo.get(Nodes).lng = vInfo.get(vInfo.get(Nodes).group_id).lng;
                }

                vInfo.get(Nodes).coords = Coord.of(vInfo.get(Nodes).lat, vInfo.get(Nodes).lng);
            }

            if (!vInfo.get(Nodes).kind.equals(vInfo.get(Nodes).kind.EXHIBIT)) {
                continue;
            }
            for (String tag : vInfo.get(Nodes).tags) { //vInfo.get(Nodes) returns VertexInfo, .tags has array
                if (map.containsKey(tag) && !map.get(tag).contains(Nodes)) {
                    map.get(tag).add(Nodes);
                } else {
                    if (!arrayOfTagToDisplay.contains(tag)) {
                        arrayOfTagToDisplay.add(tag);
                    }
                    ArrayList<String> tagNodes = new ArrayList<String>();
                    tagNodes.add(Nodes);
                    map.put(tag, tagNodes);
                }
            }
        }

        // Initialize edgeSlopeBInfo to hold all edge IDs and their respective slopes
        Set<String> EdgeKeys = eInfo.keySet();
        for (String Edge : EdgeKeys) {
            Set<IdentifiedWeightedEdge> edgeList;
            IdentifiedWeightedEdge edgeToIdentify;
            String Source;
            String Target;
            edgeList = g.edgeSet();
            for (IdentifiedWeightedEdge Edges : edgeList) {
                if (Edges.getId().equals(Edge)) {
                    edgeToIdentify = Edges;
                    Source = g.getEdgeSource(edgeToIdentify);
                    Target = g.getEdgeTarget(edgeToIdentify);
                    edgeSlopeBInfo.put(Edge, SlopeMath.returnSlopeB(Source, Target));
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
                intent.putExtra("category", arrayAdapter.getItem(position));
                startActivity(intent);
            }
        });

        //Initialize UserCoord information/persistence
        sp = getSharedPreferences("PrefFile", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        if (!sp.contains("lat")||!sp.contains("lng")) {
            UserCoord = Coord.of(MainActivity.vInfo.get("entrance_exit_gate").coords.lat, MainActivity.vInfo.get("entrance_exit_gate").coords.lng);
        }else{
            UserCoord = Coord.of(Double.longBitsToDouble(sp.getLong("lat", Double.doubleToLongBits(0))),
                    Double.longBitsToDouble(sp.getLong("lng", Double.doubleToLongBits(0))));
        }

    }

    // this method is used to set the Search Bar search function
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

    //this method is used to transition to the TodoListActivity which contains
    //the list of exhibits that user selected and other buttons
    public void onPlanButtonClicked(View view) {
        SearchView searchView = findViewById(R.id.action_search);
        searchView.setQuery("", true);
        Intent intent = new Intent (this, TodoListActivity.class);
        startActivity(intent);
    }
}