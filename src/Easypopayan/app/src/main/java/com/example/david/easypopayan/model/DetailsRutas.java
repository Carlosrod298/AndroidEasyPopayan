package com.example.david.easypopayan.model;

import android.graphics.Region;

import com.example.david.easypopayan.OperacionesBaseD;

/**
 * Created by david on 13/03/18.
 */

public class DetailsRutas {

    public long idRuta;
    public String Ruta;
    public String empresa;
    public long idRuta1;
    public float precio;
    public long estinicial;
    public long estfinal;
    public long est1;
    public long est2;
    public long est3;
    public long est4;

    public String estacionInicial;
    public String estacionfinal;
    public DetailsRutas(){}

    public DetailsRutas(long idRuta, String Rutan,String empresa, long idRuta1, float precio,
                    long estinicial, long estfinal,long est1, long est2,long est3, long est4) {
        this.idRuta = idRuta;
        this.Ruta = Rutan;
        this.empresa = empresa;
        this.idRuta1 = idRuta1;
        this.precio = precio;
        this.estinicial = estinicial;
        this.estfinal = estfinal;
        this.est1 = est1;
        this.est2 = est2;
        this.est3 = est3;
        this.est4 = est4;
    }

    public float getidRuta() {
        return idRuta;
    }

    public void setIdRuta(long data) {
        this.idRuta = data;
    }

    public String getRuta() {
        return Ruta;
    }
    public void setRuta(String data) {
        this.Ruta = data;
    }

    public String getStatioIni() {
         return this.estacionInicial;
    }
    public String getStatioFin() {
        return this.estacionfinal;
    }
    public void setStatioIni(String data) {
        this.estacionInicial = data;
    }
    public void setStatioFin(String data) {
        this.estacionfinal = data;
    }



}
