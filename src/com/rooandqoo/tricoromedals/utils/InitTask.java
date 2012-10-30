
package com.rooandqoo.tricoromedals.utils;

import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

import com.rooandqoo.tricoromedals.R;
import com.rooandqoo.tricoromedals.activities.MainActivity;
import com.rooandqoo.tricoromedals.activities.PlayMedal;
import com.rooandqoo.tricoromedals.database.DatabaseHelper;
import com.rooandqoo.tricoromedals.database.MedalsDao;
import com.rooandqoo.tricoromedals.models.Medal;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class InitTask extends AsyncTask<Void, Void, Void> {

    MainActivity activity;
    SQLiteDatabase db;
    MedalsDao medalsDao;

    public InitTask(MainActivity a) {
        this.activity = a;
    }

    @Override
    protected Void doInBackground(Void... params) {
        DatabaseHelper dbHelper = new DatabaseHelper(activity);
        db = dbHelper.getWritableDatabase();
        medalsDao = new MedalsDao(db);

        dbHelper.onUpgrade(db, 1, 1);

        load();
        return null;
    }

    protected void onPostExecute(Void result) {
        db.close();
        Editor e = activity.getSharedPreferences(Constants.PREFERENCE_FILE, activity.MODE_PRIVATE)
                .edit();
        e.putInt(Constants.PREF_DB_VERSION, Constants.NEWEST_DB_VERSION);
        e.commit();

        activity.progressDialog.dismiss();

        Intent intent = new Intent(activity, PlayMedal.class);
        activity.startActivity(intent);
        activity.finish();
    }

    private void load() {
        InputStream is = activity.getResources().openRawResource(R.raw.init);
        BufferedReader br = null;
        String[] line;
        List<Medal> medals = new ArrayList<Medal>();
        Medal medal;
        try {
            br = new BufferedReader(new InputStreamReader(is));
            String str;
            while ((str = br.readLine()) != null) {
                line = str.split(",");
                medal = new Medal();
                medal.setCategory(Integer.parseInt(line[0]));
                medal.setColor(Integer.parseInt(line[1]));
                medal.setDescription(line[2]);
                medals.add(medal);
            }
            if (br != null)
                br.close();
            is.close();

            for (int i = 0; i < medals.size(); i++) {
                medalsDao.insert(medals.get(i));
            }
        } catch (Exception e) {
            Log.e("tricoro", e.getMessage());
        }
    }

}
