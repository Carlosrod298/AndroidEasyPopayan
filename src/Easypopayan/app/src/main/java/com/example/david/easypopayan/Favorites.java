package com.example.david.easypopayan;

import android.app.Activity;
import android.content.Context;
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

import com.example.david.easypopayan.model.DetailsFavoritos;
import com.example.david.easypopayan.model.DetailsRutas;
import com.example.david.easypopayan.model.DetailsStation;
import com.example.david.easypopayan.sqlite.transporte;

import java.util.ArrayList;
import java.util.List;



public class Favorites extends Activity {

    TextView selection;

    OperacionesBaseD BaseDatos;

    //
    private List<DetailsRutas> FavoList = new ArrayList<>();
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_routs);


        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        RoutesAdapter mAdapter = new RoutesAdapter(FavoList,this);
        recyclerView.setAdapter(mAdapter);

        BaseDatos = OperacionesBaseD.obtenerInstancia(getApplication());
        prepareRouteData();


    }


   /* public void onListItemClick(ListView parent, View v, int position, long id) {
        selection.setText(items[position]);
        Log.d("Clientes","Clientes");
        Log.d("Clientes",String.valueOf(position));
    }*/



    private void prepareRouteData() {
        FavoList.clear();
        Cursor cursor = BaseDatos.obtenerFavoritos();

        if (cursor.moveToFirst()){
            while(!cursor.isAfterLast()){

                long ID = cursor.getInt(cursor.getColumnIndex(transporte.CabFavoritos.ID));
                String Ruta = cursor.getString(cursor.getColumnIndex(transporte.CabFavoritos.Rutas));
                DetailsRutas Rutafavorita = BaseDatos.obtenerRutasPorId(String.valueOf(ID));
                DetailsStation Inicial = BaseDatos.obtenerStationPorId(String.valueOf(Rutafavorita.estinicial));
                DetailsStation Final = BaseDatos.obtenerStationPorId(String.valueOf(Rutafavorita.estfinal));
                Rutafavorita.setStatioIni(Inicial.Estaciones);
                Rutafavorita.setStatioFin(Final.Estaciones);
                Rutafavorita.setRuta(Ruta);
                FavoList.add(Rutafavorita);
                // do what ever you want here
                cursor.moveToNext();
            }
        }
        cursor.close();

    }

}

