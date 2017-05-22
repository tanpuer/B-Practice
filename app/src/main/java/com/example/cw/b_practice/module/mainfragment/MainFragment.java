package com.example.cw.b_practice.module.mainfragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.cw.b_practice.MyApplication;
import com.example.cw.b_practice.R;
import com.example.cw.b_practice.base.RxBaseFragment;
import com.example.cw.b_practice.module.mainfragment.adapter.MainPagerAdapter;
import com.flyco.tablayout.SlidingTabLayout;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by cw on 2017/5/16.
 */

public class MainFragment extends RxBaseFragment implements IMainFragmentContract.IMainFragmentView{

    private IMainFragmentContract.IMainFragmentPresenter mPresenter;
    private Toolbar mToolbar;
    private CircleImageView mCircleImage;
    private SlidingTabLayout mSlidingTabs;
    private ViewPager mViewPager;
    private MainPagerAdapter mAdapter;

    public static MainFragment newInstance(){
        return new MainFragment();
    }

    @Override
    protected int getResLayoutId() {
        return R.layout.fragment_home_pager;
    }

    @Override
    protected void finishCreateView(View view, Bundle savedInstanceState) {
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mCircleImage = (CircleImageView) view.findViewById(R.id.circle_image);
        mSlidingTabs = (SlidingTabLayout) view.findViewById(R.id.sliding_tabs);
        mViewPager = (ViewPager) view.findViewById(R.id.view_pager);
        mAdapter = new MainPagerAdapter(getChildFragmentManager(), MyApplication.getInstance());
        mViewPager.setOffscreenPageLimit(5);
        mViewPager.setAdapter(mAdapter);
        mSlidingTabs.setViewPager(mViewPager);
        mViewPager.setCurrentItem(0);
        if (mPresenter == null){
            setPresenter(new MainFragmentPresenter(this));
        }
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    public void setPresenter(IMainFragmentContract.IMainFragmentPresenter presenter) {
        mPresenter = presenter;
    }
}
