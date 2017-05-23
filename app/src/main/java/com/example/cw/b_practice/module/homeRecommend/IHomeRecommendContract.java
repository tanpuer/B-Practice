package com.example.cw.b_practice.module.homeRecommend;

import com.example.cw.b_practice.base.home.IHomeBasePresenter;
import com.example.cw.b_practice.base.home.IHomeBaseView;
import com.example.cw.b_practice.entity.recommend.RecommendBean;

/**
 * Created by cw on 2017/5/16.
 */

public interface IHomeRecommendContract {

    interface IHomeRecommendPresenter extends IHomeBasePresenter{

        void loadData();

        void refreshData();

    }

    interface IHomeRecommendView extends IHomeBaseView<IHomeRecommendPresenter> {

        void setIsRefreshing(boolean isRefreshing);

        void setRecommendBean(RecommendBean recommendBean);

    }
}
