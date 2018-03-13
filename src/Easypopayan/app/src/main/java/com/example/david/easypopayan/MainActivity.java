package com.example.david.easypopayan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button viewRoutesBnt;
    private ListView myListView;

    //Variable para informacion
    static final private int REMOVE_TODO = Menu.FIRST;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewRoutesBnt = (Button)findViewById(R.id.viewRoutes);
        viewRoutesBnt.setOnClickListener(this);

        //Creacion del menu contextual
        //registerForContextMenu(myListView);

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
            Intent i = new Intent(this, AvailableRouts.class);
            i.setClassName("com.example.david.easypopayan","com.example.david.easypopayan.AvailableRouts");
            startActivity(i);
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
}
