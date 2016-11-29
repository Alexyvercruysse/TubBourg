package com.example.alexy.tubtabbar.Repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.alexy.tubtabbar.Entities.Stop;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by iem on 18/10/2016.
 */

public class StopRepositoryImpl implements StopRepository {

    public static final String TABLE_NAME = "stop";
    public static final String KEY_ID = "id";
    public static final String KEY_NAME ="name";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_LATITUDE = "latitude";
    public static final String KEY_LONGITUDE = "longitude";

    public static final String CREATE_TABLE_STOP =
            String.format("CREATE TABLE %s ( %s INTEGER primary key, %s TEXT, %s TEXT, %s TEXT, %s TEXT );",
                    TABLE_NAME,
                    KEY_ID,
                    KEY_DESCRIPTION,
                    KEY_NAME,
                    KEY_LATITUDE,
                    KEY_LONGITUDE);

    private MySQLite dbSQLite;
    private SQLiteDatabase db;

    public StopRepositoryImpl(Context context)
    {
        dbSQLite = MySQLite.getInstance(context);
    }

    public void open()
    {
        db = dbSQLite.getWritableDatabase();
    }

    public void close()
    {
        db.close();
    }

    @Override
    public long addStop(Stop stop) {

        ContentValues values = new ContentValues();

        values.put(KEY_NAME, stop.getName());
        values.put(KEY_DESCRIPTION, stop.getDescription());
        values.put(KEY_LATITUDE, stop.getGpsCoord().latitude);
        values.put(KEY_LONGITUDE, stop.getGpsCoord().longitude);

        return db.insert(TABLE_NAME,null,values);
    }

    @Override
    public int modStop(Stop stop) {

        ContentValues values = new ContentValues();

        values.put(KEY_ID, stop.getId());
        values.put(KEY_NAME, stop.getName());
        values.put(KEY_DESCRIPTION, stop.getDescription());
        values.put(KEY_LATITUDE, stop.getGpsCoord().latitude);
        values.put(KEY_LONGITUDE, stop.getGpsCoord().longitude);

        String where = KEY_ID + " = ?";
        String[] whereArgs = { stop.getId() + "" };

        return db.update(TABLE_NAME, values, where, whereArgs);
    }

    @Override
    public int supStop(Stop stop) {

        String where = KEY_ID + " = ?";
        String[] whereArgs = { stop.getId() + "" };

        return db.delete(TABLE_NAME, where, whereArgs);
    }

    @Override
    public Stop getStop(int id) {
        open();
        Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s WHERE %s = %s",TABLE_NAME, KEY_ID, id), null);
        Stop stop = cursorToStop(cursor);
        cursor.close();

        return stop;
    }

    @Override
    public List<Stop> getAllStop() {
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        final List<Stop> stops = new ArrayList<>();
        while (cursor.moveToNext()){
            Stop stop = getStop(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
            stops.add(stop);
        }
        cursor.close();
        return stops;
    }

    @Override
    public List<Stop> getStopsById(List<Integer> ids){
        List<Stop> stops = new ArrayList<>();
        for(Integer i : ids){
            stops.add(getStop(i.intValue()));
        }
        return stops;
    }

    public int getStopByName(String name){
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " +KEY_NAME + " = "+"'" + name + "'", null);
        Stop stop = cursorToStop(cursor);
        cursor.close();
        return stop.getId();
    }

    private Stop cursorToStop(Cursor cursor){
        Stop stop = new Stop();
        if (cursor.moveToFirst()) {
            stop.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
            stop.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            stop.setDescription(cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION)));
            stop.setGpsCoord(
                    Double.parseDouble(cursor.getString(cursor.getColumnIndex(KEY_LATITUDE))),
                    Double.parseDouble(cursor.getString(cursor.getColumnIndex(KEY_LONGITUDE)))
            );
        }
        return stop;
    }

}
