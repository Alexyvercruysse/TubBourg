package com.example.alexy.tubtabbar.Repositories;

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
        return SQLite.select().from(Hour.class).where(Hour_Table.stop.eq(stop.getId())).queryList();
    }

    @Override
    public List<Hour> listHoursByIdLine(int idLine) {
        return SQLite.select().from(Hour.class).where(Hour_Table.line.eq(idLine)).queryList();
    }

    @Override
    public List<Hour> listHoursByIdLineAndIdStopAndIdDirection (int idLine, int idStop, int idDirection) {
        return SQLite.select().from(Hour.class).where(Hour_Table.line.eq(idLine), Hour_Table.stop.eq(idStop), Hour_Table.direction.eq(idDirection)).queryList();
    }

    @Override
    public List<Integer> listDirectionsByIdLine(int idLine) {
        List<Hour> listHour = SQLite.select().from(Hour.class).where(Hour_Table.line.eq(idLine)).queryList();
        List<Integer> results = new ArrayList<>();
        boolean found = false;
        for(Hour hour : listHour) {
            int idDir = hour.getDirection();
            for(int res : results) {
                if (res == idDir) {
                    found = true;
                }
            }
            if(!found){
                results.add(idDir);
            }
            found = false;
        }
        return results;
    }

    @Override
    public void addHour(Hour hour) {
         SQLite.insert(Hour.class).values(hour);
    }
}
