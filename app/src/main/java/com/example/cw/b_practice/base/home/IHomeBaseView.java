package com.example.cw.b_practice.base.home;

import com.example.cw.b_practice.base.IBaseView;

/**
 * Created by cw on 2017/5/23.
 */

public interface IHomeBaseView<T> extends IBaseView<T>{

    void showOrHideLoadingView(boolean show);

    void showOrHideErrorView(boolean show);

}
