package com.example.david.easypopayan.sqlite;
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
    }

    interface Rutas{
        String ID = "id";
        String Ruta = "Ruta";
        String Empresa = "Empresa";
        String id_ruta = "cantidad";
        String PRECIO = "precio";
    }


    public static class CabecerasEstaciones implements Estaciones {
        public static String generarIdCabeceraPedido() {
            return "ST-" + UUID.randomUUID().toString();
        }
    }

    public static class AdicionalEstaciones implements Estaciones {
        // Métodos auxiliares
    }

    public static class CabecerasRutas implements Rutas {
        public static String generarIdCabeceraPedido() {
            return "RT-" + UUID.randomUUID().toString();
        }
    }

    public static class AdicionalRutas implements Rutas {
        // Métodos auxiliares
    }


    private transporte() {
    }



}
