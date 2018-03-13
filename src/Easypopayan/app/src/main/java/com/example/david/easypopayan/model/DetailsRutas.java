package com.example.david.easypopayan.model;

/**
 * Created by david on 13/03/18.
 */

public class DetailsRutas {

    public String idProducto;

    public String nombre;

    public float precio;

    public int existencias;

    public Producto(String idProducto, String nombre, float precio, int existencias) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.precio = precio;
        this.existencias = existencias;
    }
}
