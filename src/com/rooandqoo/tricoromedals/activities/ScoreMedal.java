
package com.rooandqoo.tricoromedals.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.rooandqoo.tricoromedals.R;
import com.rooandqoo.tricoromedals.fragments.BaseFragment;
import com.rooandqoo.tricoromedals.utils.Constants;

public class ScoreMedal extends BaseActivity {

    ScoreCollectionPagerAdapter scoreCollectionPagerAdapter;
    ViewPager viewPager;

    BaseFragment rankAFragment;
    BaseFragment rankAAFragment;
    BaseFragment rankAAAFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.score_medal);

        rankAFragment = new BaseFragment(Constants.MEDAL_CATEGORY_DJLV_A);
        rankAAFragment = new BaseFragment(Constants.MEDAL_CATEGORY_DJLV_AA);
        rankAAAFragment = new BaseFragment(Constants.MEDAL_CATEGORY_DJLV_AAA);

        scoreCollectionPagerAdapter =
                new ScoreCollectionPagerAdapter(
                        getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.score_medal_pager);
        viewPager.setAdapter(scoreCollectionPagerAdapter);
    }

    public class ScoreCollectionPagerAdapter extends
            FragmentStatePagerAdapter {

        String[] titles = getResources().getStringArray(R.array.array_score);

        public ScoreCollectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            switch (i) {
                case 0:
                    return rankAFragment;
                case 1:
                    return rankAAFragment;
                case 2:
                    return rankAAAFragment;
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
