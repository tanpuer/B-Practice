package com.example.cw.b_practice.module.rank.fragment;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.cw.b_practice.R;
import com.example.cw.b_practice.base.RxBaseFragment;
import com.example.cw.b_practice.entity.discover.AllareasRankInfo;
import com.example.cw.b_practice.module.rank.fragment.adapter.RankRecyclerViewAdapter;
import com.example.cw.b_practice.util.ConstantsUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cw on 2017/5/23.
 */

public class RankFragment extends RxBaseFragment implements IRankFragmentContract.IRankFragmentView {

    private String type;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private List<AllareasRankInfo.RankBean.ListBean> mList;
    private View loadingView;
    private View errorView;
    private AnimationDrawable mAnimationDrawable;
    private ImageView loadingImage;

    public static RankFragment newInstance(String type){
        RankFragment fragment = new RankFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ConstantsUtil.EXTRA_KEY, type);
        fragment.setArguments(bundle);
        return fragment;
    }

    private IRankFragmentContract.IRankFragmentPresenter mPresenter;

    @Override
    protected int getResLayoutId() {
        return R.layout.fragment_rank;
    }

    @Override
    protected void finishCreateView(View view, Bundle savedInstanceState) {
        if (mPresenter == null){
            setPresenter(new RankFragmentPresenter(this));
        }
        type = getArguments().getString(ConstantsUtil.EXTRA_KEY);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.rank_fragment_swipe);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rank_fragment_recyclerview);
        loadingView = view.findViewById(R.id.loading_view);
        errorView = view.findViewById(R.id.error_view);
        loadingImage = (ImageView) loadingView.findViewById(R.id.loading_image);
        mList = new ArrayList<>();
        isPrepared = true;
        lazyLoad();
    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible){
            return;
        }
        initRecyclerView();
        initRefreshLayout();
        isPrepared = false;
        mPresenter.loadData(type);
    }

    @Override
    protected void initRecyclerView() {
        RankRecyclerViewAdapter adapter = new RankRecyclerViewAdapter(mList, getActivity());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void initRefreshLayout() {
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefreshLayout.setOnRefreshListener(()->mPresenter.loadData(type));
    }

    @Override
    public void setPresenter(IRankFragmentContract.IRankFragmentPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void setList(List<AllareasRankInfo.RankBean.ListBean> list) {
        clearList();
        mList.addAll(list.subList(0,20));
        mRecyclerView.getAdapter().notifyDataSetChanged();
    }

    private void clearList() {
        if (mList.size() !=0){
            mList.clear();
        }
    }

    @Override
    public void showOrHideLoadingView(boolean show) {
        loadingView.setVisibility(show?View.VISIBLE:View.GONE);
        if (show){
            if (mAnimationDrawable == null){
                mAnimationDrawable = (AnimationDrawable) loadingImage.getDrawable();
            }
            mAnimationDrawable.start();
        }else {
            mAnimationDrawable.stop();
            mAnimationDrawable = null;
        }
    }

    @Override
    public void showOrHideErrorView(boolean show) {
        errorView.setVisibility(show?View.VISIBLE:View.GONE);
    }
}
