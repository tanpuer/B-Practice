package com.example.cw.b_practice.module.homeDiscovery;

import android.os.Bundle;
import android.view.View;

import com.example.cw.b_practice.entity.discover.HotSearchTag;
import com.example.cw.b_practice.network.RetrofitHelper;

import java.lang.ref.WeakReference;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by cw on 2017/5/22.
 */

public class HomeDiscoveryPresenter implements IHomeDiscoveryContract.IHomeDiscoveryPresenter {

    private WeakReference<IHomeDiscoveryContract.IHomeDiscoveryView> mView;

    public HomeDiscoveryPresenter(IHomeDiscoveryContract.IHomeDiscoveryView view) {
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
        final IHomeDiscoveryContract.IHomeDiscoveryView view = mView.get();
        if (view != null) {
            view.showOrHideLoadingView(true);
            RetrofitHelper.getSearchAPI()
                    .getHotSearchTags()
                    .map(HotSearchTag::getList)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<List<HotSearchTag.ListBean>>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onNext(@NonNull List<HotSearchTag.ListBean> listBeen) {
                            view.setListBean(listBeen);
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            view.showOrHideLoadingView(false);
                            view.showOrHideErrorView(true);
                        }

                        @Override
                        public void onComplete() {
                            view.showOrHideLoadingView(false);
                        }
                    });
        }
    }
}
