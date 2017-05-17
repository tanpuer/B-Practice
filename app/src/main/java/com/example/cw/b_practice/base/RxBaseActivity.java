package com.example.cw.b_practice.base;


import android.os.Bundle;
import android.support.annotation.Nullable;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

/**
 * Created by cw on 2017/5/15.
 */

public abstract class RxBaseActivity extends RxAppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initViews(savedInstanceState);
        initToolBar();
    }

    protected abstract int getLayoutId();

    protected abstract void initViews(Bundle savedInstanceState);

    protected abstract void initToolBar();

    protected void loadData(){}

    protected void showProgressBar(){}

    protected void hideProgressBar(){}

    protected void initRecyclerView(){}

    protected void initRefreshLayout(){}

    protected void finishTask(){}

    //todo 切换主题的逻辑 待做
}
