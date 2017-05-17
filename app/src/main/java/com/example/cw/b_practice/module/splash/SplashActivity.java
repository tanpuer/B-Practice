package com.example.cw.b_practice.module.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.cw.b_practice.R;
import com.example.cw.b_practice.module.login.LoginActivity;
import com.example.cw.b_practice.module.main.MainActivity;
import com.example.cw.b_practice.util.ConstantsUtil;
import com.example.cw.b_practice.util.PreferencesUtil;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by cw on 2017/5/15.
 */

public class SplashActivity extends RxAppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setUpSplash();
    }

    private void setUpSplash() {
        Observable.timer(2000, TimeUnit.MILLISECONDS)
                .compose(bindToLifecycle())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> finishTask());
    }

    private void finishTask() {
        boolean isLogin = PreferencesUtil.getBoolean(ConstantsUtil.KEY, false);
        if (!isLogin){
            startActivity(new Intent(this, MainActivity.class));
        }else {
            startActivity(new Intent(this, LoginActivity.class));
        }
        this.finish();
    }

}
