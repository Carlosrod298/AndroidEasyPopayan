package com.example.david.easypopayan.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.provider.BaseColumns;
import android.util.Log;

import com.example.david.easypopayan.model.DetailsStation;
import  com.example.david.easypopayan.sqlite.transporte.Rutas;
import  com.example.david.easypopayan.sqlite.transporte.Estaciones;

public class BaseST extends SQLiteOpenHelper {

    private static final String NOMBRE_BASE_DATOS = "EasyPopayan1.db";

    private static final int VERSION_ACTUAL = 1;

    private final Context contexto;

    public interface Tablas {
        String ESTACIONES = "tablaestaciones";
        String RUTAS = "tablarutas";
        String FAVORITOS = "tablafavoritos";
    }


    interface Referencias {

        String ID_ESTACIONES = String.format("REFERENCES %s(%s) ON DELETE CASCADE",
                Tablas.ESTACIONES, transporte.CabEstaciones.ID);

        String ID_RUTAS = String.format("REFERENCES %s(%s) ON DELETE CASCADE",
                Tablas.RUTAS, transporte.CabRutas.ID);
    }

    public BaseST(Context contexto) {
        super(contexto, NOMBRE_BASE_DATOS, null, VERSION_ACTUAL);
        this.contexto = contexto;
    }

    //public void close(SQLiteDatabase db) {
    //    db.close();
    //}

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                db.setForeignKeyConstraintsEnabled(true);
            } else {
                db.execSQL("PRAGMA foreign_keys=ON ");
            }
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.d("Base de Datos","Creacion de Tablas");
        db.execSQL(String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s REAL NOT NULL," +
                        "%s REAL NOT NULL," +
                        "%s TEXT NOT NULL," +
                        "%s INT  NOT NULL )",
                Tablas.ESTACIONES,
                transporte.CabEstaciones.ID,
                transporte.CabEstaciones.Latitud,
                transporte.CabEstaciones.Longitud,
                transporte.CabEstaciones.Estaciones,
                transporte.CabEstaciones.ID_estacion
                ));

        db.execSQL(String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT NOT NULL," +
                        "%s TEXT NOT NULL," +
                        "%s INT NOT NULL," +
                        "%s REAL NOT NULL," +
                        "%s INT NOT NULL %s," +
                        "%s INT NOT NULL %s," +
                        "%s INT NOT NULL %s," +
                        "%s INT NOT NULL %s," +
                        "%s INT NOT NULL %s," +
                        "%s INT NOT NULL %s)",
                Tablas.RUTAS,
                transporte.CabRutas.ID,
                transporte.CabRutas.Ruta,
                transporte.CabRutas.Empresa,
                transporte.CabRutas.id_ruta,
                transporte.CabRutas.PRECIO,
                transporte.CabRutas.EsInicial,Referencias.ID_ESTACIONES,
                transporte.CabRutas.EsFinal,Referencias.ID_ESTACIONES,
                transporte.CabRutas.Es1,Referencias.ID_ESTACIONES,
                transporte.CabRutas.Es2,Referencias.ID_ESTACIONES,
                transporte.CabRutas.Es3,Referencias.ID_ESTACIONES,
                transporte.CabRutas.Es4,Referencias.ID_ESTACIONES
        ));

        db.execSQL(String.format("CREATE TABLE %s (%s INT UNIQUE NOT NULL %s," +
                        "%s TEXT NOT NULL)",
                Tablas.FAVORITOS,
                transporte.CabFavoritos.ID,Referencias.ID_RUTAS,
                transporte.CabFavoritos.Rutas

        ));

        Log.d("Base de Datos","Fin de Tablas");
    }







    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + Tablas.ESTACIONES);
        db.execSQL("DROP TABLE IF EXISTS " + Tablas.RUTAS);
        db.execSQL("DROP TABLE IF EXISTS " + Tablas.FAVORITOS);

        onCreate(db);
    }



}
