package com.example.alexy.tubtabbar.Repositories;

import com.example.alexy.tubtabbar.Entities.Stop;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * Created by iem on 30/11/2016.
 */

public interface StopRepository {

    List<Stop> listStops();

    Stop getStopById(int idStop);

    Stop getStopByName(String nameStop);

    List<Stop> listStopByIdLine(int idLine);

}
