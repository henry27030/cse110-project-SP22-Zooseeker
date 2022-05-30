package edu.ucsd.cse110.cse110group51;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Stack;

public class TodoListActivity extends AppCompatActivity {
    //Exposed for testing purposes later...
    public RecyclerView recyclerView;

    // location monitor permission checker
    private final LocationPermissionChecker permissionChecker =
            new LocationPermissionChecker(this);


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

        this.newTodoText = this.findViewById(R.id.x_coor);
        this.addTodoButton = this.findViewById(R.id.add_todo_btn);

        addTodoButton.setOnClickListener(this::onAddTodoClicked);

    }

    void onAddTodoClicked(View view) {
        String text = newTodoText.getText().toString();
        newTodoText.setText("");
        MainActivity.viewModel.createTodo(text);
    }

    public void onBackClicked(View view) {
        /*
        Intent intent = new Intent(TodoListActivity.this, MainActivity.class);
        intent.putExtra("num", adapter.getItemCount());
        startActivity(intent);

         */
        finish();
    }

    public void onPlanDisplayClicked(View view) {
        if (MainActivity.UserCoord!=null) {
            IdentifiedWeightedEdge edgeOfUser = SlopeMath.edgeUserIsOn(MainActivity.UserCoord);
            if (SlopeMath.edgeChecker(edgeOfUser, MainActivity.UserCoord)) {
                Intent intent = new Intent(this, PlanActivity.class);
                startActivity(intent);
            }
        }
    }

    //Mock User Location
    public void onMockLocationClicked(View view) {
        Intent intent = new Intent(this, MockingActivity.class);
        startActivity(intent);
    }

    public void ListClear(View view) {
        while (MainActivity.exhibitList.size() != 0) {
            MainActivity.exhibitList.remove(0);
            MainActivity.viewModel.deleteTodo(MainActivity.viewModel.getCurrentItems().get(0));
        }
        MainActivity.previousExhibits = new Stack<String>();
    }

    public void ReadUserLocation(View view) {
        // check location permissions
        if (permissionChecker.ensurePermissions()) return;
        MainActivity.UserCoordLiveUpdateEnabled = true;

        // Listen for location updates
        var provider = LocationManager.GPS_PROVIDER;
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener = new LocationListener() {

            @Override
            public void onLocationChanged(@NonNull Location location) {
                if (MainActivity.UserCoordLiveUpdateEnabled) {
                    if (SlopeMath.WithinZooArea(MainActivity.UserCoord)) {
                        Log.d("location updates", String.format("Location changed: %s", location));
                        MainActivity.UserCoord = Coord.of(location.getLatitude(), location.getLongitude());
                        Log.d("User location changed", MainActivity.UserCoord.toString());

                    } else {
                        // set to entrance coordinate if User is not in the zoo
                        MainActivity.UserCoord = Coord.of(32.73459618734685, -117.14936);
                        Log.d("user is not in the zoo, should be entrance location: ",
                                MainActivity.UserCoord.toString());
                    }
                }
            }

        };
        // ignore this if statement, handled in permissionChecker.ensurePermission()
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(provider, 0, 0f, locationListener);
    }

}