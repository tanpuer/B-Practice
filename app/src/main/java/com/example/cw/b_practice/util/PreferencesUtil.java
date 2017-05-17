package com.example.cw.b_practice.util;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.cw.b_practice.MyApplication;

/**
 * Created by cw on 2017/5/15.
 */

public class PreferencesUtil {

    public static void setString(String key, String value){
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(MyApplication.getInstance()).edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static void setBoolean(String key, boolean value){
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(MyApplication.getInstance()).edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static String getString(String key, String defaultValue){
        return PreferenceManager.getDefaultSharedPreferences(MyApplication.getInstance()).getString(key, defaultValue);
    }

    public static boolean getBoolean(String key, boolean defaultValue){
        return PreferenceManager.getDefaultSharedPreferences(MyApplication.getInstance()).getBoolean(key, defaultValue);
    }
}
