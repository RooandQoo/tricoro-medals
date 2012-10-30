
package com.rooandqoo.tricoromedals.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.rooandqoo.tricoromedals.R;
import com.rooandqoo.tricoromedals.fragments.BaseFragment;

public class LegendCross extends BaseActivity {

    LCCollectionPagerAdapter legendCrossCollectionPagerAdapter;
    ViewPager viewPager;

    BaseFragment oneToFiveFragment;
    BaseFragment sixToTwelveFragment;
    BaseFragment redBossAnotherFragment;
    BaseFragment redBossHyperFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.lc_medal);

        oneToFiveFragment = new BaseFragment(20);
        sixToTwelveFragment = new BaseFragment(21);
        redBossAnotherFragment = new BaseFragment(22);
        redBossHyperFragment = new BaseFragment(23);

        legendCrossCollectionPagerAdapter =
                new LCCollectionPagerAdapter(
                        getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.lc_medal_pager);
        viewPager.setAdapter(legendCrossCollectionPagerAdapter);
    }

    public class LCCollectionPagerAdapter extends
            FragmentStatePagerAdapter {

        String[] titles = getResources().getStringArray(R.array.array_legend);

        public LCCollectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            switch (i) {
                case 0:
                    return oneToFiveFragment;
                case 1:
                    return sixToTwelveFragment;
                case 2:
                    return redBossAnotherFragment;
                case 3:
                    return redBossHyperFragment;
            }
            return null;
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }

}
