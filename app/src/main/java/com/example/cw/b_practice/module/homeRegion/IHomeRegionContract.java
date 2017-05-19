package com.example.cw.b_practice.module.homeRegion;

import com.example.cw.b_practice.base.IBasePresenter;
import com.example.cw.b_practice.base.IBaseView;
import com.example.cw.b_practice.entity.region.RegionTypesInfo;

import java.util.List;

/**
 * Created by cw on 2017/5/19.
 */

public interface IHomeRegionContract {

    interface IHomeRegionView extends IBaseView<IHomeRegionPresenter>{

        void setData(List<RegionTypesInfo.DataBean> list);

    }

    interface IHomeRegionPresenter extends IBasePresenter{

        void loadData();

    }
}
