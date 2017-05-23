package com.example.cw.b_practice.module.homeLive;

import com.example.cw.b_practice.base.home.IHomeBasePresenter;
import com.example.cw.b_practice.base.home.IHomeBaseView;
import com.example.cw.b_practice.entity.live.LiveAppIndexInfo;

/**
 * Created by cw on 2017/5/19.
 */

public interface IHomeLiveContract {

    interface IHomeLivePresenter extends IHomeBasePresenter{

        void loadData();

    }

    interface IHomeLiveView extends IHomeBaseView<IHomeLivePresenter> {

        void setIsRefreshing(boolean isRefreshing);

        void setLiveBean(LiveAppIndexInfo liveAppIndexInfo);

    }
}
