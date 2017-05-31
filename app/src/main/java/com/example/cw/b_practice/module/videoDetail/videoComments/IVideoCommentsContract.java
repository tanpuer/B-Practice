package com.example.cw.b_practice.module.videoDetail.videoComments;

import com.example.cw.b_practice.base.IBasePresenter;
import com.example.cw.b_practice.base.IBaseView;
import com.example.cw.b_practice.entity.video.VideoCommentInfo;

import java.util.List;

/**
 * Created by cw on 2017/5/26.
 */

public interface IVideoCommentsContract {

    interface IVideoCommentsView extends IBaseView<IVideoCommentsPresenter>{

        void showLoadingView(boolean show);

        void showErrorView(boolean show);

        void setBeans(List<VideoCommentInfo.List> list, List<VideoCommentInfo.HotList> hotList);

    }

    interface IVideoCommentsPresenter extends IBasePresenter{

        void loadData(int page, int pageSize, int aid);
    }
}
