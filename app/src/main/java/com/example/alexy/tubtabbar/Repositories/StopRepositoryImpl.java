package com.example.alexy.tubtabbar.Repositories;

import android.util.Log;

import com.example.alexy.tubtabbar.Entities.Hour;
import com.example.alexy.tubtabbar.Entities.Stop;
import com.example.alexy.tubtabbar.Entities.Stop_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.io.IOException;
import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by iem on 30/11/2016.
 */

public class StopRepositoryImpl implements  StopRepository {

    private HourRepository hourRepository;

    @Override
    public List<Stop> listStops() {
        return  SQLite.select().from(Stop.class).queryList();
    }

    @Override
    public Stop getStopById(int idStop) {
        return SQLite.select().from(Stop.class).where(Stop_Table.id.eq(idStop)).querySingle();
    }

    @Override
    public Stop getStopByName(String nameStop) {
        //QuerySingle has a limit of 1 result so we don't need to throw an exception, "HardCodding" ^^
        return SQLite.select().from(Stop.class).where(Stop_Table.name.is(nameStop)).querySingle();
    }

    @Override
    public List<Stop> listStopByIdLine(int idLine) {
        hourRepository = new HourRepositoryImpl();
        List<Hour> listHour = hourRepository.listHoursByIdLine(idLine);

        //Little algorithm to add list the stops only once
        List<Stop> listStop = new ArrayList<>();
        for(Hour hour : listHour){
            Stop stop = getStopById(hour.getIdStop());
            if(!listStop.contains(stop)){
                listStop.add(stop);
            }
        }
        return listStop;
    }

    @Override
    public void addStop(Stop stop) {
        SQLite.insert(Stop.class).values(stop);
    }

}
