package com.example.cw.b_practice.module.rank.fragment;

import android.os.Bundle;
import android.view.View;

import java.lang.ref.WeakReference;

/**
 * Created by cw on 2017/5/23.
 */

public class RankFragmentPresenter implements IRankFragmentContract.IRankFragmentPresenter {

    private WeakReference<IRankFragmentContract.IRankFragmentView> mView;

    public RankFragmentPresenter(IRankFragmentContract.IRankFragmentView view) {
        mView = new WeakReference<>(view);
    }

    @Override
    public void onCreate(Bundle args) {

    }

    @Override
    public void onCreateView(Bundle savedInstanceState) {

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {

    }
}
