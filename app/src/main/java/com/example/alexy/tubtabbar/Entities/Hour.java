package com.example.alexy.tubtabbar.Entities;
import com.example.alexy.tubtabbar.Repositories.TubDatabase;
import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.Date;
import java.util.List;

import static android.R.id.list;

/**
 * Created by iem on 18/10/2016.
 */

@Table(database = TubDatabase.class)
public class Hour extends BaseModel {

    @Column
    @PrimaryKey
    private int id;

    @Column
    private int idStop;

    @Column
    private int idLine;

    @Column
    private String hour;

    @Column
    private int direction;

    @Column
    private int idStartStop;

    @Column
    private int idEndStop;

    public Hour(){

    }

    public Hour(int stop, int line, String hour, int direction, int idStartStop, int idEndStop){
        this.idStop = stop;
        this.idLine = line;
        this.hour = hour;
        this.direction = direction;
        this.idStartStop = idStartStop;
        this.idEndStop = idEndStop;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdStop() {
        return idStop;
    }

    public void setIdStop(int stop) {
        this.idStop = stop;
    }

    public int getIdLine() {
        return idLine;
    }

    public void setIdLine(int line) {
        this.idLine = line;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getDirection() {
        return direction;
    }

    public int getIdEndStop() {
        return idEndStop;
    }

    public void setIdEndStop(int idEndStop) {
        this.idEndStop = idEndStop;
    }

    public int getIdStartStop() {
        return idStartStop;
    }

    public void setIdStartStop(int idStartStop) {
        this.idStartStop = idStartStop;
    }
}
