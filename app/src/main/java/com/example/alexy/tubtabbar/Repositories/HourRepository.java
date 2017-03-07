package com.example.alexy.tubtabbar.Repositories;

import com.example.alexy.tubtabbar.Entities.Hour;

import java.util.List;

/**
 * Created by iem on 30/11/2016.
 */

public interface HourRepository {

    List<Hour> listHoursByNameStop(String nameStop);

    List<Hour> listHoursByIdLine(int idLine);

    List<Hour> listHoursByDirectionStopAndLine(int direction, int idStop, int idLine);

    void addHour(Hour hour);
}
