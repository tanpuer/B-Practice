package com.example.cw.b_practice.module.rank.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.cw.b_practice.module.rank.fragment.RankFragment;

/**
 * Created by cw on 2017/5/23.
 */

public class RankAdapter extends FragmentPagerAdapter {

    private String[] titles;
    private String[] types;
    private String toolbarTitle;
    private Fragment[] mFragments;

    public RankAdapter(FragmentManager fm, String[] titles, String[] types, String toolbarTitle) {
        super(fm);
        this.titles = titles;
        this.types = types;
        this.toolbarTitle = toolbarTitle;
        mFragments = new Fragment[titles.length];
    }

    @Override
    public Fragment getItem(int position) {
        if (mFragments[position] ==null){
            return RankFragment.newInstance();
        }
        return mFragments[position];
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
