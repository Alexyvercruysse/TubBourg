package com.example.alexy.tubtabbar.Repositories;

import com.example.alexy.tubtabbar.Entities.Hour;
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

    @Override
    public Line getLineByName(String nameLine) {
        return  SQLite.select().from(Line.class).where(Line_Table.name.eq(nameLine)).querySingle();
    }

    @Override
    public void addLine(Line line) {
        SQLite.insert(Line.class).values(line);
    }
}
