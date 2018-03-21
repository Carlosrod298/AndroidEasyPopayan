package com.example.david.easypopayan;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.david.easypopayan.model.DetailsFavoritos;
import com.example.david.easypopayan.model.DetailsRutas;
import com.example.david.easypopayan.model.DetailsStation;
import com.example.david.easypopayan.sqlite.BaseST;
import com.example.david.easypopayan.sqlite.transporte;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by david on 18/03/18.
 */

public class OperacionesBaseD {


    private static BaseST baseDatos;

    private static OperacionesBaseD instancia = new OperacionesBaseD();

    private OperacionesBaseD() {
    }

    public static OperacionesBaseD obtenerInstancia(Context contexto) {
        if (baseDatos == null) {
            baseDatos = new BaseST(contexto);
        }
        return instancia;
    }

    //insertar valores
    public long insertarEstacion(DetailsStation Station) {
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(transporte.CabEstaciones.ID, Station.ID);
        valores.put(transporte.CabEstaciones.Latitud, Station.Latitud);
        valores.put(transporte.CabEstaciones.Longitud, Station.Longitud);
        valores.put(transporte.CabEstaciones.Estaciones, Station.Estaciones);
        valores.put(transporte.CabEstaciones.ID_estacion, Station.ID_estacion);
        return db.insert(BaseST.Tablas.ESTACIONES, null, valores);
    }

    public long insertarRutas(DetailsRutas Ruta) {
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(transporte.CabRutas.ID, Ruta.idRuta);
        valores.put(transporte.CabRutas.Ruta, Ruta.Ruta);
        valores.put(transporte.CabRutas.Empresa, Ruta.empresa);
        valores.put(transporte.CabRutas.id_ruta, Ruta.idRuta1);
        valores.put(transporte.CabRutas.PRECIO, Ruta.precio);
        valores.put(transporte.CabRutas.EsInicial, Ruta.estinicial);
        valores.put(transporte.CabRutas.EsFinal, Ruta.estfinal);
        valores.put(transporte.CabRutas.Es1, Ruta.est1);
        valores.put(transporte.CabRutas.Es2, Ruta.est2);
        valores.put(transporte.CabRutas.Es3, Ruta.est3);
        valores.put(transporte.CabRutas.Es4, Ruta.est4);

        return db.insert(BaseST.Tablas.RUTAS, null, valores);

    }

    public long insertarFavoritos(DetailsFavoritos favor) {
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(transporte.CabFavoritos.ID, favor.idRuta);
        valores.put(transporte.CabFavoritos.Rutas, favor.Ruta);
        return db.insert(BaseST.Tablas.FAVORITOS, null, valores);
       // return String.format("%s#%s", Ruta.idRuta, Ruta.Ruta);

    }

    //Actualizar por ID

    public boolean updateEstacion(DetailsStation Station) {
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        String selection = String.format("%s=? AND %s=?",
                transporte.CabEstaciones.ID, Station.ID);

        ContentValues valores = new ContentValues();
        valores.put(transporte.CabEstaciones.ID, Station.ID_estacion);
        valores.put(transporte.CabEstaciones.Estaciones, Station.Estaciones);
        valores.put(transporte.CabEstaciones.Latitud, Station.Latitud);
        valores.put(transporte.CabEstaciones.Longitud, Station.Longitud);
        return db.update(BaseST.Tablas.ESTACIONES, valores, selection,
                null) > 0;
    }

    public boolean updateRuta(DetailsRutas Ruta) {
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        String selection = String.format("%s=? AND %s=?",
                transporte.CabRutas.ID, Ruta.idRuta);

        ContentValues valores = new ContentValues();
        valores.put(transporte.CabRutas.ID, Ruta.idRuta);
        valores.put(transporte.CabRutas.Ruta, Ruta.Ruta);
        valores.put(transporte.CabRutas.Empresa, Ruta.empresa);
        valores.put(transporte.CabRutas.id_ruta, Ruta.idRuta1);
        valores.put(transporte.CabRutas.PRECIO, Ruta.precio);
        valores.put(transporte.CabRutas.EsInicial, Ruta.estinicial);
        valores.put(transporte.CabRutas.EsFinal, Ruta.estfinal);
        return db.update(BaseST.Tablas.RUTAS, valores, selection,
                null) > 0;
    }

