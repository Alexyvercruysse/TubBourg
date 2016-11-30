package com.example.alexy.tubtabbar.Repositories;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by iem on 30/11/2016.
 */

@Database(name = TubDatabase.name, version = TubDatabase.version)
public class TubDatabase {

    public static final String name = "db";

    public static final int version = 10;
}
