
package com.rooandqoo.tricoromedals.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "tricoromedal";

    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_MEDAL_TABLE_SQL = "create table medals " +
            "(rowid integer primary key autoincrement, " +
            "category integer not null, " +
            "color integer not null, " +
            "description text not null, " +
            "checked integer)";

    private static final String DROP_MEDAL_TABLE_SQL = "drop table if exists medals";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_MEDAL_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_MEDAL_TABLE_SQL);
        onCreate(db);
    }
}
