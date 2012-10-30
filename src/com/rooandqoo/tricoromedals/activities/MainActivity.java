
package com.rooandqoo.tricoromedals.activities;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.view.WindowManager;

import com.rooandqoo.tricoromedals.R;
import com.rooandqoo.tricoromedals.database.DatabaseHelper;
import com.rooandqoo.tricoromedals.utils.Constants;
import com.rooandqoo.tricoromedals.utils.InitTask;
import com.rooandqoo.tricoromedals.utils.UpdateTask;

import java.io.File;

public class MainActivity extends Activity {

    public ProgressDialog progressDialog;

    private static int DIALOG_PROGRESS = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // ディレクトリ生成
        String filePath = Environment.getExternalStorageDirectory() + File.separator
                + Constants.DIR_NAME;
        File dir = new File(filePath);

        try {
            if (!dir.exists()) {
                dir.mkdir();
            }
        } catch (Exception ex) {
            ex.getMessage();
        }

        SharedPreferences pref = getSharedPreferences(Constants.PREFERENCE_FILE, MODE_PRIVATE);
        int db_version = pref.getInt(Constants.PREF_DB_VERSION, 0);
        if (db_version == 0) {
            prepareUpdate();
            init();
        }
        else if (db_version < Constants.NEWEST_DB_VERSION) {
            prepareUpdate();
            new UpdateTask(this, db_version).execute();
        } else {
            Intent intent = new Intent(this, PlayMedal.class);
            startActivity(intent);
            finish();
        }
    }

    private void prepareUpdate() {
        showDialog(DIALOG_PROGRESS);

        // 画面を縦で固定
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // スリープしないように
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    private void init() {

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        dbHelper.onUpgrade(db, 1, 1);

        new InitTask(this).execute();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        return createDialog(id);
    }

    private Dialog createDialog(int id) {
        if (id == DIALOG_PROGRESS) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage(getResources().getString(
                    R.string.dialog_message_loading));
            progressDialog.setCancelable(false);
            return progressDialog;
        }
        return null;
    }

}
