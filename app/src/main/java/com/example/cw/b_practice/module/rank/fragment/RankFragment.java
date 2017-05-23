package com.example.cw.b_practice.module.rank.fragment;

import android.os.Bundle;
import android.view.View;

import com.example.cw.b_practice.R;
import com.example.cw.b_practice.base.RxBaseFragment;

/**
 * Created by cw on 2017/5/23.
 */

public class RankFragment extends RxBaseFragment implements IRankFragmentContract.IRankFragmentView {

    public static RankFragment newInstance(){
        return new RankFragment();
    }

    private IRankFragmentContract.IRankFragmentPresenter mPresenter;

    @Override
    protected int getResLayoutId() {
        return R.layout.fragment_rank;
    }

    @Override
    protected void finishCreateView(View view, Bundle savedInstanceState) {
        if (mPresenter == null){
            setPresenter(new RankFragmentPresenter(this));
        }
    }

    @Override
    public void setPresenter(IRankFragmentContract.IRankFragmentPresenter presenter) {
        mPresenter = presenter;
    }
}
