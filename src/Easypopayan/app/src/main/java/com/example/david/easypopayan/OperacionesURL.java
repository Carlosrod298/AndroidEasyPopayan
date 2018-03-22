package com.example.david.easypopayan;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.david.easypopayan.model.DetailsRutas;
import com.example.david.easypopayan.model.DetailsStation;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by david on 21/03/18.
 */

public class OperacionesURL {


    public List<DetailsStation> StationList = new ArrayList<>();
    public List<DetailsRutas> RoutesList = new ArrayList<>();
    //declaración de atributos
    public String rawText;
    //public String rawFavoritos;

    public OperacionesURL(String dataText) {
        this.rawText = dataText;
        //this.rawFavoritos = dataFavo;

    }


    public List<DetailsStation> buildStationsCasts() throws Exception {

        StationList.clear();

        DocumentBuilder builder = DocumentBuilderFactory.newInstance()
                .newDocumentBuilder();
        Document doc = builder.parse(new InputSource(new StringReader(rawText)));
        doc.getDocumentElement().normalize();
        NodeList stationList = doc.getElementsByTagName("current_Station");

        String IDstr;
        String Latitudestr;
        String Longitudestr;
        String Namestr;
        String IDStationstr;

        for (int i = 0; i < stationList.getLength(); i++) {

            Node stateItem = (Element) stationList.item(i);
            if (stateItem.getNodeType() == Node.ELEMENT_NODE) {

                Element stationItemElement = (Element) stateItem;
                NodeList IDlist = stationItemElement.getElementsByTagName("Id");
                Element IDElement = (Element) IDlist.item(0);

                NodeList latitudeList = stationItemElement.getElementsByTagName("Latitud");
                Element latitudeElement = (Element) latitudeList.item(0);

                NodeList longitudeList = stationItemElement.getElementsByTagName("Longitud");
                Element longitudeElement = (Element) longitudeList.item(0);

                NodeList nameStationList = stationItemElement.getElementsByTagName("Name");
                Element nameElement = (Element) nameStationList.item(0);

                NodeList idStationList = stationItemElement.getElementsByTagName("ID_station");
                Element idStationElement = (Element) idStationList.item(0);

                IDstr =  IDElement.getAttribute("data");
                Latitudestr = latitudeElement.getAttribute("data");
                Longitudestr = longitudeElement.getAttribute("data");
                Namestr = nameElement.getAttribute("data");
                IDStationstr = idStationElement.getAttribute("data");

                DetailsStation Station = new DetailsStation(Long.parseLong(IDstr),
                        Double.parseDouble(Latitudestr),
                        Double.parseDouble(Longitudestr),
                        Namestr,
                        Long.parseLong(IDStationstr));

                StationList.add(Station);

            }
        }
        return StationList;
        //prepareStations(BaseDatos.obtenerEstaciones());
    }


    public List<DetailsRutas> buildRoutesCasts() throws Exception {

        RoutesList.clear();

        DocumentBuilder builder = DocumentBuilderFactory.newInstance()
                .newDocumentBuilder();
        Document doc = builder.parse(new InputSource(new StringReader(rawText)));
        doc.getDocumentElement().normalize();
        NodeList RoutList = doc.getElementsByTagName("current_Routes");

        String IDstr;
        String Routetr;
        String Companytr;
        String timelapsedtr;
        String costostr;
        String Einistr;
        String Efinstr;
        String Allstationstr;
        for (int i = 0; i < RoutList.getLength(); i++) {

            Node stateItem = (Element) RoutList.item(i);
            if (stateItem.getNodeType() == Node.ELEMENT_NODE) {

                Element stationItemElement = (Element) stateItem;
                NodeList IDlist = stationItemElement.getElementsByTagName("Id");
                Element IDElement = (Element) IDlist.item(0);

                NodeList rutaList = stationItemElement.getElementsByTagName("Route");
                Element rutaElement = (Element) rutaList.item(0);

                NodeList companyList = stationItemElement.getElementsByTagName("Company");
                Element companyElement = (Element) companyList.item(0);

                NodeList timeList = stationItemElement.getElementsByTagName("timelapsed");
                Element timeElement = (Element) timeList.item(0);

                NodeList costoList = stationItemElement.getElementsByTagName("costo");
                Element costoElement = (Element) costoList.item(0);

                NodeList EiniList = stationItemElement.getElementsByTagName("Eini");
                Element EiniElement = (Element) EiniList.item(0);

                NodeList EfinList = stationItemElement.getElementsByTagName("Efin");
                Element EfinElement = (Element) EfinList.item(0);

                NodeList AllList = stationItemElement.getElementsByTagName("tracking");
                Element AllElement = (Element) AllList.item(0);


                IDstr =  IDElement.getAttribute("data");
                Routetr = rutaElement.getAttribute("data");
                Companytr = companyElement.getAttribute("data");
                timelapsedtr = timeElement.getAttribute("data");
                costostr = costoElement.getAttribute("data");
                Einistr = EiniElement.getAttribute("data");
                Efinstr = EfinElement.getAttribute("data");
                Allstationstr = AllElement.getAttribute("data");
                String[] parts= Allstationstr.split(",");
                DetailsRutas Rutas = new DetailsRutas(Long.parseLong(IDstr),
                        Routetr,Companytr,Long.parseLong(timelapsedtr),
                        Float.parseFloat(costostr),
                        Long.parseLong(Einistr),
                        Long.parseLong(Efinstr),
                        Long.parseLong(parts[0]),
                        Long.parseLong(parts[1]),
                        Long.parseLong(parts[2]),
                        Long.parseLong(parts[3]));
                RoutesList.add(Rutas);

            }
        }

        return RoutesList;
        //prepareStations(BaseDatos.obtenerEstaciones());
    }



}
