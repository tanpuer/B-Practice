package com.example.cw.b_practice.module.videoDetail.listener;

import android.support.design.widget.AppBarLayout;

/**
 * Created by cw on 2017/5/26.
 */

public abstract class AppBarLayoutStateChangeListener implements AppBarLayout.OnOffsetChangedListener{

    protected enum State {
        EXPANDED,
        COLLAPSED,
        IDLE,
    }

    private State currentState = State.IDLE;

    protected abstract void onStateChange(AppBarLayout appBarLayout, State state, int verticalOffset);

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        if (verticalOffset == 0){
            if (currentState != State.EXPANDED){
                onStateChange(appBarLayout, State.EXPANDED, verticalOffset);
            }
            currentState = State.EXPANDED;
        }
        if (verticalOffset >= appBarLayout.getTotalScrollRange()){
            if (currentState != State.COLLAPSED){
                onStateChange(appBarLayout, State.COLLAPSED, verticalOffset);
            }
            currentState = State.COLLAPSED;
        }else {
            if (currentState != State.IDLE){
                onStateChange(appBarLayout, State.IDLE, verticalOffset);
            }
            currentState = State.IDLE;
        }
    }
}
