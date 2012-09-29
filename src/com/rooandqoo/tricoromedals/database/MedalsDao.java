
package com.rooandqoo.tricoromedals.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.rooandqoo.tricoromedals.models.Medal;

import java.util.ArrayList;
import java.util.List;

public class MedalsDao {

    private static final String TABLE_NAME = "medals";
    private static final String COLUMN_ID = "rowid";
    private static final String COLUMN_CATEGORY = "category";
    private static final String COLUMN_COLOR = "color";
    private static final String COLUMN_DESC = "description";
    private static final String COLUMN_CHECK = "checked";
    private static final String[] COLUMNS = {
            COLUMN_ID, COLUMN_CATEGORY, COLUMN_COLOR, COLUMN_DESC, COLUMN_CHECK
    };
    private SQLiteDatabase db;

    public MedalsDao(SQLiteDatabase db) {
        this.db = db;
    }

    public long insert(Medal medal) {
        ContentValues values = new ContentValues();

        values.put(COLUMN_CATEGORY, medal.getCategory());
        values.put(COLUMN_COLOR, medal.getColor());
        values.put(COLUMN_DESC, medal.getDescription());
        values.put(COLUMN_CHECK, 0);
        return db.insert(TABLE_NAME, null, values);
    }

    public int update(int rowid, int check) {
        Log.v("tricoro", "rowid:" + rowid);
        ContentValues values = new ContentValues();

        values.put(COLUMN_CHECK, check);
        String whereClause = "rowid = " + rowid;
        return db.update(TABLE_NAME, values, whereClause, null);
    }

    public int fixMedalFromDesc(String description) {
        ContentValues values = new ContentValues();

        values.put(COLUMN_DESC, description);
        String whereClause = "description = " + "'" + description.replace("'", "''") + "'";
        return db.update(TABLE_NAME, values, whereClause, null);
    }

    public List<Medal> findByCategory(int category) {
        List<Medal> medals = new ArrayList<Medal>();
        String selection = "category = " + category;
        Cursor cursor = db.query(TABLE_NAME, COLUMNS, selection, null, null, null, null);
        while (cursor.moveToNext()) {

            Medal medal = new Medal();
            medal.setRowid(cursor.getInt(0));
            medal.setCategory(cursor.getInt(1));
            medal.setColor(cursor.getInt(2));
            medal.setDescription(cursor.getString(3));
            medal.setCheck(cursor.getInt(4));
            medals.add(medal);
        }
        return medals;
    }

    public int deleteFromName(String description) {
        return db.delete(TABLE_NAME, "description = '" + description + "'", null);
    }
}
