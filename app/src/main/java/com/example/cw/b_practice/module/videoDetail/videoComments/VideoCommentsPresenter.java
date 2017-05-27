package com.example.cw.b_practice.module.videoDetail.videoComments;

import android.os.Bundle;
import android.view.View;

import java.lang.ref.WeakReference;

/**
 * Created by cw on 2017/5/26.
 */

public class VideoCommentsPresenter implements IVideoCommentsContract.IVideoCommentsPresenter {

    private WeakReference<IVideoCommentsContract.IVideoCommentsView> mView;

    public VideoCommentsPresenter(IVideoCommentsContract.IVideoCommentsView view) {
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

    @Override
    public void loadData(int page, int pageSize, int aid) {
        final IVideoCommentsContract.IVideoCommentsView view = mView.get();
        if (view != null){
            view.showLoadingView(true);

        }
    }
}
