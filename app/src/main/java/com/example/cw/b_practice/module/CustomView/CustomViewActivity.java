package com.example.cw.b_practice.module.CustomView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import com.example.cw.b_practice.R;
import com.example.cw.b_practice.base.RxBaseActivity;

/**
 * Created by cw on 2017/8/9.
 */

public class CustomViewActivity extends RxBaseActivity {

    private SeekBar mSeekBar;
    private ImageView smileFace;
    private LinearLayout mLinearLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_custom_view;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        mSeekBar = (SeekBar) findViewById(R.id.seekBar);
        smileFace = (ImageView) findViewById(R.id.smileFace);
        mLinearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) smileFace.getLayoutParams();
                params.bottomMargin = progress*3;
                smileFace.setLayoutParams(params);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    protected void initToolBar() {

    }
}
