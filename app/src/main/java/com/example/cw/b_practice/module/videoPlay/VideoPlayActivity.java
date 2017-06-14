package com.example.cw.b_practice.module.videoPlay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.cw.b_practice.R;
import com.example.cw.b_practice.base.RxBaseActivity;
import com.example.cw.b_practice.module.videoPlay.component.StandardVideoPlayer;
import com.example.cw.b_practice.util.ConstantsUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by cw on 2017/6/5.
 */

public class VideoPlayActivity extends RxBaseActivity implements IVidePlayContract.IVideoPlayView {

    @BindView(R.id.video_player)
    StandardVideoPlayer mVideoPlayer;

    private IVidePlayContract.IVideoPlayPresenter mPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_video_play;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        if (mPresenter == null) {
            setPresenter(new VideoPlayPresenter(this));
        }
        mVideoPlayer.setUp("http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4", false, "1");
    }

    @Override
    protected void initToolBar() {

    }

    @Override
    public void setPresenter(IVidePlayContract.IVideoPlayPresenter presenter) {
        mPresenter = presenter;
    }

    public static void launch(Activity activity, int cid, String title) {

        Intent mIntent = new Intent(activity, VideoPlayActivity.class);
        mIntent.putExtra(ConstantsUtil.EXTRA_CID, cid);
        mIntent.putExtra(ConstantsUtil.EXTRA_TITLE, title);
        activity.startActivity(mIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
