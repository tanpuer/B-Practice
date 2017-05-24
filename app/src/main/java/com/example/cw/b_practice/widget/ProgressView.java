package com.example.cw.b_practice.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.cw.b_practice.R;
import com.example.cw.b_practice.util.ResourceUtil;

/**
 * Created by cw on 2017/5/24.
 */

public class ProgressView extends View{

    private int height;
    private int usedColor;
    private int unUsedColor;
    private static final int Default_Height = 50;
    private static final int Used_Color = R.color.colorPrimary;
    private static final int Unused_Color = R.color.gray_dark;
    private Paint usedPaint, unUsedPaint;
    private RectF usedRectF, unUsedRectF;

    public ProgressView(Context context) {
        this(context, null);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (attrs != null){
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ProgressView);
            height = a.getDimensionPixelSize(R.styleable.ProgressView_progress_height, 50);
            usedColor = a.getColor(R.styleable.ProgressView_used_color, ResourceUtil.getColorById(Used_Color));
            unUsedColor = a.getColor(R.styleable.ProgressView_unused_color, ResourceUtil.getColorById(Unused_Color));
            a.recycle();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }
}
