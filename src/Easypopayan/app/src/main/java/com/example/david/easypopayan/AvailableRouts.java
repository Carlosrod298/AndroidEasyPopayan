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
        prepareRouteData();


    }


   /* public void onListItemClick(ListView parent, View v, int position, long id) {
        selection.setText(items[position]);
        Log.d("Clientes","Clientes");
        Log.d("Clientes",String.valueOf(position));
    }*/



    private void prepareRouteData() {
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
        /*
        Cursor cursor = BaseDatos.obtenerRutas();
        if (cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                long ID = cursor.getInt(cursor.getColumnIndex(transporte.CabRutas.ID));
                String Ruta = cursor.getString(cursor.getColumnIndex(transporte.CabRutas.Ruta));
                String Empresa = cursor.getString(cursor.getColumnIndex(transporte.CabRutas.Empresa));
                long id_Ruta = cursor.getInt(cursor.getColumnIndex(transporte.CabRutas.id_ruta));
                float Precio = cursor.getFloat(cursor.getColumnIndex(transporte.CabRutas.PRECIO));
                long EstInicial = cursor.getInt(cursor.getColumnIndex(transporte.CabRutas.EsInicial));
                long EstFinal = cursor.getInt(cursor.getColumnIndex(transporte.CabRutas.EsFinal));
                long Est1 = cursor.getInt(cursor.getColumnIndex(transporte.CabRutas.Es1));
                long Est2 = cursor.getInt(cursor.getColumnIndex(transporte.CabRutas.Es2));
                long Est3 = cursor.getInt(cursor.getColumnIndex(transporte.CabRutas.Es3));
                long Est4 = cursor.getInt(cursor.getColumnIndex(transporte.CabRutas.Es4));
                DetailsStation Inicial = BaseDatos.obtenerStationPorId(String.valueOf(EstInicial));
                DetailsStation Final = BaseDatos.obtenerStationPorId(String.valueOf(EstFinal));

                DetailsRutas result = new DetailsRutas(ID,Ruta,Empresa,id_Ruta,Precio,EstInicial,
                        EstFinal,Est1,Est2,Est3,Est4);
                result.setStatioIni(Inicial.Estaciones);
                result.setStatioFin(Final.Estaciones);
                routesList.add(result);
                // do what ever you want here
                cursor.moveToNext();
            }
        }
        cursor.close();*/

        /*
        DetailsRutas routeSelect = new DetailsRutas(1,"R 1", "Tr Pubenza",
                5,1500,2,1);
        routesList.add(routeSelect);
        routeSelect = new DetailsRutas(2,"R 2", "Tr Pubenza",
                5,1500,1,2);
        routesList.add(routeSelect);

        routeSelect = new DetailsRutas(3,"R 3", "Tr Pubenza",
                5,1500,2,1);
        routesList.add(routeSelect);*/

        //mAdapter.notifyDataSetChanged();
    }

}
