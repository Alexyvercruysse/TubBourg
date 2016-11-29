package com.example.alexy.tubtabbar.Repositories;

import android.database.Cursor;

import com.example.alexy.tubtabbar.Entities.Line;


/**
 * Created by iem on 19/10/2016.
 */

public interface LineRepository {
    long addLine(Line line);

    int modLine(Line line);

    int supLine(Line line);

    Line getLine(int id);

    Cursor getAllLine();
}
