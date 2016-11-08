package com.example.alexy.tubtabbar.Repository;

import android.database.Cursor;

import com.example.alexy.tubtabbar.Entities.Stop;

import java.util.List;

/**
 * Created by iem on 19/10/2016.
 */

public interface StopRepository {

    long addStop(Stop stop);

    int modStop(Stop stop);

    int supStop(Stop stop);

    Stop getStop(int id);

    List<Stop> getAllStop();

    List<Stop> getStopsById(List<Integer> ids);
}
