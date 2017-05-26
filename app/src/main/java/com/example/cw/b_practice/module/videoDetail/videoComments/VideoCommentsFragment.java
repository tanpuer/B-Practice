package com.example.cw.b_practice.module.videoDetail.videoComments;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.cw.b_practice.R;
import com.example.cw.b_practice.base.newBase.BaseFragment;
import com.example.cw.b_practice.util.ConstantsUtil;

import butterknife.BindView;

/**
 * Created by cw on 2017/5/26.
 */

public class VideoCommentsFragment extends BaseFragment implements IVideoCommentsContract.IVideoCommentsView{


    @BindView(R.id.comment_recyclerview)
    RecyclerView mCommentRecyclerview;
    @BindView(R.id.comment_rootview)
    FrameLayout mCommentRootview;

    private IVideoCommentsContract.IVideoCommentsPresenter mPresenter;
    private int pageNum =1;
    private int pageSize = 20;
    private int aid;

    public static VideoCommentsFragment newInstance(int aid) {
        VideoCommentsFragment fragment = new VideoCommentsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ConstantsUtil.AID, aid);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getResLayoutId() {
        return R.layout.video_comment_fragment;
    }

    @Override
    protected void finishCreateView(View view, Bundle savedInstanceState) {
        if (mPresenter == null){
            mPresenter = new VideoCommentsPresenter(this);
        }
        aid = getArguments().getInt(ConstantsUtil.AID, -1);
        initRecyclerView();
        isPrepared = true;
        lazyLoad();
    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible){
            return;
        }
        isPrepared = false;
        mPresenter.loadData(pageNum, pageSize, aid);
    }

    @Override
    protected ViewGroup getRootView() {
        return mCommentRootview;
    }

    @Override
    public void setPresenter(IVideoCommentsContract.IVideoCommentsPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showLoadingView(boolean show) {
        showOrHideLoadingView(show);
    }

    @Override
    public void showErrorView(boolean show) {
        showOrHideLoadingView(show);
    }
}
