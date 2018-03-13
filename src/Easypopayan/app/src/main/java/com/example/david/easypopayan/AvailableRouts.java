package com.example.david.easypopayan;

import android.app.Activity;
import android.os.Bundle;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;


//
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;



public class AvailableRouts extends Activity {

    TextView selection;
    String[] items={"Pub1", "Pub2", "Pub3", "Pub4", "Tambo1",
            "Tambo2", "Tambo3", "Tambo4"};

    //
    private List<Route> routesList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RoutesAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_routs);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new RoutesAdapter(routesList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareMovieData();

       // setListAdapter(new ArrayAdapter<String>(this,
         //       android.R.layout.simple_list_item_1, items));
        //selection = (TextView) findViewById(R.id.selection);

    }

    public void onListItemClick(ListView parent, View v, int position, long id) {
        selection.setText(items[position]);
    }

    private void prepareMovieData() {
        Route routeSelect = new Route("Trans Pubenza 1", "Ver Detalles", "5 Min");
        routesList.add(routeSelect);

        routeSelect = new Route("Trans Pubenza 2", "Ver Detalles", "15 Min");
        routesList.add(routeSelect);

        routeSelect = new Route("Trans Tambo 1", "Ver Detalles", "5 Min");
        routesList.add(routeSelect);

        routeSelect = new Route("Trans Libertad 1", "Ver Detalles", "10 Min");
        routesList.add(routeSelect);



        mAdapter.notifyDataSetChanged();
    }

}
