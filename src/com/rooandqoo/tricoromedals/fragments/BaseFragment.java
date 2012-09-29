
package com.rooandqoo.tricoromedals.fragments;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragment;
import com.rooandqoo.tricoromedals.R;
import com.rooandqoo.tricoromedals.database.DatabaseHelper;
import com.rooandqoo.tricoromedals.database.MedalsDao;
import com.rooandqoo.tricoromedals.models.Medal;
import com.rooandqoo.tricoromedals.utils.MedalAdapter;

import java.util.List;

public class BaseFragment extends SherlockFragment {

    private int category;

    public BaseFragment(int category) {
        this.category = category;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.base_fragment, null);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        DatabaseHelper dbHelper = new DatabaseHelper(getActivity());
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        MedalsDao medalsDao = new MedalsDao(db);

        List<Medal> medals = medalsDao.findByCategory(category);
        for (int i = 0; i < medals.size(); i++) {
            Log.v("tricoro", medals.get(i).dump());
        }
        MedalAdapter adapter = new MedalAdapter(this.getSherlockActivity(),
                R.layout.list_item, medals);

        ListView lv = (ListView) getView().findViewById(R.id.list_medals);
        lv.setAdapter(adapter);

    }
}
