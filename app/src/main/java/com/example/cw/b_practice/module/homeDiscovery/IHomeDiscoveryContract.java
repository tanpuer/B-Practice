package com.example.cw.b_practice.module.homeDiscovery;

import com.example.cw.b_practice.base.IBasePresenter;
import com.example.cw.b_practice.base.IBaseView;
import com.example.cw.b_practice.entity.discover.HotSearchTag;

import java.util.List;

/**
 * Created by cw on 2017/5/22.
 */

public interface IHomeDiscoveryContract {

    interface IHomeDiscoveryView extends IBaseView<IHomeDiscoveryPresenter>{

        void showOrHideLoadingView(boolean show);

        void showOrHideErrorView(boolean show);

        void setListBean(List<HotSearchTag.ListBean> listBean);

    }

    interface IHomeDiscoveryPresenter extends IBasePresenter{

        void loadData();

    }
}
