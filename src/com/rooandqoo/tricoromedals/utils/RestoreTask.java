
package com.rooandqoo.tricoromedals.utils;

import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Environment;
import android.widget.Toast;

import com.rooandqoo.tricoromedals.R;
import com.rooandqoo.tricoromedals.activities.BaseActivity;
import com.rooandqoo.tricoromedals.database.DatabaseHelper;
import com.rooandqoo.tricoromedals.database.MedalsDao;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;

public class RestoreTask extends AsyncTask<Void, Void, Void> {

    BaseActivity a;

    public RestoreTask(BaseActivity activity) {
        this.a = activity;
    }

    @Override
    protected Void doInBackground(Void... params) {
        restore();
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        a.progressDialog.dismiss();
        Toast.makeText(a, R.string.restore_fin, Toast.LENGTH_SHORT).show();
        a.recreate();
    }

    public void restore() {

        DatabaseHelper dbHelper = new DatabaseHelper(a.getApplicationContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        MedalsDao medalsDao = new MedalsDao(db);
        String filePath = Environment.getExternalStorageDirectory() + File.separator
                + Constants.DIR_NAME + File.separator + Constants.FILE_NAME;

        try {
            File file = new File(filePath);
            byte[] fileByte = new byte[(int) file.length()];
            FileInputStream fi = new FileInputStream(file);

            String backupData;
            fi.read(fileByte);
            backupData = new String(fileByte);

            fi.close();

            JSONArray array = new JSONArray(backupData);
            JSONObject json;
            for (int i = 0; i < array.length(); i++) {
                json = array.getJSONObject(i);
                medalsDao.updateFromDesc(json.getString(Constants.KEY_NAME),
                        json.getInt(Constants.KEY_CHECKED));
            }
        } catch (Exception e) {

        } finally {
            db.close();
        }

    }
}
