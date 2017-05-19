package com.example.cw.b_practice.module.main;

import android.view.View;

import com.example.cw.b_practice.base.IBasePresenter;
import com.example.cw.b_practice.base.IBaseView;

/**
 * Created by cw on 2017/5/15.
 */

public interface IMainContract {

    interface IMainPresenter extends IBasePresenter{

        void onClick(View v);

    }

    interface IMainView extends IBaseView<IMainPresenter>{

        void changeDayNightImage();

        void reCreateView();

    }
}
