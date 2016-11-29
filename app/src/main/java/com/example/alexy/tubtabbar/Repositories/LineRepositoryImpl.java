package com.example.alexy.tubtabbar.Repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.alexy.tubtabbar.Entities.Line;

/**
 * Created by iem on 19/10/2016.
 */

public class LineRepositoryImpl implements LineRepository {

    //Inject
    private StopRepository stopRepository;

    private static final String TABLE_NAME = "line";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME ="name";
    private static final String KEY_DESCRIPTION = "description";



    public static final String CREATE_TABLE_LINE =
            String.format("CREATE TABLE %s ( %s INTEGER primary key, %s TEXT, %s TEXT);",
                    TABLE_NAME,
                    KEY_ID,
                    KEY_NAME,
                    KEY_DESCRIPTION);

    private MySQLite dbSQLite;
    private SQLiteDatabase db;

    public LineRepositoryImpl(Context context)
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
    public long addLine(Line line) {

        ContentValues values = new ContentValues();

        values.put(KEY_ID, line.getId());
        values.put(KEY_NAME, line.getName());
        values.put(KEY_DESCRIPTION, line.getDescription());

        return db.insert(TABLE_NAME, null, values);
    }

    @Override
    public int modLine(Line line) {

        ContentValues values = new ContentValues();

        values.put(KEY_ID, line.getId());
        values.put(KEY_NAME, line.getName());
        values.put(KEY_DESCRIPTION, line.getDescription());

        String where = KEY_ID + " = ?";
        String[] whereArgs = { line.getId() + "" };

        return db.update(TABLE_NAME, values, where, whereArgs);
    }

    @Override
    public int supLine(Line line) {

        String where = KEY_ID + " = ?";
        String[] whereArgs = { line.getId() + "" };

        return db.delete(TABLE_NAME, where, whereArgs);
    }

    @Override
    public Line getLine(int id) {
        open();
        Line line = new Line();
        Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s WHERE %s = %s",TABLE_NAME, KEY_ID, id), null);

        if (cursor.moveToFirst()) {

            line.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
            line.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            line.setDescription(cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION)));

            cursor.close();
        }

        return line;
    }

    @Override
    public Cursor getAllLine() {

        return db.rawQuery("SELECT * FROM "+TABLE_NAME, null);
    }

}
