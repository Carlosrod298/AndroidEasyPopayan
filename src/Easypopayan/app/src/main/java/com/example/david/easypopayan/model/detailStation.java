package com.example.david.easypopayan.model;

/**
 * Created by david on 13/03/18.
 */

public class detailStation {

    public String idCabeceraPedido;

    public int secuencia;

    public String idProducto;

    public int cantidad;

    public float precio;

    public detailStation(String idCabeceraPedido, int secuencia,
                         String idProducto, int cantidad, float precio) {
        this.idCabeceraPedido = idCabeceraPedido;
        this.secuencia = secuencia;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precio = precio;
    }
}