    public boolean updateFavorito(DetailsFavoritos favor) {
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        String selection = String.format("%s=? AND %s=?",
                transporte.CabEstaciones.ID, favor.idRuta);

        ContentValues valores = new ContentValues();
        valores.put(transporte.CabFavoritos.ID, favor.idRuta);
        valores.put(transporte.CabFavoritos.Rutas, favor.Ruta);
        return db.update(BaseST.Tablas.ESTACIONES, valores, selection,
                null) > 0;
    }


    //Leer

    public Cursor obtenerEstaciones() {
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        String[] Estaciones = {
                //BaseST.Tablas.ESTACIONES + ".",
                transporte.CabEstaciones.ID,
                transporte.CabEstaciones.Latitud,
                transporte.CabEstaciones.Longitud,
                transporte.CabEstaciones.Estaciones,
                transporte.CabEstaciones.ID_estacion};

        return db.query(BaseST.Tablas.ESTACIONES, Estaciones, null, null,
                null, null, null);

    }

    public Cursor obtenerRutas() {
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        String[] Rutas = {
                //BaseST.Tablas.RUTAS + ".",
                transporte.CabRutas.ID,
                transporte.CabRutas.Ruta,
                transporte.CabRutas.Empresa,
                transporte.CabRutas.id_ruta,
                transporte.CabRutas.PRECIO,
                transporte.CabRutas.EsInicial,
                transporte.CabRutas.EsFinal,
                transporte.CabRutas.Es1,
                transporte.CabRutas.Es2,
                transporte.CabRutas.Es3,
                transporte.CabRutas.Es4};

        return db.query(BaseST.Tablas.RUTAS, Rutas, null, null,
                null, null, null);

    }

    public Cursor obtenerFavoritos() {
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        String[] Favoritos = {
                //BaseST.Tablas.FAVORITOS + ".",
                transporte.CabFavoritos.ID,
                transporte.CabFavoritos.Rutas};

        return db.query(BaseST.Tablas.FAVORITOS, Favoritos, null, null,
                null, null, null);

    }

    public DetailsStation obtenerStationPorId(String id) {
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        String selection = String.format("%s=?", transporte.CabEstaciones.ID);
        String[] selectionArgs = {id};

        String[] Estaciones = {
                transporte.CabEstaciones.ID,
                transporte.CabEstaciones.Latitud,
                transporte.CabEstaciones.Longitud,
                transporte.CabEstaciones.Estaciones,
                transporte.CabEstaciones.ID_estacion};
        Cursor cursor = db.query(BaseST.Tablas.ESTACIONES, Estaciones, selection, selectionArgs,
                null, null, null);
        if ((cursor.getCount() == 0) || !cursor.moveToFirst()) {
            throw new SQLException("No to do item found for row: " + id);
        }

        long ID = cursor.getInt(cursor.getColumnIndex(transporte.CabEstaciones.ID));
        double Latitud = cursor.getDouble(cursor.getColumnIndex(transporte.CabEstaciones.Latitud));
        double Longitud = cursor.getDouble(cursor.getColumnIndex(transporte.CabEstaciones.Longitud));
        String REstaciones = cursor.getString(cursor.getColumnIndex(transporte.CabEstaciones.Estaciones));
        long ID_estacion = cursor.getInt(cursor.getColumnIndex(transporte.CabEstaciones.ID_estacion));

        DetailsStation result = new DetailsStation(ID,Latitud,Longitud,REstaciones,ID_estacion);
        return result;
    }


