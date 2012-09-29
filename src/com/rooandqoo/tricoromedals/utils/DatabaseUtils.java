
package com.rooandqoo.tricoromedals.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.sqlite.SQLiteDatabase;

import com.rooandqoo.tricoromedals.database.DatabaseHelper;
import com.rooandqoo.tricoromedals.database.MedalsDao;

public class DatabaseUtils {

    public static void updateDataBase(Context context, int version) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        MedalsDao medalsDao = new MedalsDao(db);

        final SharedPreferences pref = context.getSharedPreferences(Constants.PREFERENCE_FILE,
                context.MODE_PRIVATE);
        Editor e = pref.edit();

        db.close();
    }

}
