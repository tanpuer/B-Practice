package com.example.cw.b_practice.widget.mzbanner.transformer;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by cw on 2017/6/14.
 */

public class CustomerTransformer implements ViewPager.PageTransformer {

    private static final float MIN_FLOAT = 0.9f;

    @Override
    public void transformPage(View page, float position) {
        if (position<-1){
            page.setScaleY(MIN_FLOAT);
        }else if (position>1){
            page.setScaleY(MIN_FLOAT);
        }else {
            page.setScaleY(Math.max(MIN_FLOAT, 1 - Math.abs(position)));
        }
    }
}
