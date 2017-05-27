package com.example.cw.b_practice.base.newBase;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.cw.b_practice.R;
import com.example.cw.b_practice.base.RxBaseFragment;

/**
 * Created by cw on 2017/5/26.
 */

public abstract class BaseFragment extends RxBaseFragment{

    protected View loadingView;
    protected View errorView;

    protected ViewGroup rootView;
    protected AnimationDrawable mAnimationDrawable;
    protected ImageView mImageView;

    protected abstract ViewGroup getRootView();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        rootView = getRootView();
        super.onViewCreated(view, savedInstanceState);
    }

    protected void showOrHideLoadingView(boolean show){
        if (loadingView == null){
            loadingView = LayoutInflater.from(getActivity()).inflate(R.layout.loading_view,null,false);
            rootView.addView(loadingView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            rootView.setVisibility(View.INVISIBLE);
            mImageView = (ImageView) loadingView.findViewById(R.id.loading_image);
            mAnimationDrawable = (AnimationDrawable) mImageView.getDrawable();
        }
        loadingView.setVisibility(show?View.VISIBLE:View.INVISIBLE);
        if (show){
            mAnimationDrawable.start();
        }else {
            mAnimationDrawable.stop();
        }
    }

    protected void showOrHideErrorView(boolean show){
        if (errorView == null){
            errorView = LayoutInflater.from(getActivity()).inflate(R.layout.error_view, null,false);
            rootView.addView(errorView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            rootView.setVisibility(View.INVISIBLE);
        }
        errorView.setVisibility(show?View.VISIBLE:View.INVISIBLE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mAnimationDrawable = null;
        mImageView = null;
        loadingView = null;

    }
}
