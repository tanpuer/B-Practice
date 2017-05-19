package com.example.cw.b_practice.module.homeRecommend;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.cw.b_practice.R;
import com.example.cw.b_practice.base.RxBaseFragment;
import com.example.cw.b_practice.entity.recommend.RecommendBannerInfo;
import com.example.cw.b_practice.entity.recommend.RecommendBean;
import com.example.cw.b_practice.entity.recommend.RecommendInfo;
import com.example.cw.b_practice.module.homeRecommend.adapter.RecommendRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cw on 2017/5/16.
 */

public class HomeRecommendFragment extends RxBaseFragment implements IHomeRecommendContract.IHomeRecommendView{

    private static final String TAG = "HomeRecommendFragment";
    private IHomeRecommendContract.IHomeRecommendPresenter mPresenter;

    private View loadingView;
    private View errorView;
    private ImageView loadingImage;
    private AnimationDrawable mAnimationDrawable;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private boolean isRefreshing =false;
    private RecommendBean mRecommendBean;
    private RecommendRecyclerViewAdapter mAdapter;
    private List<RecommendBannerInfo.DataBean> bannerList;
    private List<RecommendInfo.ResultBean> infoList;
    private List<RecommendInfo.ResultBean> tempinfoList;

    public static HomeRecommendFragment newInstance(){
        return new HomeRecommendFragment();
    }

    @Override
    protected int getResLayoutId() {
        return R.layout.fragment_home_recommedn;
    }

    @Override
    protected void finishCreateView(View view, Bundle savedInstanceState) {
        if (mPresenter == null){
            setPresenter(new HomeRecommendPresenter(this));
        }
        loadingView = view.findViewById(R.id.loading_view);
        errorView = view.findViewById(R.id.error_view);
        loadingImage = (ImageView) loadingView.findViewById(R.id.loading_image);
        bannerList = new ArrayList<>();
        infoList = new ArrayList<>();
        isPrepared = true;
        lazyLoad();
    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible){
            return;
        }
        initRefreshLayout();
        initRecyclerView();
        isPrepared = false;//保证load一遍
        mPresenter.loadData();
    }

    @Override
    public void setPresenter(IHomeRecommendContract.IHomeRecommendPresenter presenter) {
        mPresenter = presenter;
    }

    /**
     * @param show
     *ImageView 设置src，background的区别
     */
    @Override
    public void showOrHideLoadingView(boolean show) {
        loadingView.setVisibility(show? View.VISIBLE: View.GONE);
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
        errorView.setVisibility(show? View.VISIBLE: View.GONE);
    }

    @Override
    public void setIsRefreshing(boolean isRefreshing) {
        if (mSwipeRefreshLayout != null){
            this.isRefreshing = isRefreshing;
            mSwipeRefreshLayout.setRefreshing(isRefreshing);
        }
    }

    @Override
    protected void initRefreshLayout() {
        mSwipeRefreshLayout = (SwipeRefreshLayout) getParentView().findViewById(R.id.recommend_refresh_layout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefreshLayout.setOnRefreshListener(()-> mPresenter.loadData());
    }

    @Override
    protected void initRecyclerView() {
        mRecyclerView = (RecyclerView) getParentView().findViewById(R.id.recommend_recycler_view);
        mAdapter = new RecommendRecyclerViewAdapter(infoList, bannerList, getActivity());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
    }

    /**
     * @param recommendBean
     *
     * For An ArrayAdapter, notifyDataSetChanged only works if you add, remove, insert, clear on the Adapter.
     * When An ArrayAdapter is constructed, it holds the reference to the List that was passed in. If you
     * were to pass in a List that was a member of an activity, and change the activity member later, the
     * ArrayAdapter is still holding the original List. The Adapter does not know you changed the List in
     * the Activity.
     */
    @Override
    public void setRecommendBean(RecommendBean recommendBean) {
        this.mRecommendBean = recommendBean;
        tempinfoList = recommendBean.getRecommendInfo().getResult();
        // TODO: 2017/5/19  先过滤 避免crash
        for (int i=0;i<tempinfoList.size();i++){
            if (tempinfoList.get(i).getBody().size()!=4){
                tempinfoList.remove(i);
            }
        }
        clearList();
        infoList.addAll(tempinfoList);
//        infoList.addAll(recommendBean.getRecommendInfo().getResult());
        bannerList.addAll(recommendBean.getRecommendBannerInfo().getData());
        //如果是add进去，而不是赋值的话， notifyDataSetChanged
//        mAdapter = new RecommendRecyclerViewAdapter(infoList, bannerList, getActivity());
//        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    private void clearList(){
        if (infoList.size() !=0 && bannerList.size() !=0){
            infoList.clear();
            bannerList.clear();
        }
    }
}
