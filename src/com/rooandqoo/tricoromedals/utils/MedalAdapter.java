
package com.rooandqoo.tricoromedals.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.rooandqoo.tricoromedals.R;
import com.rooandqoo.tricoromedals.database.DatabaseHelper;
import com.rooandqoo.tricoromedals.database.MedalsDao;
import com.rooandqoo.tricoromedals.models.Medal;

import java.util.List;

public class MedalAdapter extends ArrayAdapter<Medal> {

    /** XMLからViewを生成するのに使うヤツ. */
    private LayoutInflater inflater;

    private Context context;
    private int resourceId;
    private List<Medal> items;

    private int rowid;

    public MedalAdapter(Context context, int resourceId, List<Medal> items) {
        super(context, resourceId, items);
        this.context = context;
        this.items = items;
        this.resourceId = resourceId;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view;

        // convertViewなんか入ってたら、それを使う
        if (convertView != null) {
            view = convertView;
        }
        // convertViewがnullなら新規作成
        else {
            view = inflater.inflate(resourceId, null);
        }

        Medal item = items.get(position);
        rowid = item.getRowid();

        switch (item.getColor()) {
            case 0:
                view.findViewById(R.id.color).setBackgroundColor(Color.rgb(230, 184, 175));
                break;
            case 1:
                view.findViewById(R.id.color).setBackgroundColor(Color.rgb(207, 226, 243));
                break;
            case 2:
                view.findViewById(R.id.color).setBackgroundColor(Color.rgb(255, 242, 204));
                break;
        }

        ((TextView) view.findViewById(R.id.description)).setText(item.getDescription());

        CheckBox cb = (CheckBox) view.findViewById(R.id.check);
        if (item.isChecked() == 1) {
            cb.setChecked(true);
        } else {
            cb.setChecked(false);
        }

        cb.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                int checkedState = 0;
                if (((CheckBox) v).isChecked()) {
                    checkedState = 1;
                }
                Medal medal = items.get(position);
                medal.setCheck(checkedState);
                DatabaseHelper dbHelper = new DatabaseHelper(context);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                MedalsDao medalsDao = new MedalsDao(db);
                medalsDao.update(medal.getRowid(), checkedState);
                db.close();

            }
        });

        // cb.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        //
        // @Override
        // public void onCheckedChanged(CompoundButton buttonView, boolean
        // isChecked) {
        // int checkedState = 0;
        // if (isChecked) {
        // checkedState = 1;
        // }
        // Medal medal = items.get(position);
        // medal.setCheck(checkedState);
        // Log.v("tricoro", "checked changed");
        // Log.v("tricoro", medal.dump());
        // DatabaseHelper dbHelper = new DatabaseHelper(context);
        // SQLiteDatabase db = dbHelper.getWritableDatabase();
        // MedalsDao medalsDao = new MedalsDao(db);
        // medalsDao.update(medal.getRowid(), checkedState);
        // db.close();
        // }
        //
        // });

        return view;
    }
}
