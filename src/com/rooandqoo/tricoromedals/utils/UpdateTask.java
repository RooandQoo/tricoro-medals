
package com.rooandqoo.tricoromedals.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;

import com.rooandqoo.tricoromedals.activities.MainActivity;
import com.rooandqoo.tricoromedals.activities.PlayMedal;

public class UpdateTask extends AsyncTask<Void, Void, Void> {

    MainActivity activity;
    int dbVersion;

    public UpdateTask(Activity activity, int dbVersion) {
        this.activity = (MainActivity) activity;
        this.dbVersion = dbVersion;
    }

    @Override
    protected Void doInBackground(Void... params) {
        DatabaseUtils.updateDataBase(activity, dbVersion);
        return null;
    }

    public void onPostExecute() {
        activity.progressDialog.dismiss();
        Intent intent = new Intent(activity, PlayMedal.class);
        activity.startActivity(intent);
        activity.finish();

    }

}
