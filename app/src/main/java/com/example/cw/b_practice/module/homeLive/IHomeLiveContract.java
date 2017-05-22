package com.example.cw.b_practice.module.homeLive;

import com.example.cw.b_practice.base.IBasePresenter;
import com.example.cw.b_practice.base.IBaseView;
import com.example.cw.b_practice.entity.live.LiveAppIndexInfo;

/**
 * Created by cw on 2017/5/19.
 */

public interface IHomeLiveContract {

    interface IHomeLivePresenter extends IBasePresenter{

        void loadData();

    }

    interface IHomeLiveView extends IBaseView<IHomeLivePresenter>{

        void showOrHideLoadingView(boolean show);

        void showOrHideErrorView(boolean show);

        void setIsRefreshing(boolean isRefreshing);

        void setLiveBean(LiveAppIndexInfo liveAppIndexInfo);

    }
}
