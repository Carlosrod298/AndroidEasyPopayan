package com.example.david.easypopayan;

/**
 * Created by david on 12/03/18.
 */

import android.content.Context;
import android.renderscript.Sampler;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.david.easypopayan.model.DetailsRutas;
import com.example.david.easypopayan.model.DetailsStation;

import java.util.List;

class RecyclerViewHolder extends RecyclerView.ViewHolder
        implements View.OnCreateContextMenuListener {

    static final private int REMOVE_TODO = Menu.FIRST;
    static final private int SEND_TODO = Menu.FIRST+1;

    public TextView ruta;
    public TextView empresa;
    public TextView precio;
    public TextView efinal;
    public TextView eincial;
    public TextView timeestimated;
    private ItemClickListener itemClickListener;

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        ruta = (TextView)itemView.findViewById(R.id.name);
        empresa = (TextView)itemView.findViewById(R.id.empresa);
        precio = (TextView)itemView.findViewById(R.id.precio);
        eincial = (TextView)itemView.findViewById(R.id.E_ini);
        efinal = (TextView)itemView.findViewById(R.id.E_fin);
        timeestimated =(TextView)itemView.findViewById(R.id.timeestimated);
        //itemView.setOnClickListener(this);
        //itemView.setOnLongClickListener(this);

        itemView.setOnCreateContextMenuListener(this);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        //menuInfo is null
        menu.setHeaderTitle(R.string.menu_title);
        menu.add(0, v.getId(), 0, R.string.see_details);
        menu.add(0, v.getId(), 0, R.string.add_favorite);
    }

}

public class RoutesAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    private List<DetailsRutas> routeList;
    private Context context;

    public RoutesAdapter(List<DetailsRutas> routesList, Context context) {

        this.routeList = routesList;
        this.context = context;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /*LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.route_list_row, parent, false);*/
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.route_list_row,
                parent,false);
        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {

        DetailsRutas RouteData  = routeList.get(position);
        holder.ruta.setText("Ruta: "+RouteData.Ruta);
        holder.empresa.setText("Empresa: "+ RouteData.empresa);
        holder.precio.setText("Costo: "+String.valueOf(RouteData.precio));
        holder.eincial.setText("Estacion Inicial: "+RouteData.getStatioIni());
        holder.efinal.setText("Estacion Final: "+RouteData.getStatioFin());
        holder.timeestimated.setText(String.valueOf(RouteData.idRuta1)+" Minutos");

    }

    @Override
    public int getItemCount() {
        return routeList.size();
    }

}
