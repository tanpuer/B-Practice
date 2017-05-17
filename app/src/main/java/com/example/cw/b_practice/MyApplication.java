package com.example.cw.b_practice;

import android.app.Application;
import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;

import com.bilibili.magicasakura.utils.ThemeUtils;
import com.example.cw.b_practice.util.ThemeHelper;
import com.facebook.stetho.Stetho;

/**
 * Created by cw on 2017/5/15.
 */

public class MyApplication extends Application implements ThemeUtils.switchColor{

    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        Stetho.initializeWithDefaults(this);
    }

    public static MyApplication getInstance(){
        return mInstance;
    }

    @Override
    public int replaceColorById(Context context, @ColorRes int colorId) {
        return 0;
    }

    @Override
    public int replaceColor(Context context, @ColorInt int color) {
        return 0;
    }


    private String getTheme(Context context) {

        if (ThemeHelper.getTheme(context) == ThemeHelper.CARD_STORM) {
            return "blue";
        } else if (ThemeHelper.getTheme(context) == ThemeHelper.CARD_HOPE) {
            return "purple";
        } else if (ThemeHelper.getTheme(context) == ThemeHelper.CARD_WOOD) {
            return "green";
        } else if (ThemeHelper.getTheme(context) == ThemeHelper.CARD_LIGHT) {
            return "green_light";
        } else if (ThemeHelper.getTheme(context) == ThemeHelper.CARD_THUNDER) {
            return "yellow";
        } else if (ThemeHelper.getTheme(context) == ThemeHelper.CARD_SAND) {
            return "orange";
        } else if (ThemeHelper.getTheme(context) == ThemeHelper.CARD_FIREY) {
            return "red";
        }
        return null;
    }
}
