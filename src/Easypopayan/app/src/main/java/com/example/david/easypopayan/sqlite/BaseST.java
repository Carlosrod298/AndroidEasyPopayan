package com.example.david.easypopayan.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.provider.BaseColumns;

import  com.example.david.easypopayan.sqlite.transporte.Rutas;
import  com.example.david.easypopayan.sqlite.transporte.Estaciones;

public class BaseST extends SQLiteOpenHelper {

    private static final String NOMBRE_BASE_DATOS = "EasyPopayan.db";

    private static final int VERSION_ACTUAL = 1;

    private final Context contexto;

    interface Tablas {
        String CABECERA_ESTACIONES = "cabecera_estaciones";
        String CABECERA_RUTAS = "cabecera_rutas";
    }


    interface Referencias {

        String ID_CABECERA_ESTACIONES = String.format("REFERENCES %s(%s) ON DELETE CASCADE",
                Tablas.CABECERA_ESTACIONES, transporte.CabecerasEstaciones.ID);

        String ID_CABECERA_RUTAS = String.format("REFERENCES %s(%s)",
                Tablas.CABECERA_RUTAS, Rutas.ID);
    }

    public BaseST(Context contexto) {
        super(contexto, NOMBRE_BASE_DATOS, null, VERSION_ACTUAL);
        this.contexto = contexto;
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                db.setForeignKeyConstraintsEnabled(true);
            } else {
                db.execSQL("PRAGMA foreign_keys=ON");
            }
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s REAL NOT NULL," +
                        "%s REAL NOT NULL," +
                        "%s TEXT NOT NULL %s," +
                        "%s INT UNIQUE NOT NULL %s)",
                Tablas.CABECERA_ESTACIONES, BaseColumns._ID,
                Estaciones.Latitud,
                Estaciones.Longitud,
                Estaciones.Estaciones,
                Estaciones.ID_estacion
                ));

        db.execSQL(String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT NOT NULL," +
                        "%s TEXT NOT NULL," +
                        "%s INT UNIQUE NOT NULL ," +
                        "%s REAL NOT NULL )",
                Tablas.CABECERA_RUTAS, BaseColumns._ID,
                Rutas.Ruta,
                Rutas.Empresa,
                Rutas.id_ruta,
                Rutas.PRECIO
        ));

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + Tablas.CABECERA_ESTACIONES);
        db.execSQL("DROP TABLE IF EXISTS " + Tablas.CABECERA_RUTAS);

        onCreate(db);
    }



}
