package com.example.cw.b_practice.module.rank.fragment;

import android.os.Bundle;
import android.view.View;

import com.example.cw.b_practice.entity.discover.AllareasRankInfo;
import com.example.cw.b_practice.network.RetrofitHelper;

import java.lang.ref.WeakReference;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by cw on 2017/5/23.
 */

public class RankFragmentPresenter implements IRankFragmentContract.IRankFragmentPresenter {

    private WeakReference<IRankFragmentContract.IRankFragmentView> mView;

    public RankFragmentPresenter(IRankFragmentContract.IRankFragmentView view) {
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
    public void loadData(String type) {
        final IRankFragmentContract.IRankFragmentView view = mView.get();
        if (view != null){
            view.showOrHideLoadingView(true);
            RetrofitHelper.getRankAPI()
                    .getAllareasRanks(type)
                    .map(allareasRankInfo -> allareasRankInfo.getRank().getList())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<List<AllareasRankInfo.RankBean.ListBean>>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onNext(@NonNull List<AllareasRankInfo.RankBean.ListBean> listBeen) {
                            view.setList(listBeen);
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
