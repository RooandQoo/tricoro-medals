
package com.rooandqoo.tricoromedals.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.actionbarsherlock.R;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.rooandqoo.tricoromedals.database.DatabaseHelper;
import com.rooandqoo.tricoromedals.database.MedalsDao;
import com.rooandqoo.tricoromedals.utils.Constants;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class BaseActivity extends SherlockFragmentActivity {
    private int currentActivityId = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_Sherlock);
        super.onCreate(savedInstanceState);

        SharedPreferences pref = getSharedPreferences(Constants.PREFERENCE_FILE, MODE_PRIVATE);

        int appVersion = pref.getInt("APP_VERSION", 0);
        int versionCode = 0;
        try {
            versionCode = getPackageManager().getPackageInfo(this.getPackageName(),
                    PackageManager.GET_META_DATA).versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }

        if (appVersion < versionCode) {
            showInformation();
            pref.edit().putInt("APP_VERSION", versionCode).commit();
        }

        currentActivityId = getIntent().getIntExtra("activity", 0);

        ActionBar actionBar = this.getSupportActionBar();
        String[] navigationItems = {
                "プレー系", "クリア系", "スコア系", "ステップアップ系", "行脚系", "その他"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, navigationItems);

        actionBar.setListNavigationCallbacks(adapter, new ActionBar.OnNavigationListener() {

            @Override
            public boolean onNavigationItemSelected(int itemPosition, long itemId) {
                showActivity(itemId);
                return false;
            }
        });

        actionBar.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        actionBar.setSelectedNavigationItem(currentActivityId);
        if (currentActivityId != 0) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getSupportMenuInflater();
        inflater.inflate(R.menu.base, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                showActivity(0);
                break;
            case R.id.menu_info:
                showInformation();
                break;
            case R.id.menu_count:
                showMedalsInfo();
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    public void showActivity(long itemId) {
        Intent intent = null;
        if (currentActivityId != itemId) {
            switch ((int) itemId) {
                case 0:
                    intent = new Intent(this, PlayMedal.class);
                    intent.putExtra("activity", 0);
                    break;
                case 1:
                    intent = new Intent(this, ClearMedal.class);
                    intent.putExtra("activity", 1);
                    break;
                case 2:
                    intent = new Intent(this, ScoreMedal.class);
                    intent.putExtra("activity", 2);
                    break;
                case 3:
                    intent = new Intent(this, StepupMedal.class);
                    intent.putExtra("activity", 3);
                    break;
                case 4:
                    intent = new Intent(this, AngyaMedal.class);
                    intent.putExtra("activity", 4);
                    break;
                case 5:
                    intent = new Intent(this, OtherMedal.class);
                    intent.putExtra("activity", 5);
                    break;
                default:
                    break;
            }
            startActivity(intent);
            finish();
        }
    }

    private void showMedalsInfo() {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        MedalsDao medalsDao = new MedalsDao(db);

        for (int i = 0; i < 3; i++) {
            Log.v("tricoro", "取得数：" + medalsDao.countCheckedMedals(i));
            Log.v("tricoro", "総数：" + medalsDao.countMedals(i));
        }
    }

    private void showInformation() {

        String history = getHistory();

        new AlertDialog.Builder(this).setTitle("インフォメーション").setMessage(history)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub

                    }
                }).create().show();
    }

    private String getHistory() {
        InputStream is = getResources().openRawResource(R.raw.history);
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        try {
            br = new BufferedReader(new InputStreamReader(is));
            String str;
            while ((str = br.readLine()) != null) {
                sb.append(str + "\n");
            }
            if (br != null)
                br.close();
            is.close();
        } catch (Exception e) {

        }
        return sb.toString();
    }
}
