package com.example.david.easypopayan.model;

/**
 * Created by david on 13/03/18.
 */

public class cabeceraStation {
    public String idCabeceraEstacion;

    public String Latitud;

    public String Longitud;

    public String Estaciones;

    public String ID_estacion;

    public cabeceraStation(String idCabeceraEstacion, String Latitud,
                          String Longitud, String Estaciones, String ID_estacion) {
        this.idCabeceraEstacion = idCabeceraEstacion;
        this.Latitud = Latitud;
        this.Longitud = Longitud;
        this.Estaciones = Estaciones;
        this.ID_estacion = ID_estacion;
    }
}
