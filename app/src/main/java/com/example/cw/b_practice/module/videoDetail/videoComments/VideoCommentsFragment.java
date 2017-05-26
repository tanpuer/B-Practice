package com.example.cw.b_practice.module.videoDetail.videoComments;

import android.os.Bundle;
import android.view.View;

import com.example.cw.b_practice.R;
import com.example.cw.b_practice.base.RxBaseFragment;
import com.example.cw.b_practice.util.ConstantsUtil;

/**
 * Created by cw on 2017/5/26.
 */

public class VideoCommentsFragment extends RxBaseFragment {

    public static VideoCommentsFragment newInstance(int aid){
        VideoCommentsFragment fragment = new VideoCommentsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ConstantsUtil.AID, aid);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getResLayoutId() {
        return R.layout.video_comment_fragment;
    }

    @Override
    protected void finishCreateView(View view, Bundle savedInstanceState) {

    }
}
