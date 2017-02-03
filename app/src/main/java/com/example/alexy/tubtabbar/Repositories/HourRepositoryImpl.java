package com.example.alexy.tubtabbar.Repositories;

import android.util.Log;

import com.example.alexy.tubtabbar.Entities.Hour;
import com.example.alexy.tubtabbar.Entities.Hour_Table;
import com.example.alexy.tubtabbar.Entities.Stop;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iem on 30/11/2016.
 */

public class HourRepositoryImpl implements HourRepository {

    private StopRepository stopRepository;

    @Override
    public List<Hour> listHoursByNameStop(String nameStop) {
        stopRepository = new StopRepositoryImpl();
        Stop stop = stopRepository.getStopByName(nameStop);
        return SQLite.select().from(Hour.class).where(Hour_Table.idStop.eq(stop.getId())).queryList();
    }

    @Override
    public List<Hour> listHoursByIdLine(int idLine) {
        List<Hour> hours = new ArrayList<>();
        hours = SQLite.select().from(Hour.class).queryList();
        for(Hour hour : hours){
            Log.d("ID STOP", hour.getIdStop() + "");
            Log.d("ID LINE", hour.getIdLine() + "");

        }
        return SQLite.select().from(Hour.class).where(Hour_Table.idLine.eq(idLine)).queryList();
    }

    @Override
    public List<Hour> listHoursByDirectionStopAndLine(int direction, int idStop, int idLine) {
        return SQLite.select().from(Hour.class).where(Hour_Table.direction.eq(direction)).and(Hour_Table.idStop.eq(idStop)).and(Hour_Table.idLine.eq(idLine)).queryList();
    }

    @Override
    public void addHour(Hour hour) {
         SQLite.insert(Hour.class).values(hour);
    }
}
