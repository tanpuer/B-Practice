package com.example.cw.b_practice.module.homeDiscovery;

import com.example.cw.b_practice.base.home.IHomeBasePresenter;
import com.example.cw.b_practice.base.home.IHomeBaseView;
import com.example.cw.b_practice.entity.discover.HotSearchTag;

import java.util.List;

/**
 * Created by cw on 2017/5/22.
 */

public interface IHomeDiscoveryContract {

    interface IHomeDiscoveryView extends IHomeBaseView<IHomeDiscoveryPresenter> {

        void setListBean(List<HotSearchTag.ListBean> listBean);

    }

    interface IHomeDiscoveryPresenter extends IHomeBasePresenter{

        void loadData();

    }
}
