package com.example.cw.b_practice.module.rank.fragment;

import com.example.cw.b_practice.base.IBasePresenter;
import com.example.cw.b_practice.base.home.IHomeBaseView;
import com.example.cw.b_practice.entity.discover.AllareasRankInfo;

import java.util.List;

/**
 * Created by cw on 2017/5/23.
 */

public interface IRankFragmentContract {

    interface IRankFragmentPresenter extends IBasePresenter{

        void loadData(String type);

    }

    interface IRankFragmentView extends IHomeBaseView<IRankFragmentPresenter> {

        void setList(List<AllareasRankInfo.RankBean.ListBean> list);
    }

}
