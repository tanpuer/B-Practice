package com.example.cw.b_practice.module.homeRecommend;

import com.example.cw.b_practice.base.IBasePresenter;
import com.example.cw.b_practice.base.IBaseView;
import com.example.cw.b_practice.entity.recommend.RecommendBean;

/**
 * Created by cw on 2017/5/16.
 */

public interface IHomeRecommendContract {

    interface IHomeRecommendPresenter extends IBasePresenter{

        void loadData();

        void refreshData();

    }

    interface IHomeRecommendView extends IBaseView<IHomeRecommendPresenter>{

        void showOrHideLoadingView(boolean show);

        void showOrHideErrorView(boolean show);

        void setIsRefreshing(boolean isRefreshing);

        void setRecommendBean(RecommendBean recommendBean);

    }
}
