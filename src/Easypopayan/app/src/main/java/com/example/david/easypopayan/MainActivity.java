package com.example.david.easypopayan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button viewRoutesBnt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewRoutesBnt = (Button)findViewById(R.id.viewRoutes);
        viewRoutesBnt.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view == viewRoutesBnt){
            Intent i = new Intent(this, AvailableRouts.class);
            i.setClassName("com.example.david.easypopayan","com.example.david.easypopayan.AvailableRouts");
            startActivity(i);
        }
    }
}
