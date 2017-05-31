package com.example.cw.b_practice.module.videoDetail.videoComments;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.cw.b_practice.R;
import com.example.cw.b_practice.base.RxBaseFragment;
import com.example.cw.b_practice.entity.video.VideoCommentInfo;
import com.example.cw.b_practice.module.videoDetail.videoComments.adapter.CommentsRecyclerAdapter;
import com.example.cw.b_practice.util.ConstantsUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by cw on 2017/5/26.
 */

public class VideoCommentsFragment extends RxBaseFragment implements IVideoCommentsContract.IVideoCommentsView {


    @BindView(R.id.comment_recyclerview)
    RecyclerView mCommentRecyclerview;
    @BindView(R.id.comment_rootview)
    FrameLayout mCommentRootview;
    Unbinder unbinder;

    private IVideoCommentsContract.IVideoCommentsPresenter mPresenter;
    private int pageNum = 1;
    private int pageSize = 20;
    private int aid;
    private View loadingView, errorView;
    private ImageView mImageView;
    private AnimationDrawable mAnimationDrawable;
    private List<VideoCommentInfo.List> mLists;
    private List<VideoCommentInfo.HotList> mHotLists;

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
        if (mPresenter == null) {
            mPresenter = new VideoCommentsPresenter(this);
        }
        mLists = new ArrayList<>();
        mHotLists = new ArrayList<>();
        loadingView = view.findViewById(R.id.loading_view);
        errorView = view.findViewById(R.id.error_view);
        mImageView = (ImageView) loadingView.findViewById(R.id.loading_image);
        aid = getArguments().getInt(ConstantsUtil.AID, -1);
        initRecyclerView();
        isPrepared = true;
        lazyLoad();
    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible) {
            return;
        }
        initRecyclerView();
        isPrepared = false;
        mPresenter.loadData(pageNum, pageSize, aid);
    }

    @Override
    public void setPresenter(IVideoCommentsContract.IVideoCommentsPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    protected void initRecyclerView() {
        mCommentRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        CommentsRecyclerAdapter adapter = new CommentsRecyclerAdapter(mLists, mHotLists, getActivity());
        mCommentRecyclerview.setAdapter(adapter);
    }

    @Override
    public void showLoadingView(boolean show) {
        loadingView.setVisibility(show?View.VISIBLE:View.INVISIBLE);
        if (show){
            if (mAnimationDrawable == null){
                mAnimationDrawable = (AnimationDrawable) mImageView.getDrawable();
            }
            mAnimationDrawable.start();
        }
    }

    @Override
    public void showErrorView(boolean show) {
        errorView.setVisibility(show?View.VISIBLE:View.INVISIBLE);
    }

    @Override
    public void setBeans(List<VideoCommentInfo.List> list, List<VideoCommentInfo.HotList> hotList) {
        mLists.addAll(list);
        mHotLists.addAll(hotList);
        mCommentRecyclerview.getAdapter().notifyDataSetChanged();
    }

}
