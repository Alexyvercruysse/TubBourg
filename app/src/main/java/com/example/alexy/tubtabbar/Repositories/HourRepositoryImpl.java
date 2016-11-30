package com.example.alexy.tubtabbar.Repositories;

import com.example.alexy.tubtabbar.Entities.Hour;
import com.example.alexy.tubtabbar.Entities.Hour_Table;
import com.example.alexy.tubtabbar.Entities.Stop;
import com.example.alexy.tubtabbar.Entities.Stop_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

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

}
