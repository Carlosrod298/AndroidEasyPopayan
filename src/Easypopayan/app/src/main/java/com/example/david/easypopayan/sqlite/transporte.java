package com.example.david.easypopayan.sqlite;
import com.google.android.gms.maps.model.Marker;

import java.util.UUID;
/**
 * Created by david on 13/03/18.
 */

public class transporte {

    interface Estaciones {
        String ID = "id";
        String Latitud = "latitud";
        String Longitud = "longitud";
        String Estaciones = "Estaciones";
        String ID_estacion = "id_estacion";
        Marker maker = null;
    }

    interface Rutas{
        String ID = "id";
        String Ruta = "Ruta";
        String Empresa = "Empresa";
        String id_ruta = "cantidad";
        String PRECIO = "precio";
        String EsInicial = "EstInicial";
        String EsFinal = "EstFinal";
        String Es1 = "Est1";
        String Es2 = "Est2";
        String Es3 = "Est3";
        String Es4 = "Est4";

    }

    interface Favoritos{
        String ID = "id";
        String Rutas = "Ruta";
     }


    public static class CabEstaciones implements Estaciones {
        public static String generarEstaciones() {
            return "ST-" + UUID.randomUUID().toString();
        }
    }


    public static class CabRutas implements Rutas {
        public static String generarRutas() {
            return "RT-" + UUID.randomUUID().toString();
        }
    }
    public static class CabFavoritos implements Favoritos {
        public static String generarFavoritos() {
            return "FV-" + UUID.randomUUID().toString();
        }
    }
    public static class AdicionalRutas implements Rutas {
        // Métodos auxiliares
    }


    private transporte() {
    }



}
