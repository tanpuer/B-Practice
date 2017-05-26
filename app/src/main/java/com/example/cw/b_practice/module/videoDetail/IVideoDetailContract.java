package com.example.cw.b_practice.module.videoDetail;

import com.example.cw.b_practice.base.IBasePresenter;
import com.example.cw.b_practice.base.IBaseView;
import com.example.cw.b_practice.entity.video.VideoDetailsInfo;

/**
 * Created by cw on 2017/5/26.
 */

public interface IVideoDetailContract {

    interface iVideoDetailPresenter extends IBasePresenter{

        void loadData(int aid);

    }

    interface iVideoDetailView extends IBaseView<iVideoDetailPresenter>{

        void showErrorImage();

        void setFabClickable(boolean clickable);

        void setData(VideoDetailsInfo.DataBean bean);

    }
}
