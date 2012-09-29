
package com.rooandqoo.tricoromedals.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.actionbarsherlock.R;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;

public class BaseActivity extends SherlockFragmentActivity {
    private int currentActivityId = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_Sherlock);
        super.onCreate(savedInstanceState);

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
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            showActivity(0);
        } else {
            return super.onOptionsItemSelected(item);
        }
        return true;
    }

    public void showActivity(long itemId) {
        Log.v("debug", "current:" + currentActivityId + " itemId:" + itemId);
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

}
