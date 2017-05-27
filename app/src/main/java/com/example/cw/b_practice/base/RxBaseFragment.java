package com.example.cw.b_practice.base;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle2.components.support.RxFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by cw on 2017/5/15.
 */

public abstract class RxBaseFragment extends RxFragment {

    private View parentView;

    private FragmentActivity mActivity;

    //标记位，fragment完成初始化
    protected boolean isPrepared;
    //标记位, fragment可见
    protected boolean isVisible;

    private Unbinder binder;

    protected abstract int getResLayoutId();

    protected abstract void finishCreateView(View view, Bundle savedInstanceState);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        parentView = inflater.inflate(getResLayoutId(), container, false);
        binder = ButterKnife.bind(this, parentView);
        return parentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        finishCreateView(view,savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = (FragmentActivity) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mActivity = null;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            isVisible = true;
            onVisible();
        }else {
            isVisible = false;
            onInvisible();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binder.unbind();
    }

    protected void onVisible() {
        lazyLoad();
    }

    public View getParentView(){
        return parentView;
    }

    protected  void lazyLoad() {}

    protected void onInvisible() {}

    protected void showProgressBar() {}

    protected void hideProgressBar() {}

    protected void initRecyclerView() {}

    protected void initRefreshLayout() {}

    protected void finishTask() {}
}
