package com.example.alexy.tubtabbar;

import android.graphics.BitmapFactory;

import com.example.alexy.tubtabbar.Entities.Line;
import com.example.alexy.tubtabbar.Entities.Stop;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexy on 02/11/2016.
 */

public class Utilities {

    public static MarkerOptions addStopToMarker(Stop stop){
        return new MarkerOptions().title(stop.getName()).position(stop.getGpsCoord()).icon(BitmapDescriptorFactory.fromResource(R.drawable.busmarker));
    }

    public static List<MarkerOptions> addStopToMarker(List<Stop> stops){
        List<MarkerOptions> markers = new ArrayList<>();
        for (Stop stop:stops){
            markers.add(new MarkerOptions().title(stop.getName()).position(stop.getGpsCoord()).icon(BitmapDescriptorFactory.fromResource(R.drawable.busmarker)));
        }
        return markers;
    }

}
