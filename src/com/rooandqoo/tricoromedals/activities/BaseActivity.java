
package com.rooandqoo.tricoromedals.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.R;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.rooandqoo.tricoromedals.database.DatabaseHelper;
import com.rooandqoo.tricoromedals.database.MedalsDao;
import com.rooandqoo.tricoromedals.utils.BackupTask;
import com.rooandqoo.tricoromedals.utils.Constants;
import com.rooandqoo.tricoromedals.utils.RestoreTask;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

public class BaseActivity extends SherlockFragmentActivity {
    private int currentActivityId = 0;

    private static final int ACTIVITY_INDEX_PLAY = 0;
    private static final int ACTIVITY_INDEX_CLEAR = 1;
    private static final int ACTIVITY_INDEX_SCORE = 2;
    private static final int ACTIVITY_INDEX_STEPUP = 3;
    private static final int ACTIVITY_INDEX_ANGYA = 4;
    private static final int ACTIVITY_INDEX_LEGEND = 5;
    private static final int ACTIVITY_INDEX_OMEGA = 6;
    private static final int ACTIVITY_INDEX_OTHER = 7;

    private AlertDialog backupDialog;
    private AlertDialog restoreDialog;
    public ProgressDialog progressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_Sherlock);
        super.onCreate(savedInstanceState);

        SharedPreferences pref = getSharedPreferences(Constants.PREFERENCE_FILE, MODE_PRIVATE);

        int appVersion = pref.getInt(Constants.PREF_APP_VERSION, 0);
        int versionCode = 0;
        try {
            versionCode = getPackageManager().getPackageInfo(this.getPackageName(),
                    PackageManager.GET_META_DATA).versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }

        if (appVersion < versionCode) {
            showInformation();
            pref.edit().putInt(Constants.PREF_APP_VERSION, versionCode).commit();
        }

        currentActivityId = getIntent().getIntExtra(Constants.PARAM_ACTIVITY_INDEX, 0);

        ActionBar actionBar = this.getSupportActionBar();
        String[] navigationItems = getResources().getStringArray(R.array.array_category);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, navigationItems);

        actionBar.setListNavigationCallbacks(adapter, new ActionBar.OnNavigationListener() {

            @Override
            public boolean onNavigationItemSelected(int itemPosition, long itemId) {
                showActivity(itemId);
                return false;
            }
        });

        actionBar.setBackgroundDrawable(new ColorDrawable(getResources()
                .getColor(R.color.actionbar)));
        actionBar.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        actionBar.setSelectedNavigationItem(currentActivityId);
        if (currentActivityId != 0) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        backupDialog = new AlertDialog.Builder(this).setTitle(R.string.title_backup)
                .setMessage(R.string.msg_backup)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        backup();
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).create();

        restoreDialog = new AlertDialog.Builder(this).setTitle(R.string.title_restore)
                .setMessage(R.string.msg_restore)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        restore();
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).create();

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
                break;
            case R.id.menu_backup:
                backupDialog.show();
                break;
            case R.id.menu_restore:
                restoreDialog.show();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    public void showActivity(long itemId) {
        Intent intent = null;
        if (currentActivityId != itemId) {
            switch ((int) itemId) {
                case ACTIVITY_INDEX_PLAY:
                    intent = new Intent(this, PlayMedal.class);
                    intent.putExtra(Constants.PARAM_ACTIVITY_INDEX, ACTIVITY_INDEX_PLAY);
                    break;
                case ACTIVITY_INDEX_CLEAR:
                    intent = new Intent(this, ClearMedal.class);
                    intent.putExtra(Constants.PARAM_ACTIVITY_INDEX, ACTIVITY_INDEX_CLEAR);
                    break;
                case ACTIVITY_INDEX_SCORE:
                    intent = new Intent(this, ScoreMedal.class);
                    intent.putExtra(Constants.PARAM_ACTIVITY_INDEX, ACTIVITY_INDEX_SCORE);
                    break;
                case ACTIVITY_INDEX_STEPUP:
                    intent = new Intent(this, StepupMedal.class);
                    intent.putExtra(Constants.PARAM_ACTIVITY_INDEX, ACTIVITY_INDEX_STEPUP);
                    break;
                case ACTIVITY_INDEX_ANGYA:
                    intent = new Intent(this, AngyaMedal.class);
                    intent.putExtra(Constants.PARAM_ACTIVITY_INDEX, ACTIVITY_INDEX_ANGYA);
                    break;
                case ACTIVITY_INDEX_LEGEND:
                    intent = new Intent(this, LegendCross.class);
                    intent.putExtra(Constants.PARAM_ACTIVITY_INDEX, ACTIVITY_INDEX_LEGEND);
                    break;
                case ACTIVITY_INDEX_OMEGA:
                    intent = new Intent(this, OmegaAttack.class);
                    intent.putExtra(Constants.PARAM_ACTIVITY_INDEX, ACTIVITY_INDEX_OMEGA);
                    break;
                case ACTIVITY_INDEX_OTHER:
                    intent = new Intent(this, OtherMedal.class);
                    intent.putExtra(Constants.PARAM_ACTIVITY_INDEX, ACTIVITY_INDEX_OTHER);
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

        int[] checkedMedalCount = new int[3];
        int[] totalMedalCount = new int[3];

        int checkedMedals = 0;
        int totalMedals = 0;

        for (int i = 0; i < 3; i++) {
            checkedMedals += checkedMedalCount[i] = medalsDao.countCheckedMedals(i);
            totalMedals += totalMedalCount[i] = medalsDao.countMedals(i);
        }

        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogLayout = inflater.inflate(R.layout.dialog_medal_stats, null);

        String statsString = getResources().getString(R.string.stats);

        ((TextView) dialogLayout.findViewById(R.id.stat01)).setText(String.format(statsString,
                checkedMedalCount[0], totalMedalCount[0]));
        ((TextView) dialogLayout.findViewById(R.id.stat02)).setText(String.format(statsString,
                checkedMedalCount[1], totalMedalCount[1]));
        ((TextView) dialogLayout.findViewById(R.id.stat03)).setText(String.format(statsString,
                checkedMedalCount[2], totalMedalCount[2]));
        ((TextView) dialogLayout.findViewById(R.id.stat_total)).setText(String.format(statsString,
                checkedMedals, totalMedals));

        AlertDialog statisticDialog = new AlertDialog.Builder(this)
                .setTitle(R.string.dialog_title_stats)
                .setView(dialogLayout)
                .setNegativeButton(R.string.close, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        dialog.cancel();
                    }
                }).create();
        db.close();
        statisticDialog.show();
    }

    private void showInformation() {

        String history = getHistory();

        new AlertDialog.Builder(this).setTitle(R.string.dialog_title_info)
                .setMessage(history)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {

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

    private void backup() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage(getResources().getString(R.string.making_backup));
        progressDialog.setCancelable(false);
        progressDialog.show();

        // 画面を縦で固定
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // スリープしないように
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        new BackupTask(this).execute();
    }

    private void restore() {

        String filePath = Environment.getExternalStorageDirectory() + File.separator
                + Constants.DIR_NAME + File.separator + Constants.FILE_NAME;
        File backupFile = new File(filePath);
        if (!backupFile.exists()) {
            Toast.makeText(this, R.string.no_backup_file, Toast.LENGTH_LONG).show();
            return;
        }
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage(getResources().getString(R.string.restore));
        progressDialog.setCancelable(false);
        progressDialog.show();

        // 画面を縦で固定
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // スリープしないように
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        (new RestoreTask(this)).execute();
    }

}
