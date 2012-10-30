
package com.rooandqoo.tricoromedals.utils;

import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Environment;
import android.widget.Toast;

import com.rooandqoo.tricoromedals.R;
import com.rooandqoo.tricoromedals.activities.BaseActivity;
import com.rooandqoo.tricoromedals.database.DatabaseHelper;
import com.rooandqoo.tricoromedals.database.MedalsDao;
import com.rooandqoo.tricoromedals.models.Medal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class BackupTask extends AsyncTask<Void, Void, Void> {

    BaseActivity a;

    public BackupTask(BaseActivity activity) {
        this.a = activity;
    }

    @Override
    protected Void doInBackground(Void... params) {
        makeJSON();
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        a.progressDialog.dismiss();
        Toast.makeText(a, R.string.making_backup_fin, Toast.LENGTH_SHORT).show();
    }

    public void makeJSON() {
        DatabaseHelper dbHelper = new DatabaseHelper(a.getApplicationContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        MedalsDao medalsDao = new MedalsDao(db);

        List<Medal> medals = medalsDao.findAll();

        JSONArray array = new JSONArray();
        JSONObject json;

        for (Medal medal : medals) {
            try {
                json = new JSONObject();
                json.put(Constants.KEY_NAME, medal.getDescription());
                json.put(Constants.KEY_CHECKED, medal.isChecked());

                array.put(json);
            } catch (JSONException e) {
                break;
            }

        }

        String filePath = Environment.getExternalStorageDirectory() + File.separator
                + Constants.DIR_NAME + File.separator + Constants.FILE_NAME;
        db.close();

        File jsonFile = new File(filePath);
        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(jsonFile)));
            pw.print(array.toString());
            pw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
