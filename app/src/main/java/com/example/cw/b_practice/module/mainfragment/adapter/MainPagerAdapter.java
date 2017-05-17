package com.example.cw.b_practice.module.mainfragment.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.cw.b_practice.R;
import com.example.cw.b_practice.module.homeRecommend.HomeRecommendFragment;

/**
 * Created by cw on 2017/5/16.
 */

public class MainPagerAdapter extends FragmentPagerAdapter{

    private final String[] TITLES;
    private final Fragment[] fragments;

    public MainPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        TITLES = context.getResources().getStringArray(R.array.sections);
        fragments = new Fragment[TITLES.length];
    }

    @Override
    public Fragment getItem(int position) {
        if (fragments[position] == null){
            switch (position){
                case 0:
                    fragments[0] = HomeRecommendFragment.newInstance();
                    break;
                case 1:
                    fragments[1] = HomeRecommendFragment.newInstance();
                    break;
                case 2:
                    fragments[2] = HomeRecommendFragment.newInstance();
                    break;
                case 3:
                    fragments[3] = HomeRecommendFragment.newInstance();
                    break;
                case 4:
                    fragments[4] = HomeRecommendFragment.newInstance();
                    break;
                case 5:
                    fragments[5] = HomeRecommendFragment.newInstance();
                    break;
                default:
                    break;
            }
        }
        return fragments[position];
    }

    @Override
    public int getCount() {
        return TITLES.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position];
    }
}
