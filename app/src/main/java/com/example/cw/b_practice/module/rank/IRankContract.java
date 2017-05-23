package com.example.cw.b_practice.module.rank;

import com.example.cw.b_practice.base.IBasePresenter;
import com.example.cw.b_practice.base.IBaseView;

/**
 * Created by cw on 2017/5/23.
 */

public interface IRankContract {

    interface IRankPresenter extends IBasePresenter{

        void getTypeInfo(int type);

    }

    interface IRankView extends IBaseView<IRankPresenter>{

        void setTypeInfo(String[] mTitles, String[] mTypes, String title);

    }

}
