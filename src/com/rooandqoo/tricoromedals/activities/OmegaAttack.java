
package com.rooandqoo.tricoromedals.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.rooandqoo.tricoromedals.R;
import com.rooandqoo.tricoromedals.fragments.BaseFragment;
import com.rooandqoo.tricoromedals.utils.Constants;

public class OmegaAttack extends BaseActivity {

    LCCollectionPagerAdapter omegaAttackCollectionPagerAdapter;
    ViewPager viewPager;

    BaseFragment sectorAFragment;
    BaseFragment sectorBFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.oa_medal);

        sectorAFragment = new BaseFragment(Constants.MEDAL_CATEGORY_OMEGA_1);
        sectorBFragment = new BaseFragment(Constants.MEDAL_CATEGORY_OMEGA_2);

        omegaAttackCollectionPagerAdapter =
                new LCCollectionPagerAdapter(
                        getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.oa_medal_pager);
        viewPager.setAdapter(omegaAttackCollectionPagerAdapter);
    }

    public class LCCollectionPagerAdapter extends
            FragmentStatePagerAdapter {

        String[] titles = getResources().getStringArray(R.array.array_omega);

        public LCCollectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            switch (i) {
                case 0:
                    return sectorAFragment;
                case 1:
                    return sectorBFragment;
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
