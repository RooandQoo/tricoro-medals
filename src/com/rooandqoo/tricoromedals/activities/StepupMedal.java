
package com.rooandqoo.tricoromedals.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.rooandqoo.tricoromedals.R;
import com.rooandqoo.tricoromedals.fragments.BaseFragment;
import com.rooandqoo.tricoromedals.utils.Constants;

public class StepupMedal extends BaseActivity {

    StepupCollectionPagerAdapter stepupCollectionPagerAdapter;
    ViewPager viewPager;

    BaseFragment classOneFragment;
    BaseFragment classTwoFragment;
    BaseFragment classThreeFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.stepup_medal);

        classOneFragment = new BaseFragment(Constants.MEDAL_CATEGORY_SU_1);
        classTwoFragment = new BaseFragment(Constants.MEDAL_CATEGORY_SU_2);
        classThreeFragment = new BaseFragment(Constants.MEDAL_CATEGORY_SU_3);

        stepupCollectionPagerAdapter =
                new StepupCollectionPagerAdapter(
                        getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.stepup_medal_pager);
        viewPager.setAdapter(stepupCollectionPagerAdapter);
    }

    public class StepupCollectionPagerAdapter extends
            FragmentStatePagerAdapter {

        String[] titles = getResources().getStringArray(R.array.array_stepup);

        public StepupCollectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            switch (i) {
                case 0:
                    return classOneFragment;
                case 1:
                    return classTwoFragment;
                case 2:
                    return classThreeFragment;
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
