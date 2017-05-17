package com.example.cw.b_practice.module.mainfragment;

import android.os.Bundle;
import android.view.View;

import java.lang.ref.WeakReference;

/**
 * Created by cw on 2017/5/16.
 */

public class MainFragmentPresenter implements IMainFragmentContract.IMainFragmentPresenter {

    private WeakReference<IMainFragmentContract.IMainFragmentView> mViews;

    public MainFragmentPresenter(IMainFragmentContract.IMainFragmentView view){
        mViews = new WeakReference<IMainFragmentContract.IMainFragmentView>(view);
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
