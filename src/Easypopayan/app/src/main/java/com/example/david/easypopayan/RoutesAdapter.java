package com.example.david.easypopayan;

/**
 * Created by david on 12/03/18.
 */

import android.content.Context;
import android.renderscript.Sampler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.david.easypopayan.model.DetailsRutas;
import com.example.david.easypopayan.model.DetailsStation;

import java.util.List;

class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener
{

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
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener)
    {
        this.itemClickListener= itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),false);

    }

    @Override
    public boolean onLongClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),true);
        return true;
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
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {


        DetailsRutas RouteData  = routeList.get(position);
        holder.ruta.setText("Ruta: "+RouteData.Ruta);
        holder.empresa.setText("Empresa: "+ RouteData.empresa);
        holder.precio.setText("Costo: "+String.valueOf(RouteData.precio));
        holder.eincial.setText("Estacion Inicial: "+RouteData.getStatioIni());
        holder.efinal.setText("Estacion Final: "+RouteData.getStatioFin());
        holder.timeestimated.setText(String.valueOf(RouteData.idRuta1)+" Minutos");
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if(isLongClick)
                    Toast.makeText(context,"Long click "+position,Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(context,"click "+position,Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return routeList.size();
    }



}
