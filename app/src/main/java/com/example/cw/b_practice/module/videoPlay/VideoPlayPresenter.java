package com.example.cw.b_practice.module.videoPlay;

import android.os.Bundle;
import android.view.View;

import java.lang.ref.WeakReference;

/**
 * Created by cw on 2017/6/5.
 */

public class VideoPlayPresenter implements IVidePlayContract.IVideoPlayPresenter{

    private WeakReference<IVidePlayContract.IVideoPlayView> mView;

    public VideoPlayPresenter(IVidePlayContract.IVideoPlayView view) {
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
