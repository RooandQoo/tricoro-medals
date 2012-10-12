
package com.rooandqoo.tricoromedals.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.rooandqoo.tricoromedals.R;
import com.rooandqoo.tricoromedals.fragments.BaseFragment;

public class ClearMedal extends BaseActivity {

    ClearCollectionPagerAdapter clearCollectionPagerAdapter;
    ViewPager viewPager;

    BaseFragment clearFragment;
    BaseFragment hardClearFragment;
    BaseFragment exHardFragment;
    BaseFragment fullComboFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.clear_medal);

        clearFragment = new BaseFragment(4);
        hardClearFragment = new BaseFragment(5);
        exHardFragment = new BaseFragment(6);
        fullComboFragment = new BaseFragment(7);

        clearCollectionPagerAdapter =
                new ClearCollectionPagerAdapter(
                        getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.clear_medal_pager);
        viewPager.setAdapter(clearCollectionPagerAdapter);
    }

    public class ClearCollectionPagerAdapter extends
            FragmentStatePagerAdapter {

        String[] titles = getResources().getStringArray(R.array.array_clear);

        public ClearCollectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            switch (i) {
                case 0:
                    return clearFragment;
                case 1:
                    return hardClearFragment;
                case 2:
                    return exHardFragment;
                case 3:
                    return fullComboFragment;
            }
            return null;
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }

}
