package com.example.david.easypopayan;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
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
import android.widget.Spinner;
import android.widget.Toast;

import com.example.david.easypopayan.model.DetailsRutas;
import com.example.david.easypopayan.model.DetailsStation;
import com.example.david.easypopayan.sqlite.transporte;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


public class MainActivity extends AppCompatActivity
        implements View.OnClickListener,
        AdapterView.OnItemSelectedListener,
        OnMapReadyCallback,
        GoogleMap.OnMyLocationClickListener,
        GoogleMap.OnMyLocationButtonClickListener {

    boolean addingNew;
    Button viewRoutesBnt;
    ImageView FavoritosBnt;
    ImageView QrIcon;
    ArrayAdapter spinerini;
    ArrayAdapter spinerfin;
    public Spinner spinnerInitial;
    public Spinner spinnerFinal;
    private GoogleMap mMap;
    OperacionesBaseD BaseDatos;

    private List<DetailsStation> StationList = new ArrayList<>();
    private List<DetailsStation> StationURL = new ArrayList<>();
    private List<DetailsRutas> RoutesURL = new ArrayList<>();

    final List<String> list = new ArrayList<String>();
    List<Marker> makersMapList= new ArrayList<>();

    private List<DetailsStation> StationList2 = new ArrayList<>();
    //Variable para informacion
    static final private int REMOVE_TODO = Menu.FIRST;

    int height = 80;
    int width = 80;
    Bitmap bitMap;
    Bitmap smallMarkerBusStop;
    Bitmap smallMarkerBusInit;
    Bitmap smallMarkerBusFinal;

    private String URLStation;
    private String URLRoutes;
    private HttpRequest reqStation;
    private HttpRequest reqRoutes;



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

        // Make Bitmap to bus stop maker
        bitMap = ((BitmapDrawable) getResources().getDrawable(R.drawable.bus_stop, null)).getBitmap();
        smallMarkerBusStop = Bitmap.createScaledBitmap(bitMap, width, height, false);

        // Make Bitmap to bus init maker
        bitMap = ((BitmapDrawable) getResources().getDrawable(R.drawable.bus_init, null)).getBitmap();
        smallMarkerBusInit = Bitmap.createScaledBitmap(bitMap, width, height, false);

        // Make Bitmap to bus init maker
        bitMap = ((BitmapDrawable) getResources().getDrawable(R.drawable.bus_final, null)).getBitmap();
        smallMarkerBusFinal = Bitmap.createScaledBitmap(bitMap, width, height, false);

        // conexion con el servidor
        URLStation  = getString(R.string.urlStation);
        URLRoutes = getString(R.string.urlRoutes);

        //Creacion del menu contextual
        //registerForContextMenu(myListView);

        // open data base
        Log.d("Estaciones","Estaciones");
        BaseDatos = OperacionesBaseD.obtenerInstancia(getApplication());
        //BaseDatos.obtenerEstaciones();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        spinnerInitial = (Spinner) findViewById(R.id.startList);
        spinnerInitial.setOnItemSelectedListener(this);
        //Creating the ArrayAdapter instance having the bank name list
        spinerini = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item,
                list);
        spinerini.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinnerInitial.setAdapter(spinerini);

        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        spinnerFinal = (Spinner) findViewById(R.id.finalList);
        spinnerFinal.setOnItemSelectedListener(this);
        //Creating the ArrayAdapter instance having the bank name list
        spinerfin = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item,
                list);
        spinerfin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinnerFinal.setAdapter(spinerfin);
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View view, int position,long id) {


        for (Marker marker : makersMapList
                ) {
            marker.setIcon(BitmapDescriptorFactory.fromBitmap(smallMarkerBusStop));
        }

        int posfin = spinnerFinal.getSelectedItemPosition();
        int posini = spinnerInitial.getSelectedItemPosition();

        if (arg0 == spinnerInitial) {
            Toast.makeText(getApplicationContext(), "Initial station: " + spinnerInitial.getSelectedItem().toString(), Toast.LENGTH_LONG).show();
            makersMapList.get(posini).showInfoWindow();

        } else if (arg0 == spinnerFinal) {

            Toast.makeText(getApplicationContext(), "End station: " + spinnerInitial.getSelectedItem().toString(), Toast.LENGTH_LONG).show();
            makersMapList.get(posfin).showInfoWindow();
        }
        makersMapList.get(posini).setIcon(BitmapDescriptorFactory.fromBitmap(smallMarkerBusInit));
        makersMapList.get(posfin).setIcon(BitmapDescriptorFactory.fromBitmap(smallMarkerBusFinal));
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
            int posini = spinnerInitial.getSelectedItemPosition();
            int posfin = spinnerFinal.getSelectedItemPosition();

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
            Cursor cursor = BaseDatos.obtenerFavoritos();

            if(cursor.getCount()==0) {
                new AlertDialog.Builder(this)
                        .setTitle("Mensaje")
                        .setMessage("No tienes favoritos")
                        //.setIcon(R.drawable.ok)
                        .setNeutralButton("Cerrar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dlg, int sumthin) {
                                // do nothing -- it will close on its own
                            }
                        })
                        .show();
            }
            else {
                Intent i = new Intent(this, Favorites.class);
                i.setClassName("com.example.david.easypopayan", "com.example.david.easypopayan.Favorites");
                startActivity(i);
            }
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
    public void prepareStations(Cursor cursor) {

        StationList.clear();
        list.clear();
        for (Marker marker: makersMapList
                ) {
            marker.remove();
        }
        makersMapList.clear();

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                long ID = cursor.getInt(cursor.getColumnIndex(transporte.CabEstaciones.ID));
                double Latitud = cursor.getDouble(cursor.getColumnIndex(transporte.CabEstaciones.Latitud));
                double Longitud = cursor.getDouble(cursor.getColumnIndex(transporte.CabEstaciones.Longitud));
                String Estacion = cursor.getString(cursor.getColumnIndex(transporte.CabEstaciones.Estaciones));
                long ID_estacion = cursor.getInt(cursor.getColumnIndex(transporte.CabEstaciones.ID_estacion));
                makersMapList.add(createMarker(Latitud,Longitud,Estacion,"",smallMarkerBusStop));
                DetailsStation result = new DetailsStation(ID,Latitud,Longitud,Estacion,ID_estacion);
                StationList.add(result);
                list.add(Estacion);
                cursor.moveToNext();
            }
        }

        spinerini.notifyDataSetChanged();
        spinerfin.notifyDataSetChanged();
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
                Log.i("SCAN RESULT",contents);


                int index = list.indexOf(contents);
                Log.i("INDEX!!!", String.valueOf(index));
                if(index != -1){
                    spinnerInitial.setSelection(index);
                }

                //list.add(0, contents);
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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        //createMarker(2.433, -76.617, "Centro", "", smallMarker);
        //createMarker(2.432, -76.613, "Campanario", "", smallMarker);
        //createMarker(2.434, -76.614, "terminal", "", smallMarker);

        LatLng popayan = new LatLng(2.433, -76.617);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(popayan));
        mMap.setMaxZoomPreference(18.0f);
        mMap.setMinZoomPreference(13.5f);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.setOnMyLocationButtonClickListener(this);
        mMap.setOnMyLocationClickListener(this);

        Log.d("Estaciones","Base de datos");
        prepareStations(BaseDatos.obtenerEstaciones());

    }

    protected Marker createMarker(double latitude, double longitude, String title, String snippet, Bitmap iconBitMap) {
        return mMap.addMarker(new MarkerOptions()
                .position(new LatLng(latitude, longitude))
                .anchor(0.5f, 0.5f)
                .title(title)
                .snippet(snippet)
                .icon(BitmapDescriptorFactory.fromBitmap(iconBitMap)));
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
        Toast.makeText(this, "Current location:\n" + location, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT).show();
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false;
    }



    @Override
    public void onResume() {
        super.onResume();
        updateStationcast();

    }


    Handler handlerSta = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            String responseText = (String) msg.obj;
            Log.i("HTTP MESSAGE",responseText);
            try{
                OperacionesURL ListasUrl = new OperacionesURL(responseText);
                StationURL = ListasUrl.buildStationsCasts();
            }
            catch (Exception e){
                e.printStackTrace();
            }

        }
    };

    Handler handlerRout = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            String responseText = (String) msg.obj;
            Log.i("HTTP MESSAGE",responseText);
            try{
                OperacionesURL ListasUrl = new OperacionesURL(responseText);
                RoutesURL = ListasUrl.buildRoutesCasts();
                Log.i("MESSAGE","RoutesUrL");
                updateDatabases();
            }
            catch (Exception e){
                Log.i("HTTP MESSAGE","execption");
                e.printStackTrace();
            }

        }
    };

    public void updateStationcast() {
        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    // conexion con el servidor
                    reqStation = new HttpRequest(URLStation);
                    reqRoutes = new HttpRequest(URLRoutes);
                    Message StationMsg = new Message();
                    Message RoutesMsg = new Message();
                    StationMsg.obj = reqStation.prepare(HttpRequest.Method.GET).sendAndReadString();
                    RoutesMsg.obj = reqRoutes.prepare(HttpRequest.Method.GET).sendAndReadString();
                    handlerSta.sendMessage(StationMsg);
                    handlerRout.sendMessage(RoutesMsg);

                } catch (Exception e) {
                    //Toast.makeText(WeatherDemo.this, "Request failed: " + e.getMessage(), Toast.LENGTH_LONG)
                    //      .show();
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }

    public void updateDatabases() {
        Log.i("UPDATED", "Updating1");
        for(DetailsStation Station:StationURL){
            Log.i("UPDATED", "Updating2");
            if(BaseDatos.updateEstacion(Station)){
                Log.i("UPDATED", Station.Estaciones);
            }
            else{
                Log.i("Insert", Station.Estaciones);
                BaseDatos.insertarEstacion(Station);
            }
        }
        for(DetailsRutas Routes:RoutesURL){
            Log.i("UPDATED", "Updating4");
            if(BaseDatos.updateRuta(Routes)){
                Log.i("UPDATED", Routes.Ruta);
            }
            else{
                Log.i("Insert", Routes.Ruta);
                BaseDatos.insertarRutas(Routes);
            }
        }

    }



}
