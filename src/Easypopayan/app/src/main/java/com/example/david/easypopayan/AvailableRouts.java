package com.example.david.easypopayan;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;


//
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.david.easypopayan.model.DetailsRutas;
import com.example.david.easypopayan.model.DetailsStation;
import com.example.david.easypopayan.sqlite.transporte;

import java.util.ArrayList;
import java.util.List;



public class AvailableRouts extends Activity {

    TextView selection;

    OperacionesBaseD BaseDatos;
    ArrayList<Integer> IDRutas = new ArrayList<Integer>();
    //
    private List<DetailsRutas> routesList = new ArrayList<>();
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_routs);


        Intent i = getIntent();
        Bundle b = i.getExtras();
        IDRutas = b.getIntegerArrayList("IdsRutas");
        Log.d("Ids Rutas: ",IDRutas.toString());

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        RoutesAdapter mAdapter = new RoutesAdapter(routesList,this);
        recyclerView.setAdapter(mAdapter);

        BaseDatos = OperacionesBaseD.obtenerInstancia(getApplication());
        prepareRouteData1();


    }


   /* public void onListItemClick(ListView parent, View v, int position, long id) {
        selection.setText(items[position]);
        Log.d("Clientes","Clientes");
        Log.d("Clientes",String.valueOf(position));
    }*/



    private void prepareRouteData1() {
        routesList.clear();
        //Toast.makeText(context,"No Hay rutas Disponibles ",Toast.LENGTH_SHORT).show();
         if(IDRutas.size()>0)
         {
            for (int x = 0; x < IDRutas.size(); x++) {
                DetailsRutas result = BaseDatos.obtenerRutasPorId(String.valueOf(IDRutas.get(x)));
                DetailsStation Inicial = BaseDatos.obtenerStationPorId(String.valueOf(result.estinicial));
                DetailsStation Final = BaseDatos.obtenerStationPorId(String.valueOf(result.estfinal));
                result.setStatioIni(Inicial.Estaciones);
                result.setStatioFin(Final.Estaciones);
                routesList.add(result);
            }
        }

    }

}
