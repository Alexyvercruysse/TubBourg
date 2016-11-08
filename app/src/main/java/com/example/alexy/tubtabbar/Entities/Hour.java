package com.example.alexy.tubtabbar.Entities;
import java.util.Date;
import java.util.List;

import static android.R.id.list;

/**
 * Created by iem on 18/10/2016.
 */

public class Hour {

    private int id;
    private int stop;
    private int line;
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

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }
}
