package com.example.cw.b_practice.module.rank;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.example.cw.b_practice.R;
import com.example.cw.b_practice.base.RxBaseActivity;
import com.example.cw.b_practice.module.rank.adapter.RankAdapter;
import com.flyco.tablayout.SlidingTabLayout;

/**
 * Created by cw on 2017/5/23.
 */

public class RankActivity extends RxBaseActivity implements IRankContract.IRankView{

    private IRankContract.IRankPresenter mPresenter;
    private SlidingTabLayout mSlidingTabLayout;
    private Toolbar mToolbar;
    private ViewPager mViewPager;
    private int type;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_rank;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        if (mPresenter == null){
            setPresenter(new RankPresenter(this));
        }
        mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        type = getIntent().getExtras().getInt("type",1);
        mPresenter.getTypeInfo(type);
    }

    @Override
    protected void initToolBar() {

    }

    @Override
    public void setPresenter(IRankContract.IRankPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void setTypeInfo(String[] mTitles, String[] mTypes, String title) {
        RankAdapter adapter = new RankAdapter(getSupportFragmentManager(), mTitles, mTypes, title);
        mToolbar.setTitle(title);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(mTitles.length-1);
        mSlidingTabLayout.setViewPager(mViewPager);
    }
}
