package com.example.cw.b_practice.module.videoDetail.videoComments;

import android.os.Bundle;
import android.view.View;

import com.example.cw.b_practice.entity.video.VideoCommentInfo;
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
            int ver = 3;
            view.showLoadingView(true);
            RetrofitHelper.getBiliAPI()
                    .getVideoComment(aid, page, pageSize, ver)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<VideoCommentInfo>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onNext(@NonNull VideoCommentInfo videoCommentInfo) {
                            view.setBeans(videoCommentInfo.list, videoCommentInfo.hotList);
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            view.showLoadingView(false);
                            view.showErrorView(true);
                        }

                        @Override
                        public void onComplete() {
                            view.showLoadingView(false);
                        }
                    });
        }
    }
}
