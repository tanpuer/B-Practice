package com.example.cw.b_practice.module.videoDetail.videoIntroduction;

import android.os.Bundle;
import android.view.View;

import java.lang.ref.WeakReference;

/**
 * Created by cw on 2017/5/26.
 */

public class VideoIntroductionPresenter implements IVideoIntroductionContract.IVideoIntroductionPresenter {

    private WeakReference<IVideoIntroductionContract.IVideoIntroductionView> mView;

    public VideoIntroductionPresenter(IVideoIntroductionContract.IVideoIntroductionView view) {
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