    public DetailsRutas obtenerRutasPorId(String id) {
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        String selection = String.format("%s=?", transporte.CabRutas.ID);
        String[] selectionArgs = {id};

        String[] Rutas = {
                //BaseST.Tablas.RUTAS + ".",
                transporte.CabRutas.ID,
                transporte.CabRutas.Ruta,
                transporte.CabRutas.Empresa,
                transporte.CabRutas.id_ruta,
                transporte.CabRutas.PRECIO,
                transporte.CabRutas.EsInicial,
                transporte.CabRutas.EsFinal,
                transporte.CabRutas.Es1,
                transporte.CabRutas.Es2,
                transporte.CabRutas.Es3,
                transporte.CabRutas.Es4};

        Cursor cursor = db.query(BaseST.Tablas.RUTAS, Rutas, selection, selectionArgs,
                null, null, null);
        if ((cursor.getCount() == 0) || !cursor.moveToFirst()) {
            throw new SQLException("No to do item found for row: " + id);
        }

        long ID = cursor.getInt(cursor.getColumnIndex(transporte.CabRutas.ID));
        String Ruta = cursor.getString(cursor.getColumnIndex(transporte.CabRutas.Ruta));
        String Empresa = cursor.getString(cursor.getColumnIndex(transporte.CabRutas.Empresa));
        long id_Ruta = cursor.getInt(cursor.getColumnIndex(transporte.CabRutas.id_ruta));
        float Precio = cursor.getFloat(cursor.getColumnIndex(transporte.CabRutas.PRECIO));
        long EstInicial = cursor.getInt(cursor.getColumnIndex(transporte.CabRutas.EsInicial));
        long EstFinal = cursor.getInt(cursor.getColumnIndex(transporte.CabRutas.EsFinal));
        long Est1 = cursor.getInt(cursor.getColumnIndex(transporte.CabRutas.Es1));
        long Est2 = cursor.getInt(cursor.getColumnIndex(transporte.CabRutas.Es2));
        long Est3 = cursor.getInt(cursor.getColumnIndex(transporte.CabRutas.Es3));
        long Est4 = cursor.getInt(cursor.getColumnIndex(transporte.CabRutas.Es4));

        DetailsRutas result = new DetailsRutas(ID,Ruta,Empresa,id_Ruta,Precio,
                EstInicial,EstFinal,Est1,Est2,Est3,Est4);
        return result;
    }

    public Cursor obtenerRutasPorStations(int e1,int e2) {
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        String Sta1 = String.valueOf(e1);
        String Sta2 = String.valueOf(e2);
        String[] selectionArgs = {Sta1,Sta1,Sta1,Sta1,Sta1,Sta1,Sta2,Sta2,Sta2,Sta2,Sta2,Sta2};

        String query=String.format("SELECT %s FROM %s WHERE (%s=? OR %s=? OR %s=? OR %s=? " +
                        "OR %s=? OR %s=?)AND(%s=? OR %s=? OR %s=? OR %s=? OR %s=? OR %s=?)",
                transporte.CabRutas.ID,BaseST.Tablas.RUTAS,
                transporte.CabRutas.EsInicial, transporte.CabRutas.EsFinal,
                transporte.CabRutas.Es1, transporte.CabRutas.Es2,
                transporte.CabRutas.Es3, transporte.CabRutas.Es4,
                transporte.CabRutas.EsInicial, transporte.CabRutas.EsFinal,
                transporte.CabRutas.Es1, transporte.CabRutas.Es2,
                transporte.CabRutas.Es3, transporte.CabRutas.Es4);

        Cursor cursor = db.rawQuery(query, selectionArgs);
        return cursor;
        }


    public DetailsFavoritos obtenerFavoritosPorId(String id) {
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        String selection = String.format("%s=?", transporte.CabFavoritos.ID);
        String[] selectionArgs = {id};

        String[] Favoritos = {
                //BaseST.Tablas.FAVORITOS + ".",
                transporte.CabRutas.ID,
                transporte.CabRutas.Ruta};

        Cursor cursor = db.query(BaseST.Tablas.FAVORITOS, Favoritos, selection, selectionArgs,
                null, null, null);
        if ((cursor.getCount() == 0) || !cursor.moveToFirst()) {
            throw new SQLException("No to do item found for row: " + id);
        }
        long ID = cursor.getInt(cursor.getColumnIndex(transporte.CabFavoritos.ID));
        String Ruta = cursor.getString(cursor.getColumnIndex(transporte.CabFavoritos.Rutas));

        DetailsFavoritos result = new DetailsFavoritos(ID,Ruta);
        return result;
    }



    //Eliminar
    public boolean EliminarEstacion(String ID_Station) {
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        String selection = String.format("%s=? AND %s=?",
                transporte.CabEstaciones.ID, ID_Station);

        return db.delete(BaseST.Tablas.ESTACIONES, selection,
                null) > 0;
    }

    public boolean EliminarRuta(String ID_Ruta) {
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        String selection = String.format("%s=? AND %s=?",
                transporte.CabRutas.ID, ID_Ruta);
        return db.delete(BaseST.Tablas.RUTAS, selection,
                null) > 0;
    }

    public boolean EliminarFavorito(String ID_favor) {
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        String selection = String.format("%s=? AND %s=?",
                transporte.CabEstaciones.ID, ID_favor);
        return db.delete(BaseST.Tablas.ESTACIONES, selection,
                null) > 0;
    }

    public SQLiteDatabase getDb() {
        return baseDatos.getWritableDatabase();
    }

    public void Closedatabase() {
        baseDatos.close();
    }


}