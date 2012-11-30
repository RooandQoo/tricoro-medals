
package com.rooandqoo.tricoromedals.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.rooandqoo.tricoromedals.R;
import com.rooandqoo.tricoromedals.fragments.BaseFragment;
import com.rooandqoo.tricoromedals.utils.Constants;

public class PlayMedal extends BaseActivity {
    PlayCollectionPagerAdapter playCollectionPagerAdapter;
    ViewPager viewPager;

    BaseFragment playLevelFragment;
    BaseFragment playNormalFragment;
    BaseFragment playHyperFragment;
    BaseFragment playAnotherFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.play_medal);

        playLevelFragment = new BaseFragment(Constants.MEDAL_CATEGORY_LV_PLAY);
        playNormalFragment = new BaseFragment(Constants.MEDAL_CATEGORY_NORMAL_PLAY);
        playHyperFragment = new BaseFragment(Constants.MEDAL_CATEGORY_HYPER_PLAY);
        playAnotherFragment = new BaseFragment(Constants.MEDAL_CATEGORY_ANOTHER_PLAY);

        playCollectionPagerAdapter =
                new PlayCollectionPagerAdapter(
                        getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.play_medal_pager);
        viewPager.setAdapter(playCollectionPagerAdapter);
    }

    public class PlayCollectionPagerAdapter extends
            FragmentStatePagerAdapter {

        String[] titles = getResources().getStringArray(R.array.array_play);

        public PlayCollectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            switch (i) {
                case 0:
                    return playLevelFragment;
                case 1:
                    return playNormalFragment;
                case 2:
                    return playHyperFragment;
                case 3:
                    return playAnotherFragment;
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
