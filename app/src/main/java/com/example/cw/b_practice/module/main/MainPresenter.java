package com.example.cw.b_practice.module.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;

import com.example.cw.b_practice.R;
import com.example.cw.b_practice.util.ConstantsUtil;
import com.example.cw.b_practice.util.PreferencesUtil;

import java.lang.ref.WeakReference;

/**
 * Created by cw on 2017/5/15.
 */

public class MainPresenter implements IMainContract.IMainPresenter {

    private WeakReference<IMainContract.IMainView> mViews;

    public MainPresenter(IMainContract.IMainView views) {
        mViews = new WeakReference<>(views);
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

    @Override
    public void onClick(View v) {
        final IMainContract.IMainView view = mViews.get();
        switch (v.getId()){
            case R.id.iv_head_noftiy:{
                break;
            }
            case R.id.header_avatar:{
                break;
            }
            case R.id.iv_head_switch_mode:{
                boolean isNight = PreferencesUtil.getBoolean(ConstantsUtil.SWITCH_MODE_KEY, false);
                if (isNight){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    PreferencesUtil.setBoolean(ConstantsUtil.SWITCH_MODE_KEY, false);
                }else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    PreferencesUtil.setBoolean(ConstantsUtil.SWITCH_MODE_KEY, true);
                }
                if (view != null){
                    view.reCreateView();
                }
                break;
            }
            default:
                break;
        }
    }
}
