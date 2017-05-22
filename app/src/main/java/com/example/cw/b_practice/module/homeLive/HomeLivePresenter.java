package com.example.cw.b_practice.module.homeLive;

import android.os.Bundle;
import android.view.View;

import com.example.cw.b_practice.entity.live.LiveAppIndexInfo;
import com.example.cw.b_practice.network.RetrofitHelper;

import java.lang.ref.WeakReference;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by cw on 2017/5/19.
 */

public class HomeLivePresenter implements IHomeLiveContract.IHomeLivePresenter {

    private WeakReference<IHomeLiveContract.IHomeLiveView> mView;

    public HomeLivePresenter(IHomeLiveContract.IHomeLiveView view) {
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
    public void loadData() {
        final IHomeLiveContract.IHomeLiveView view = mView.get();
        if (view != null){
            view.showOrHideLoadingView(true);
            RetrofitHelper.getLiveAPI()
                    .getLiveAppIndex()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<LiveAppIndexInfo>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onNext(@NonNull LiveAppIndexInfo liveAppIndexInfo) {
                            view.setLiveBean(liveAppIndexInfo);
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            view.setIsRefreshing(false);
                            view.showOrHideLoadingView(false);
                            view.showOrHideErrorView(true);
                        }

                        @Override
                        public void onComplete() {
                            view.setIsRefreshing(false);
                            view.showOrHideLoadingView(false);
                        }
                    });
        }
    }
}
