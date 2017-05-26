package com.example.cw.b_practice.module.videoDetail.videoIntroduction;

import android.os.Bundle;
import android.view.View;

import com.example.cw.b_practice.R;
import com.example.cw.b_practice.base.RxBaseFragment;
import com.example.cw.b_practice.util.ConstantsUtil;

/**
 * Created by cw on 2017/5/26.
 */

public class VideoIntroductionFragment extends RxBaseFragment implements IVideoIntroductionContract.IVideoIntroductionView{

    private IVideoIntroductionContract.IVideoIntroductionPresenter mPresenter;

    public static VideoIntroductionFragment newInstance(int aid){
        VideoIntroductionFragment fragment = new VideoIntroductionFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ConstantsUtil.AID, aid);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void setPresenter(IVideoIntroductionContract.IVideoIntroductionPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    protected int getResLayoutId() {
        return R.layout.video_intro_fragment;
    }

    @Override
    protected void finishCreateView(View view, Bundle savedInstanceState) {
        if (mPresenter == null){
            mPresenter = new VideoIntroductionPresenter(this);
        }
    }
}
