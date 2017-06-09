package com.example.cw.b_practice.module.videoPlay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.cw.b_practice.base.RxBaseActivity;
import com.example.cw.b_practice.util.ConstantsUtil;

/**
 * Created by cw on 2017/6/5.
 */

public class VideoPlayActivity extends RxBaseActivity implements IVidePlayContract.IVideoPlayView{

    private IVidePlayContract.IVideoPlayPresenter mPresenter;

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        if (mPresenter == null){
            setPresenter(new VideoPlayPresenter(this));
        }
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
}
