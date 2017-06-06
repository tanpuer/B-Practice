package com.example.cw.b_practice.module.videoPlay;

import android.os.Bundle;

import com.example.cw.b_practice.base.RxBaseActivity;

/**
 * Created by cw on 2017/6/5.
 */

public class VideoPlayActivity extends RxBaseActivity implements IVidePlayContract.IVideoPlayView{

    private IVidePlayContract.IVideoPlayPresenter mPresenter;

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        if (mPresenter == null){
            setPresenter(new VideoPlayPresenter(this));
        }
    }

    @Override
    protected void initToolBar() {

    }

    @Override
    public void setPresenter(IVidePlayContract.IVideoPlayPresenter presenter) {
        mPresenter = presenter;
    }
}
