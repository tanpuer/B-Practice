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

    private int usedColor;
    private int unUsedColor;
    private static final int Used_Color = R.color.colorPrimary;
    private static final int Unused_Color = R.color.gray_dark;
    private Paint usedPaint, unUsedPaint;
    private RectF usedRectF, unUsedRectF;
    private static final int Used_Percent = 0;
    private int used_percent;
    private float left, top, right, bottom;

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
            usedColor = a.getColor(R.styleable.ProgressView_used_color, ResourceUtil.getColorById(Used_Color));
            unUsedColor = a.getColor(R.styleable.ProgressView_unused_color, ResourceUtil.getColorById(Unused_Color));
            used_percent = a.getInt(R.styleable.ProgressView_used_percent, Used_Percent);
            a.recycle();
        }
        usedRectF = new RectF();
        unUsedRectF = new RectF();
        setPaint();
    }

    private void setPaint() {
        usedPaint = new Paint();
        usedPaint.setAntiAlias(true);
        usedPaint.setStyle(Paint.Style.FILL);
        usedPaint.setColor(usedColor);

        unUsedPaint = new Paint();
        unUsedPaint.setAntiAlias(true);
        unUsedPaint.setStyle(Paint.Style.FILL);
        unUsedPaint.setColor(unUsedColor);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float middleX = (right-left)*used_percent / 100;
        usedRectF.set(left, top, middleX, bottom);
        unUsedRectF.set(middleX, top, right, bottom);
        canvas.drawRect(usedRectF, usedPaint);
        canvas.drawRect(unUsedRectF, unUsedPaint);
    }

    public void setPercent(int percent){
        used_percent = percent;
        invalidate();
    }
}
