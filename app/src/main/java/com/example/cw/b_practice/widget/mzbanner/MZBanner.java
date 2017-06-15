package com.example.cw.b_practice.widget.mzbanner;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.cw.b_practice.R;

/**
 * Created by cw on 2017/6/15.
 */

public class MZBanner extends RelativeLayout{

    private static final float DEFAULT_SWITCH_TIME = 2000;
    private boolean autoSwitch;
    private float switchTime;
    private ViewPager mViewPager;

    public MZBanner(Context context) {
        this(context, null);
    }

    public MZBanner(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MZBanner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (attrs != null){
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MZBanner);
            autoSwitch = a.getBoolean(R.styleable.MZBanner_auto_switch, true);
            switchTime = a.getFloat(R.styleable.MZBanner_switch_time, DEFAULT_SWITCH_TIME);
            a.recycle();
        }
        initViews(context);
    }

    private void initViews(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.widget_mz_banner, this, false);
        mViewPager = (ViewPager) view.findViewById(R.id.view_pager);
        mViewPager.setClipChildren(false);
    }
}
