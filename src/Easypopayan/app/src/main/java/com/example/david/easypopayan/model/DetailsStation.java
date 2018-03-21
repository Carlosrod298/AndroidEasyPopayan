package com.example.david.easypopayan.model;

import com.google.android.gms.maps.model.Marker;

/**
 * Created by david on 13/03/18.
 */

public class DetailsStation {
    public long ID;

    public double Latitud;

    public double Longitud;

    public String Estaciones;

    public long ID_estacion;

    public DetailsStation(long idCabeceraEstacion, double Latitud,
                          double Longitud, String Estaciones, long ID_estacion)
    {
        this.ID = idCabeceraEstacion;
        this.Latitud = Latitud;
        this.Longitud = Longitud;
        this.Estaciones = Estaciones;
        this.ID_estacion = ID_estacion;
    }
}
