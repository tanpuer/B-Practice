package com.example.cw.b_practice.module.homeRecommend;

import android.os.Bundle;
import android.view.View;

import com.example.cw.b_practice.entity.recommend.RecommendBannerInfo;
import com.example.cw.b_practice.entity.recommend.RecommendBean;
import com.example.cw.b_practice.entity.recommend.RecommendInfo;
import com.example.cw.b_practice.network.RetrofitHelper;

import java.lang.ref.WeakReference;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by cw on 2017/5/16.
 */

public class HomeRecommendPresenter implements IHomeRecommendContract.IHomeRecommendPresenter {

    private static final String TAG = "HomeRecommendPresenter";
    private WeakReference<IHomeRecommendContract.IHomeRecommendView> mView;

    public HomeRecommendPresenter(IHomeRecommendContract.IHomeRecommendView view) {
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
        final IHomeRecommendContract.IHomeRecommendView view = mView.get();
        if (view != null){
            view.showOrHideLoadingView(true);
            Observable<RecommendBannerInfo> recommendBannerObservable =
                    RetrofitHelper.getBiliAppAPI()
                            .getRecommendedBannerInfo()
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread());
            Observable<RecommendInfo> recommendInfoObservable =
                    RetrofitHelper.getBiliAppAPI()
                            .getRecommendedInfo()
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread());
            Observable<RecommendBean> recommendObservable = Observable.zip(recommendBannerObservable, recommendInfoObservable, new BiFunction<RecommendBannerInfo, RecommendInfo, RecommendBean>() {
                @Override
                public RecommendBean apply(@NonNull RecommendBannerInfo recommendBannerInfo, @NonNull RecommendInfo recommendInfo) throws Exception {
                    RecommendBean recommendBean =  new RecommendBean(recommendBannerInfo, recommendInfo);
                    return recommendBean;
                }
            });
            recommendObservable.subscribe(new Observer<RecommendBean>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {

                }

                @Override
                public void onNext(@NonNull RecommendBean recommendBean) {
                    view.setRecommendBean(recommendBean);
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    view.showOrHideErrorView(true);
                }

                @Override
                public void onComplete() {
                    view.showOrHideLoadingView(false);
                }
            });
        }
    }

    @Override
    public void refreshData() {

    }
}
