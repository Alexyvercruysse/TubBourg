package com.example.alexy.tubtabbar.Entities;
import com.example.alexy.tubtabbar.Repositories.TubDatabase;
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
    private int stop;

    @Column
    private int line;

    @Column
    private int direction;

    @Column
    private String hour;

    public Hour(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStop() {
        return stop;
    }

    public void setStop(int stop) {
        this.stop = stop;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }
}
