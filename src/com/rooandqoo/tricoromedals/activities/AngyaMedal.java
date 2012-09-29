
package com.rooandqoo.tricoromedals.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.rooandqoo.tricoromedals.R;
import com.rooandqoo.tricoromedals.fragments.BaseFragment;

public class AngyaMedal extends BaseActivity {

    AngyaCollectionPagerAdapter angyaCollectionPagerAdapter;
    ViewPager viewPager;

    BaseFragment visitFragment;
    BaseFragment completeFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.angya_medal);

        visitFragment = new BaseFragment(14);
        completeFragment = new BaseFragment(15);

        angyaCollectionPagerAdapter =
                new AngyaCollectionPagerAdapter(
                        getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.angya_medal_pager);
        viewPager.setAdapter(angyaCollectionPagerAdapter);
    }

    public class AngyaCollectionPagerAdapter extends
            FragmentStatePagerAdapter {

        String[] titles = {
                "行脚", "地方制覇"
        };

        public AngyaCollectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            switch (i) {
                case 0:
                    return visitFragment;
                case 1:
                    return completeFragment;
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }

}
