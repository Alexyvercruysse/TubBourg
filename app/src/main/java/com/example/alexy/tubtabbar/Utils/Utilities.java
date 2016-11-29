package com.example.alexy.tubtabbar.Utils;

import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.alexy.tubtabbar.Entities.Line;
import com.example.alexy.tubtabbar.Entities.Stop;
import com.example.alexy.tubtabbar.R;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Alexy on 02/11/2016.
 */

public class Utilities {



        // Ajout d'un stop en marker sur la map
    public static MarkerOptions addStopToMarker(Stop stop){
        return new MarkerOptions().title(stop.getName()).position(stop.getGpsCoord()).icon(BitmapDescriptorFactory.fromResource(R.drawable.busmarker));
    }

        // Ajout des stops en marker sur la map
    public static List<MarkerOptions> addStopToMarker(List<Stop> stops){
        List<MarkerOptions> markers = new ArrayList<>();
        for (Stop stop:stops){
            markers.add(new MarkerOptions().title(stop.getName()).position(stop.getGpsCoord()).icon(BitmapDescriptorFactory.fromResource(R.drawable.busmarker)));
        }
        return markers;
    }


        // Récupération de l'horraire le plus proche
    // FIXME : HARDCODING
    public static String getNextPassageFromNow(List<String> lesHeures){
        List<Integer> lesHEnInt = new ArrayList<Integer>();

        // FIXME Use non-depreciated func
        int hour = new Date().getHours();
        int minute = new Date().getMinutes();

        int ahour = hour*1000+minute;
        int hourToReturn = 0;
        String result = "Pas d'horraire";
        for (String lheure : lesHeures){
            lesHEnInt.add(Integer.parseInt(lheure.substring(0,2))*1000+Integer.parseInt(lheure.substring(3,5)));
        }
        for (int chooseHour : lesHEnInt){
            Log.d("Tag","ChooseHour "+chooseHour+" leheure "+ahour);
            if (ahour < chooseHour){
                    hourToReturn = chooseHour;
                if (hourToReturn == 0){
                    hourToReturn = lesHEnInt.get(0);
                }
                if (hourToReturn < 10000){
                    result = hourToReturn/1000+":"+hourToReturn%1000;
                }
                else {
                    result = hourToReturn/1000+":"+hourToReturn%1000;
                }
                return result;
            }

        }
        return result;
    }



}
