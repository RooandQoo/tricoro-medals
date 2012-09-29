
package com.rooandqoo.tricoromedals.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.rooandqoo.tricoromedals.R;
import com.rooandqoo.tricoromedals.fragments.BaseFragment;

public class OtherMedal extends BaseActivity {

    OtherCollectionPagerAdapter otherCollectionPagerAdapter;
    ViewPager viewPager;

    BaseFragment djpFragment;
    BaseFragment classFragment;
    BaseFragment challengeFragment;
    BaseFragment recommendFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.other_medal);

        djpFragment = new BaseFragment(16);
        classFragment = new BaseFragment(17);
        challengeFragment = new BaseFragment(18);
        recommendFragment = new BaseFragment(19);

        otherCollectionPagerAdapter =
                new OtherCollectionPagerAdapter(
                        getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.other_medal_pager);
        viewPager.setAdapter(otherCollectionPagerAdapter);
    }

    public class OtherCollectionPagerAdapter extends
            FragmentStatePagerAdapter {

        String[] titles = {
                "DJ POINT", "段位合格", "ライバル挑戦状", "今日のイチオシ"
        };

        public OtherCollectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            switch (i) {
                case 0:
                    return djpFragment;
                case 1:
                    return classFragment;
                case 2:
                    return challengeFragment;
                case 3:
                    return recommendFragment;
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
