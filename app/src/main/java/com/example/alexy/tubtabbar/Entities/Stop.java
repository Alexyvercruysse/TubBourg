package com.example.alexy.tubtabbar.Entities;

import com.example.alexy.tubtabbar.Repositories.TubDatabase;
import com.google.android.gms.maps.model.LatLng;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.property.DoubleProperty;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by iem on 18/10/2016.
 */

@Table(database = TubDatabase.class)
public class Stop extends BaseModel {

    @Column
    @PrimaryKey
    private int id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private double latitude;

    @Column
    private double longitude;

    public Stop(){

    }

    public Stop(String name, String description, double latitude, double longitude){
        this.name = name;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
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

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
