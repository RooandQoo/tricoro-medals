
package com.rooandqoo.tricoromedals.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.rooandqoo.tricoromedals.R;
import com.rooandqoo.tricoromedals.fragments.BaseFragment;
import com.rooandqoo.tricoromedals.utils.Constants;

public class OtherMedal extends BaseActivity {

    OtherCollectionPagerAdapter otherCollectionPagerAdapter;
    ViewPager viewPager;

    BaseFragment djpFragment;
    BaseFragment classFragment;
    BaseFragment challengeFragment;
    BaseFragment recommendFragment;
    BaseFragment weeklyFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.other_medal);

        djpFragment = new BaseFragment(Constants.MEDAL_CATEGORY_DJP);
        classFragment = new BaseFragment(Constants.MEDAL_CATEGORY_CLASS);
        challengeFragment = new BaseFragment(Constants.MEDAL_CATEGORY_RIVAL);
        recommendFragment = new BaseFragment(Constants.MEDAL_CATEGORY_RECOMMEND);
        weeklyFragment = new BaseFragment(Constants.MEDAL_CATEGORY_WEEKLY);

        otherCollectionPagerAdapter =
                new OtherCollectionPagerAdapter(
                        getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.other_medal_pager);
        viewPager.setAdapter(otherCollectionPagerAdapter);
    }

    public class OtherCollectionPagerAdapter extends
            FragmentStatePagerAdapter {

        String[] titles = getResources().getStringArray(R.array.array_other);

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
                case 4:
                    return weeklyFragment;
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
