package com.example.cw.b_practice.module.homeRegion;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.cw.b_practice.R;
import com.example.cw.b_practice.base.RxBaseFragment;
import com.example.cw.b_practice.entity.region.RegionTypesInfo;
import com.example.cw.b_practice.module.homeRegion.adapter.HomeRegionRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cw on 2017/5/19.
 */

public class HomeRegionFragment extends RxBaseFragment implements IHomeRegionContract.IHomeRegionView{

    private IHomeRegionContract.IHomeRegionPresenter mPresenter;
    private RecyclerView mRecyclerView;
    private List<RegionTypesInfo.DataBean> mList = new ArrayList<>();
    HomeRegionRecyclerViewAdapter adapter;

    public static HomeRegionFragment newInstance(){
        return new HomeRegionFragment();
    }

    @Override
    protected int getResLayoutId() {
        return R.layout.fragment_home_region;
    }

    @Override
    protected void finishCreateView(View view, Bundle savedInstanceState) {
        if (mPresenter == null){
            setPresenter(new HomeRegionPresenter(this));
        }
        mRecyclerView = (RecyclerView) view.findViewById(R.id.region_recyclerview);
        isPrepared = true;
        lazyLoad();
    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible){
            return;
        }
        initRecyclerView();
        isPrepared = false;
        mPresenter.loadData();
    }

    @Override
    public void setPresenter(IHomeRegionContract.IHomeRegionPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void setData(List<RegionTypesInfo.DataBean> list) {
        mList.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void initRecyclerView() {
        adapter = new HomeRegionRecyclerViewAdapter(mList, getActivity());
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
        mRecyclerView.setAdapter(adapter);
    }
}
