package com.example.cw.b_practice.module.homeDiscovery;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.cw.b_practice.R;
import com.example.cw.b_practice.base.RxBaseFragment;
import com.example.cw.b_practice.entity.discover.HotSearchTag;
import com.example.cw.b_practice.module.homeDiscovery.adapter.HotTagRecyclerViewAdapter;
import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cw on 2017/5/22.
 */

public class HomeDiscoveryFragment extends RxBaseFragment implements IHomeDiscoveryContract.IHomeDiscoveryView {

    private static final String TAG = "HomeDiscoveryFragment";
    private IHomeDiscoveryContract.IHomeDiscoveryPresenter mPresenter;
    private RecyclerView mRecyclerView;
    private List<HotSearchTag.ListBean> mList;
    private View loadingView, errorView;
    private ImageView loadingImage;
    private AnimationDrawable mAnimationDrawable;

    public static HomeDiscoveryFragment newInstance(){
        return new HomeDiscoveryFragment();
    }

    @Override
    public void setPresenter(IHomeDiscoveryContract.IHomeDiscoveryPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    protected int getResLayoutId() {
        return R.layout.fragment_discovery;
    }

    @Override
    protected void finishCreateView(View view, Bundle savedInstanceState) {
        if (mPresenter == null){
            setPresenter(new HomeDiscoveryPresenter(this));
        }
        mRecyclerView = (RecyclerView) view.findViewById(R.id.discovery_recyclerview);
        loadingView = view.findViewById(R.id.loading_view);
        loadingImage = (ImageView) loadingView.findViewById(R.id.loading_image);
        errorView = view.findViewById(R.id.error_view);
        mList = new ArrayList<>();
        isPrepared = true;
        lazyLoad();
    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible){
            return;
        }
        isPrepared = false;
        initRecyclerView();
        mPresenter.loadData();
    }

    @Override
    protected void initRecyclerView() {
        HotTagRecyclerViewAdapter adapter = new HotTagRecyclerViewAdapter(mList, getActivity());
        mRecyclerView.setAdapter(adapter);
        FlexboxLayoutManager manager = new FlexboxLayoutManager(FlexDirection.ROW, FlexWrap.WRAP);
        manager.setAlignItems(AlignItems.STRETCH);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setHasFixedSize(true);
    }


    @Override
    public void showOrHideLoadingView(boolean show) {
        loadingView.setVisibility(show?View.VISIBLE:View.INVISIBLE);
        if (show){
            if (mAnimationDrawable == null){
                mAnimationDrawable = (AnimationDrawable) loadingImage.getDrawable();
            }
            mAnimationDrawable.start();
        }
    }

    @Override
    public void showOrHideErrorView(boolean show) {
        errorView.setVisibility(show?View.VISIBLE:View.INVISIBLE);
    }

    @Override
    public void setListBean(List<HotSearchTag.ListBean> listBean) {
        clearList();
        mList.addAll(listBean);
        mRecyclerView.getAdapter().notifyDataSetChanged();
    }

    private void clearList(){
        if (mList.size() !=0){
            mList.clear();
        }
    }
}
