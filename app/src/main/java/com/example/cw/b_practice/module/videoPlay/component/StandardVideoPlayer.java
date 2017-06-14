package com.example.cw.b_practice.module.videoPlay.component;

import android.content.Context;
import android.util.AttributeSet;

import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

/**
 * Created by cw on 2017/6/13.
 */

public class StandardVideoPlayer extends StandardGSYVideoPlayer {

    public StandardVideoPlayer(Context context, Boolean fullFlag) {
        super(context, fullFlag);
    }

    public StandardVideoPlayer(Context context) {
        super(context);
    }

    public StandardVideoPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
}
