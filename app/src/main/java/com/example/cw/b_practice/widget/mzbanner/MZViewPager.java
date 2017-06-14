package com.example.cw.b_practice.widget.mzbanner;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by cw on 2017/6/5.
 */

public class MZViewPager extends ViewPager implements ViewPager.PageTransformer{

    private static final float MIN_SCALE = 0.9f;

    public MZViewPager(Context context) {
        super(context);
    }

    public MZViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private void setClipChild(){
        setClipChildren(false);
    }

    @Override
    public void transformPage(View page, float position) {
        if (position <-1){
            page.setScaleY(MIN_SCALE);
        }else if (position > 1){
            page.setScaleY(MIN_SCALE);
        }else {
            page.setScaleY(Math.max(MIN_SCALE, 1-Math.abs(position)));
        }
    }
}
