package com.example.alexy.tubtabbar.Entities;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by iem on 18/10/2016.
 */

public class Stop {

    private int id;
    private String name;
    private String description;
    private LatLng gpsCoord;

    public Stop(){

    }

    public Stop(String name, String description, LatLng gpsCoord){
        this.name = name;
        this.description = description;
        this.gpsCoord = gpsCoord;
    }


    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public LatLng getGpsCoord(){
        return gpsCoord;
    }

    public void setGpsCoord(double latitude, double longitude){
        this.gpsCoord = new LatLng(latitude, longitude);
    }
}
