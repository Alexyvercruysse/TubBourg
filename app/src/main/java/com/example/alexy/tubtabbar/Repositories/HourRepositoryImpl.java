package com.example.alexy.tubtabbar.Repositories;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.alexy.tubtabbar.Entities.Hour;
import com.example.alexy.tubtabbar.Entities.Stop;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iem on 19/10/2016.
 */

public class HourRepositoryImpl implements HourRepository {


    private static final String TABLE_NAME = "hour";
    private static final String KEY_ID = "id";
    private static final String KEY_STOP ="stop";
    private static final String KEY_LINE = "line";
    private static final String KEY_HOUR = "hour";



    public static final String CREATE_TABLE_HOUR =
            String.format("CREATE TABLE %s ( %s INTEGER primary key, %s INTEGER, %s INTEGER, %s DATE );",
                    TABLE_NAME,
                    KEY_ID,
                    KEY_STOP,
                    KEY_LINE,
                    KEY_HOUR);

    private MySQLite dbSQLite;
    private SQLiteDatabase db;

    public HourRepositoryImpl(Context context)
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
    public long addHour(Hour hour) {

        ContentValues values = new ContentValues();

        values.put(KEY_ID, hour.getId());
        values.put(KEY_STOP, hour.getStop());
        values.put(KEY_LINE, hour.getLine());
        values.put(KEY_HOUR, hour.getHour());

        return db.insert(TABLE_NAME,null,values);
    }

    @Override
    public int modHour(Hour hour) {

        ContentValues values = new ContentValues();

        values.put(KEY_ID, hour.getId());
        values.put(KEY_STOP, hour.getStop());
        values.put(KEY_LINE, hour.getLine());
        ObjectMapper mapper = new ObjectMapper();

        String json = "";
        try {
            json = mapper.writeValueAsString(hour.getHour());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        values.put(KEY_HOUR, json);

        String where = KEY_ID + " = ?";
        String[] whereArgs = { hour.getId() + "" };

        return db.update(TABLE_NAME, values, where, whereArgs);
    }

    @Override
    public int supHour(Hour hour) {

        String where = KEY_ID + " = ?";
        String[] whereArgs = { hour.getId() + "" };

        return db.delete(TABLE_NAME, where, whereArgs);
    }

    @Override
    public Hour getHour(Context context, int id) {
        Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s WHERE %s = %s",TABLE_NAME, KEY_ID, id), null);
        Hour hour = cursorToHour(context, cursor);
        cursor.close();
        return hour;
    }

    @Override
    public Cursor getAllHour() {
        return db.rawQuery("SELECT * FROM "+TABLE_NAME, null);
    }

    public List<Stop> getStopsByLine (Context context, int idLigne){
        Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s WHERE %s = %s", TABLE_NAME, KEY_LINE,idLigne),null);
        List<Integer> ids = new ArrayList<>();
        while(cursor.moveToNext()){
            int idstop = cursor.getInt(cursor.getColumnIndex(KEY_STOP));
            if(!ids.contains(idstop)){
                ids.add(idstop);
            }
        }
        cursor.close();
       return  new StopRepositoryImpl(context).getStopsById(ids);
    }

    public List<String> getHoursByidLineAndidStop(Context context, int idLine, int idStop){
        Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s WHERE %s = %s AND %s = %s", TABLE_NAME, KEY_LINE,idLine,KEY_STOP,idStop),null);
        List<String> hours = new ArrayList<>();
        while (cursor.moveToNext()){
            hours.add(cursorToHour(context, cursor).getHour());
        }
        cursor.close();
      return hours;
    }

    private Hour cursorToHour(Context context, Cursor cursor){
        Hour hour = new Hour(); //move to first
            hour.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
            hour.setStop(cursor.getInt(cursor.getColumnIndex(KEY_STOP)));
            hour.setLine(cursor.getInt(cursor.getColumnIndex(KEY_LINE)));
            hour.setHour(cursor.getString(cursor.getColumnIndex(KEY_HOUR)));
        return hour;
    }
}
