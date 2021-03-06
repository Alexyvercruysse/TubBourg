package com.example.alexy.tubtabbar.Utils;

import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.alexy.tubtabbar.Entities.Hour;
import com.example.alexy.tubtabbar.Entities.Line;
import com.example.alexy.tubtabbar.Entities.Stop;
import com.example.alexy.tubtabbar.R;
import com.example.alexy.tubtabbar.Repositories.LineRepository;
import com.example.alexy.tubtabbar.Repositories.LineRepositoryImpl;
import com.example.alexy.tubtabbar.Repositories.StopRepository;
import com.example.alexy.tubtabbar.Repositories.StopRepositoryImpl;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by Alexy on 02/11/2016.
 */

public class Utilities {

        // Ajout d'un stop en marker sur la map
    public static MarkerOptions addStopToMarker(Stop stop){
        return new MarkerOptions().title(stop.getName()).position(new LatLng(stop.getLatitude(), stop.getLongitude())).icon(BitmapDescriptorFactory.fromResource(R.drawable.busmarker));
    }

        // Ajout des stops en marker sur la map
    public static List<MarkerOptions> addStopToMarker(List<Stop> stops){
        List<MarkerOptions> markers = new ArrayList<>();
        for (Stop stop : stops){
            markers.add(new MarkerOptions().title(stop.getName()).position(new LatLng(stop.getLatitude(), stop.getLongitude())).icon(BitmapDescriptorFactory.fromResource(R.drawable.busmarker)));
        }
        return markers;
    }


    //Find the next hour of stop
    public static String getNextPassageFromDate(List<Hour> listhour, Date dateParam){

        String result = "Pas d'horraire";

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");

        Date now = dateParam;
        if(now == null){
            now = new Date();
            try {
                now = simpleDateFormat.parse(simpleDateFormat.format(now));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        Date minDate = null;

        for(Hour hour : listhour){
            Date date = null;
            try {
                date = simpleDateFormat.parse(hour.getHour());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if(date != null && date.compareTo(now) >= 0) {
                if(minDate != null){
                    int diffDate = date.compareTo(minDate);
                    if(diffDate < 0) {
                        minDate = date;
                    }
                }else{
                    minDate = date;
                }
            }
        }

        if(minDate != null){
            result = simpleDateFormat.format(minDate);
        }
        return result;
    }
}
