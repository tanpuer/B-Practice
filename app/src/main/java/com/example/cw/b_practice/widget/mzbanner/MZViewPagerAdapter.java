package com.example.cw.b_practice.widget.mzbanner;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.example.cw.b_practice.module.videoDetail.adapter.ViewPagerAdapter;

import java.util.List;

/**
 * Created by cw on 2017/6/14.
 */

public class MZViewPagerAdapter extends ViewPagerAdapter {

    int realSize;
    public MZViewPagerAdapter(FragmentManager fm, List<String> titles, List<Fragment> fragments) {
        super(fm, titles, fragments);
        realSize = fragments.size();
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public Fragment getItem(int position) {
        return super.getItem(position);
    }

}
