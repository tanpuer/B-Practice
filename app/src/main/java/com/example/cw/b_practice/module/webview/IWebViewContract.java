package com.example.cw.b_practice.module.webview;

import com.example.cw.b_practice.base.IBasePresenter;
import com.example.cw.b_practice.base.IBaseView;

/**
 * Created by cw on 2017/5/25.
 */

public interface IWebViewContract {

    interface IWebViewView extends IBaseView<IWebViewPresenter>{

        void showOrHideLoadingView(boolean show);

    }

    interface IWebViewPresenter extends IBasePresenter{

//        void share();

        void copyToBoard(String url);

    }
}
