package com.example.david.easypopayan;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.david.easypopayan.model.DetailsRutas;
import com.example.david.easypopayan.model.DetailsStation;
import com.example.david.easypopayan.sqlite.transporte;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemSelectedListener
{

    boolean addingNew;
    Button viewRoutesBnt;
    ImageView FavoritosBnt;
    ImageView QrIcon;
    ArrayAdapter spinerini;
    ArrayAdapter spinerfin;
    public Spinner spin;
    public Spinner spin2;



    OperacionesBaseD BaseDatos;
    private List<DetailsStation> StationList = new ArrayList<>();
    final List<String> list = new ArrayList<String>();
    //Variable para informacion
    static final private int REMOVE_TODO = Menu.FIRST;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewRoutesBnt = (Button)findViewById(R.id.viewRoutes);
        viewRoutesBnt.setOnClickListener(this);

        FavoritosBnt = (ImageView)findViewById(R.id.Favorite);
        FavoritosBnt.setOnClickListener(this);

        QrIcon = (ImageView)findViewById(R.id.QrIcon);
        QrIcon.setOnClickListener(this);


        //Creacion del menu contextual
        //registerForContextMenu(myListView);

        // open data base
        Log.d("Estaciones","Estaciones");
        BaseDatos = OperacionesBaseD.obtenerInstancia(getApplication());
        //long infor = BaseDatos.insertarFavoritos(new DetailsFavoritos(1,"Casa"));
        //boolean answer = BaseDatos.EliminarRuta("1");
        //answer = BaseDatos.EliminarEstacion("2");
        /*
        long infor = BaseDatos.insertarEstacion(new DetailsStation
                (1,2.459178,-76.593670,"Campanario",5));
        infor = BaseDatos.insertarEstacion(new DetailsStation
                (2,2.476087,-76.570584,"Pisoje",5));
        infor = BaseDatos.insertarEstacion(new DetailsStation
                (3,2.447131,-76.598143,"UniCauca Tulcan",5));
        infor = BaseDatos.insertarEstacion(new DetailsStation
                (4,2.455350,-76.597140,"Hotel San Martin",5));
        infor = BaseDatos.insertarEstacion(new DetailsStation
                (5,2.455247,-76.592215,"Estadio",5));
        infor = BaseDatos.insertarEstacion(new DetailsStation
                (6,2.456335,-76.591459,"Virgen de los Hoyos",5));
        infor = BaseDatos.insertarEstacion(new DetailsStation
                (7,2.437551,-76.618587,"Hospital Susana Lopez",5));
        infor = BaseDatos.insertarEstacion(new DetailsStation
                (8,2.430668,-76.610773,"Colegio los Comuneros",5));
        infor = BaseDatos.insertarEstacion(new DetailsStation
                (9,2.453044,-76.601943,"Restaurante Carantanta",5));
        infor = BaseDatos.insertarEstacion(new DetailsStation
                (10,2.452411,-76.603518,"Parque el Quijote",5));
        infor = BaseDatos.insertarEstacion(new DetailsStation
                (11,2.448353,-76.603279,"Parque Carlos Alban",5));
        infor = BaseDatos.insertarEstacion(new DetailsStation
                (12,2.444696,-76.605097,"Puente Humilladero",5));
        infor = BaseDatos.insertarEstacion(new DetailsStation
                (13,2.434734,-76.611462,"I.E. Don Bosco",5));
        infor = BaseDatos.insertarEstacion(new DetailsStation
                (14,2.434102,-76.606430,"Parque Alfonso Lopez",5));*/

/*
        infor = BaseDatos.insertarRutas(new DetailsRutas(1,"Ruta1",
                "TransLibertad",5,1600,7,14,3,2,4,9));
        infor = BaseDatos.insertarRutas(new DetailsRutas(2,"Ruta2",
                "TransLibertad",5,1600,2,13,4,7,5,10));
        infor = BaseDatos.insertarRutas(new DetailsRutas(3,"Ruta3",
                "TransLibertad",5,1600,2,11,6,5,4,8));
        infor = BaseDatos.insertarRutas(new DetailsRutas(4,"Ruta4",
                "TransLibertad",5,1600,7,13,2,3,4,7));
        infor = BaseDatos.insertarRutas(new DetailsRutas(5,"Ruta5",
                "TransLibertad",5,1600,7,10,5,3,6,4));
        infor = BaseDatos.insertarRutas(new DetailsRutas(6,"Ruta3",
                "TransTambo",5,1600,2,11,6,5,4,8));
        infor = BaseDatos.insertarRutas(new DetailsRutas(7,"Ruta4",
                "TransTambo",5,1600,7,13,2,3,4,7));
        infor = BaseDatos.insertarRutas(new DetailsRutas(8,"Ruta5",
                "TransTambo",5,1600,7,10,5,3,6,4));*/

        /*DatabaseUtils.dumpCursor(BaseDatos.obtenerEstaciones());
        DatabaseUtils.dumpCursor(BaseDatos.obtenerRutas());
        DatabaseUtils.dumpCursor(BaseDatos.obtenerFavoritos());*/

        Log.d("Estaciones","Base de datos");
        prepareStations(BaseDatos.obtenerEstaciones());
        //new TareaPruebaDatos().execute();


        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        spin = (Spinner) findViewById(R.id.startList);
        spin.setOnItemSelectedListener(this);
        //Creating the ArrayAdapter instance having the bank name list
        spinerini = new ArrayAdapter(this,android.R.layout.simple_spinner_item,list);
        spinerini.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(spinerini);

        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        spin2 = (Spinner) findViewById(R.id.finalList);
        spin2.setOnItemSelectedListener(this);
        //Creating the ArrayAdapter instance having the bank name list
        spinerfin = new ArrayAdapter(this,android.R.layout.simple_spinner_item,list);
        spinerfin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin2.setAdapter(spinerfin);
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position,long id) {
        Toast.makeText(getApplicationContext(), list.get(position), Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
    // TODO Auto-generated method stub

    }


    //menu desplegable de ayuda
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_add, menu);
        return true;
    }
    //creacion del objeto

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,

                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle(R.string.menuTitle);
        menu.add(0, REMOVE_TODO, Menu.NONE, R.string.help);
    }




    @Override
    public void onClick(View view) {
        if(view == viewRoutesBnt){
            int posini = spin.getSelectedItemPosition();
            int posfin = spin2.getSelectedItemPosition();
            Cursor cursor = BaseDatos.obtenerRutasPorStations(posini+1,posfin+1);
            ArrayList<Long> IDRutas = new ArrayList<Long>();

            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    long ID = cursor.getInt(cursor.getColumnIndex(transporte.CabEstaciones.ID));
                    IDRutas.add(ID);
                    cursor.moveToNext();
                }
            }
            if(IDRutas.size()==0) {
                new AlertDialog.Builder(this)
                        .setTitle("Mensaje")
                        .setMessage("No hay Rutas Disponibles")
                        //.setIcon(R.drawable.ok)
                        .setNeutralButton("Cerrar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dlg, int sumthin) {
                                // do nothing -- it will close on its own
                            }
                        })
                        .show();
            }
            else {
                Intent i = new Intent(this, AvailableRouts.class);
                i.setClassName("com.example.david.easypopayan", "com.example.david.easypopayan.AvailableRouts");
                i.putExtra("IdsRutas", IDRutas);
                startActivity(i);
            }
        }
        if(view == FavoritosBnt){
            Intent i = new Intent(this, Favorites.class);
            i.setClassName("com.example.david.easypopayan","com.example.david.easypopayan.Favorites");
            startActivity(i);
        }
        if(view == QrIcon){
            addQR();
        }
    }




    //Guardar configuraciones
    protected void onSaveInstanceState(Bundle instanceState) {
        super.onSaveInstanceState(instanceState);
        //instanceState.putInt("visualState", myEditText.getVisibility());
    }
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
       // super.onRestoreInstanceState(savedInstanceState);
       // if(savedInstanceState.getInt("visualState") == View.VISIBLE){
       //     viewRoutesBnt.setVisibility(View.VISIBLE);
       //     viewRoutesBnt.setVisibility(View.VISIBLE);
       // }
    }


    private void prepareStations(Cursor cursor) {

        StationList.clear();
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                long ID = cursor.getInt(cursor.getColumnIndex(transporte.CabEstaciones.ID));
                double Latitud = cursor.getDouble(cursor.getColumnIndex(transporte.CabEstaciones.Latitud));
                double Longitud = cursor.getDouble(cursor.getColumnIndex(transporte.CabEstaciones.Longitud));
                String Estacion = cursor.getString(cursor.getColumnIndex(transporte.CabEstaciones.Estaciones));
                long ID_estacion = cursor.getInt(cursor.getColumnIndex(transporte.CabEstaciones.ID_estacion));
                DetailsStation result = new DetailsStation(ID,Latitud,Longitud,Estacion,ID_estacion);
                StationList.add(result);
                list.add(Estacion);
                cursor.moveToNext();
            }
        }
    }


    private void addQR() {

        addingNew = true;
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.initiateScan();

    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

        if (resultCode == RESULT_OK) {
            IntentResult scanResult = IntentIntegrator.parseActivityResult(
                    requestCode, resultCode, intent);
            if (scanResult != null) {
            // Handle successful scan
                String contents = scanResult.getContents();
                list.add(0, contents);
                //cancelAdd();
            }
        } else if (resultCode == RESULT_CANCELED) {
            Toast.makeText(this, R.string.qrcode,Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        // Close the database
        BaseDatos.Closedatabase();
    }

}
