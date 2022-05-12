package edu.ucsd.cse110.cse110group51;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
//import androidx.appcompat.widget.SearchView;
import android.widget.SearchView;
import android.widget.TextView;
//
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
//

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private TextView List_btn;

    public static ArrayList<String> exhibitList = new ArrayList<String>();
    public static Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
    public static ArrayList<String> arrayOfTagToDisplay = new ArrayList<String>();
    public static ArrayList<String> Directions;
    // 1. Load the graph...
    public static Graph<String, IdentifiedWeightedEdge> g;
    public static GraphPath<String, IdentifiedWeightedEdge> path;

    // 2. Load the information about our nodes and edges...
    public static Map<String, ZooData.VertexInfo> vInfo;
    public static Map<String, ZooData.EdgeInfo> eInfo;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        int Num = intent.getIntExtra("num", 0);
        this.List_btn =this.findViewById(R.id.list_btn);
        List_btn.setText("List("+Num+")");


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
            for (String tag:vInfo.get(Nodes).tags) { //vInfo.get(Nodes) returns VertexInfo, .tags has array
                if (map.containsKey(tag)) {
                    map.get(tag).add(Nodes);
                }
                else {
                    arrayOfTagToDisplay.add(tag);
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
                intent.putExtra("position", position);
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

    public void OnShowListClicked(View view) {
        SearchView searchView = findViewById(R.id.action_search);
        searchView.setQuery("", true);
        Intent intent = new Intent (this, TodoListActivity.class);
        startActivity(intent);
    }
    //this method
    public void onPlanButtonClicked(View view) {
        /*
        String start = "entrance_exit_gate";
        exhibitList.add("elephant_odyssey");
        exhibitList.add("arctic_foxes");
        int shortestExhibit = 0;
        float shortestLength = 0;
        float currentLength = 0;
        //int instructionCount = 1;
        while (exhibitList.size()!=0) {
            shortestExhibit = 0;
            shortestLength = 0;
            for (int i = 0; i < exhibitList.size(); i++) {
                currentLength = 0;
                path = DijkstraShortestPath.findPathBetween(g, start, exhibitList.get(i));
                for (IdentifiedWeightedEdge e : path.getEdgeList()) {
                    currentLength += g.getEdgeWeight(e);
                }
                if (shortestLength == 0 || currentLength < shortestLength) {
                    shortestLength = currentLength;
                    shortestExhibit = i;
                }
            }
            path = DijkstraShortestPath.findPathBetween(g, start, exhibitList.get(shortestExhibit));
            start=exhibitList.get(shortestExhibit);
            exhibitList.remove(shortestExhibit);
        }
        */
        Intent intent = new Intent (this, TodoListActivity.class);
        startActivity(intent);
    }
}