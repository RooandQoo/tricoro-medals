
package com.rooandqoo.tricoromedals.activities;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.WindowManager;

import com.rooandqoo.tricoromedals.R;
import com.rooandqoo.tricoromedals.database.DatabaseHelper;
import com.rooandqoo.tricoromedals.utils.Constants;
import com.rooandqoo.tricoromedals.utils.InitTask;

public class MainActivity extends Activity {

    public ProgressDialog progressDialog;

    private static int DIALOG_PROGRESS = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        SharedPreferences pref = getSharedPreferences(Constants.PREFERENCE_FILE, MODE_PRIVATE);
        int db_version = pref.getInt("VERSION", 0);
        if (db_version == 0) {
            showDialog(DIALOG_PROGRESS);
            init();
        }
        else if (db_version < Constants.NEWEST_DB_VERSION) {
            // init();
        } else {
            Intent intent = new Intent(this, PlayMedal.class);
            startActivity(intent);
            finish();
        }
    }

    private void init() {

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        dbHelper.onUpgrade(db, 1, 1);

        // 画面を縦で固定
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // スリープしないように
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
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
            progressDialog.setMessage("読み込み中…");
            progressDialog.setCancelable(false);
            return progressDialog;

        }
        return null;
    }
}
