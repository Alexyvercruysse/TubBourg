package com.example.alexy.tubtabbar.Repositories;

import com.example.alexy.tubtabbar.Entities.Line;
import com.example.alexy.tubtabbar.Entities.Line_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

/**
 * Created by iem on 30/11/2016.
 */

public class LineRepositoryImpl implements LineRepository {

    @Override
    public List<Line> listLines() {
        return  SQLite.select().from(Line.class).queryList();
    }

    @Override
    public Line getLineById(int idLine) {
        return  SQLite.select().from(Line.class).where(Line_Table.id.eq(idLine)).querySingle();
    }
}
