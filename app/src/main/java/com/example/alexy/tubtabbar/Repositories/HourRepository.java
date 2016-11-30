package com.example.alexy.tubtabbar.Repositories;

import com.example.alexy.tubtabbar.Entities.Hour;
import com.example.alexy.tubtabbar.Entities.Stop;

import java.util.List;

/**
 * Created by iem on 30/11/2016.
 */

public interface HourRepository {

    List<Hour> listHoursByNameStop(String nameStop);

    List<Hour> listHoursByIdLine(int idLine);

    List<Hour> listHoursByIdLineAndIdStopAndIdDirection(int idLine, int idStop, int idDirection);

    List<Integer> listDirectionsByIdLine(int idLine);


}
