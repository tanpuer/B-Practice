package com.example.cw.b_practice.module.homeLive;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.cw.b_practice.R;
import com.example.cw.b_practice.base.RxBaseFragment;
import com.example.cw.b_practice.entity.live.LiveAppIndexInfo;
import com.example.cw.b_practice.module.homeLive.adapter.HomeLiveAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cw on 2017/5/19.
 */

public class HomeLiveFragment extends RxBaseFragment implements IHomeLiveContract.IHomeLiveView{

    private IHomeLiveContract.IHomeLivePresenter mPresenter;
    private View loadingView;
    private View errorView;
    private ImageView loadingImage;
    private AnimationDrawable mAnimationDrawable;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private boolean isRefreshing =false;
    private List<LiveAppIndexInfo.DataBean.BannerBean> mBannerBeen;
    private List<LiveAppIndexInfo.DataBean.EntranceIconsBean> mEntranceIconBeen;
    private List<LiveAppIndexInfo.DataBean.PartitionsBean> mPartitionsBeen;

    public static HomeLiveFragment newInstance(){
        return new HomeLiveFragment();
    }

    @Override
    public void setPresenter(IHomeLiveContract.IHomeLivePresenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    protected int getResLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void finishCreateView(View view, Bundle savedInstanceState) {
        if (mPresenter == null){
            setPresenter(new HomeLivePresenter(this));
        }
        loadingView = view.findViewById(R.id.loading_view);
        errorView = view.findViewById(R.id.error_view);
        loadingImage = (ImageView) loadingView.findViewById(R.id.loading_image);
        mBannerBeen = new ArrayList<>();
        mEntranceIconBeen = new ArrayList<>();
        mPartitionsBeen = new ArrayList<>();
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
        mPresenter.loadData();
    }

    @Override
    public void showOrHideLoadingView(boolean show) {
        loadingView.setVisibility(show? View.VISIBLE: View.INVISIBLE);
        if (show){
            if (mAnimationDrawable == null){
                mAnimationDrawable = (AnimationDrawable) loadingImage.getDrawable();
            }
            mAnimationDrawable.start();
        }
    }

    @Override
    protected void initRecyclerView() {
        mRecyclerView = (RecyclerView) getParentView().findViewById(R.id.home_recycler_view);
        HomeLiveAdapter mAdapter = new HomeLiveAdapter(mBannerBeen, mEntranceIconBeen, mPartitionsBeen, getActivity());
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 10);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return mAdapter.getSpanSize(position);
            }
        });
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initRefreshLayout() {
        mSwipeRefreshLayout = (SwipeRefreshLayout) getParentView().findViewById(R.id.home_refresh_layout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefreshLayout.setOnRefreshListener(()-> mPresenter.loadData());
    }

    @Override
    public void showOrHideErrorView(boolean show) {
        errorView.setVisibility(show?View.VISIBLE:View.INVISIBLE);
    }

    @Override
    public void setIsRefreshing(boolean isRefreshing) {
        if (mSwipeRefreshLayout != null){
            this.isRefreshing = isRefreshing;
            mSwipeRefreshLayout.setRefreshing(isRefreshing);
        }
    }

    @Override
    public void setLiveBean(LiveAppIndexInfo liveAppIndexInfo) {
        clearData();
        mBannerBeen.addAll(liveAppIndexInfo.getData().getBanner());
        mPartitionsBeen.addAll(liveAppIndexInfo.getData().getPartitions());
        mEntranceIconBeen.addAll(liveAppIndexInfo.getData().getEntranceIcons());
        mRecyclerView.getAdapter().notifyDataSetChanged();
    }

    private void clearData() {
        if (mBannerBeen.size() !=0 && mPartitionsBeen.size()!=0 && mEntranceIconBeen.size() !=0){
            mBannerBeen.clear();
            mPartitionsBeen.clear();
            mEntranceIconBeen.clear();
        }
    }
}
