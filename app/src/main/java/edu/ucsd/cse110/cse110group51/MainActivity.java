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

//import androidx.appcompat.widget.SearchView;
//
//

public class MainActivity extends AppCompatActivity {
    public static SharedPreferences sp;

    private ListView listView;
    private TextView List_btn;

    //public static String start = "entrance_exit_gate";
    public static ArrayList<String> exhibitList = new ArrayList<String>();

    public static Stack<String> previousExhibits = new Stack<String>(); // saves previous exhibits
    public static Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
    public static ArrayList<String> arrayOfTagToDisplay = new ArrayList<String>();

    // boolean for determining brief/descriptive directions
    public static boolean briefDirections;
    // save (String edge, (slope, b)) for each edge
    public static Map<String , Pair<Double, Double>> edgeSlopeBInfo;

    //UserNode for testing
    public static Coord UserCoord;

    //public static ArrayList<String> Directions = new ArrayList<String>();
    // 1. Load the graph...
    public static Graph<String, IdentifiedWeightedEdge> g;
    public static GraphPath<String, IdentifiedWeightedEdge> path;

    // 2. Load the information about our nodes and edges...
    public static Map<String, ZooData.VertexInfo> vInfo;
    public static Map<String, ZooData.EdgeInfo> eInfo;
    private ArrayAdapter<String> arrayAdapter;

    // ViewModel
    public static TodoListViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Intent intent = getIntent();
        //int Num = intent.getIntExtra("num", 0);
        //this.List_btn =this.findViewById(R.id.list_btn);
        //List_btn.setText("List("+Num+")");
        briefDirections = false; //initialize it to false


        //initialize viewModel and exhibitlist
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
        //g = ZooData.loadZooGraphJSON(context, "sample_zoo_graph.json");
        g = ZooData.loadZooGraphJSON(context, "zoo_graph.json");
        //GraphPath<String, IdentifiedWeightedEdge> path = DijkstraShortestPath.findPathBetween(g, start, goal);

        // 2. Load the information about our nodes and edges...
        //vInfo = ZooData.loadVertexInfoJSON(context, "sample_node_info.json");
        vInfo = ZooData.loadVertexInfoJSON(context, "exhibit_info.json");
        //eInfo = ZooData.loadEdgeInfoJSON(context, "sample_edge_info.json");
        eInfo = ZooData.loadEdgeInfoJSON(context, "trail_info.json");
        edgeSlopeBInfo = new HashMap<String , Pair<Double, Double>>();
        this.listView = this.findViewById(R.id.list_view);

        //Display onto searchbar tags as keys to value of Nodes
        //ArrayList<String> arr = new ArrayList<String>();
        Set<String> keys=vInfo.keySet();
        for (String Nodes: keys) {
            // initializing LatLng coords for each node
            if (vInfo.get(Nodes).coords == null) {

                if (vInfo.get(Nodes).lat == 0 && vInfo.get(Nodes).lng == 0) {
                    vInfo.get(Nodes).lat = vInfo.get(vInfo.get(Nodes).group_id).lat;
                    vInfo.get(Nodes).lng = vInfo.get(vInfo.get(Nodes).group_id).lng;
                }

                //vInfo.get(Nodes).coords = new LatLng(vInfo.get(Nodes).lat, vInfo.get(Nodes).lng);
                vInfo.get(Nodes).coords = Coord.of(vInfo.get(Nodes).lat, vInfo.get(Nodes).lng);
            }

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
        Set<String> EdgeKeys = eInfo.keySet();
        for (String Edge: EdgeKeys) {
            Set<IdentifiedWeightedEdge> edgeList;
            IdentifiedWeightedEdge edgeToIdentify;
            String Source;
            String Target;
            edgeList = g.edgeSet();
            for (IdentifiedWeightedEdge Edges: edgeList) {
                //if (Edges.getId().equals(eInfo.get(Edge).id)) {
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
//                intent.putExtra("position", position);
                intent.putExtra("category", arrayAdapter.getItem(position));
//                Log.v("The category name:", arrayAdapter.getItem(position));
                startActivity(intent);
            }
        });
        //testing
        //UserCoord = Coord.of(32.74708169, -117.1628942); //midpoint between flamingo and capuchin, IdentifiedEdgeWeight id = capuchin_to_hippo_monkey


        sp = getSharedPreferences("PrefFile", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        if (!sp.contains("Xcoor")||!sp.contains("Ycoor")) {
            UserCoord = Coord.of(MainActivity.vInfo.get("flamingo").coords.lat, MainActivity.vInfo.get("flamingo").coords.lng);
            Log.v("flamingo location",
                    String.valueOf(MainActivity.vInfo.get("flamingo").coords.lat) + ", "+ String.valueOf(MainActivity.vInfo.get("flamingo").coords.lng));
        }else{
            UserCoord = Coord.of(Double.longBitsToDouble(sp.getLong("Ycoor", Double.doubleToLongBits(0))),
                    Double.longBitsToDouble(sp.getLong("Xcoor", Double.doubleToLongBits(0))));
        }

        /*
        ZooData.VertexInfo userInfo = new ZooData.VertexInfo();
        userInfo.id = "User";
        userInfo.name = "User";
        userInfo.kind = ZooData.VertexInfo.Kind.EXHIBIT;
        vInfo.put("User", userInfo);

        ZooData.EdgeInfo userInfo2 = new ZooData.EdgeInfo();
        userInfo2.id = "User";
        userInfo2.street="test street";
        eInfo.put("User", userInfo2);
        g.addVertex("User");

        IdentifiedWeightedEdge tester;
        IdentifiedWeightedEdge newEdge1 = new IdentifiedWeightedEdge();
        IdentifiedWeightedEdge newEdge2 = new IdentifiedWeightedEdge();

        tester=g.removeEdge("flamingo", "capuchin");
        newEdge1= (IdentifiedWeightedEdge) tester.clone();
        newEdge2= (IdentifiedWeightedEdge) tester.clone();
        g.addEdge("flamingo", "User", newEdge1);
        g.addEdge("User", "capuchin", newEdge2);
        g.setEdgeWeight("flamingo", "User", 100);
        g.setEdgeWeight("User", "capuchin", 100);

 */

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