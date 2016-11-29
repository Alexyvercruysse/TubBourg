package com.example.alexy.tubtabbar.Repositories;
import android.content.Context;
import android.database.Cursor;


import com.example.alexy.tubtabbar.Entities.Hour;

/**
 * Created by iem on 19/10/2016.
 */

public interface HourRepository {

    long addHour(Hour hour);

    int modHour(Hour hour);

    int supHour(Hour hour);

    Hour getHour(Context context, int id);

    Cursor getAllHour();
}
