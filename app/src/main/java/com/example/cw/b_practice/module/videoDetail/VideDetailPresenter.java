package com.example.cw.b_practice.module.videoDetail;

import android.os.Bundle;
import android.view.View;

import com.example.cw.b_practice.entity.video.VideoDetailsInfo;
import com.example.cw.b_practice.network.RetrofitHelper;

import java.lang.ref.WeakReference;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by cw on 2017/5/26.
 */

public class VideDetailPresenter implements IVideoDetailContract.iVideoDetailPresenter{

    private WeakReference<IVideoDetailContract.iVideoDetailView> mView;

    public VideDetailPresenter(IVideoDetailContract.iVideoDetailView view) {
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
    public void loadData(int aid) {
        final IVideoDetailContract.iVideoDetailView view = mView.get();
        if (view != null){
            view.setFabClickable(false);
            RetrofitHelper.getBiliAppAPI()
                    .getVideoDetails(aid)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<VideoDetailsInfo>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onNext(@NonNull VideoDetailsInfo videoDetailsInfo) {
                            view.setData(videoDetailsInfo.getData());
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            view.showErrorImage();
                        }

                        @Override
                        public void onComplete() {
                            view.setFabClickable(true);
                        }
                    });
        }
    }
}
