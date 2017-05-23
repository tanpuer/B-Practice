package com.example.cw.b_practice.util;

import com.example.cw.b_practice.MyApplication;

/**
 * Created by cw on 2017/5/23.
 */

public class ResourceUtil {

    public static String getStringById(int id){
        return MyApplication.getInstance().getResources().getString(id);
    }

    public static int getDimenById(int id){
        return (int) MyApplication.getInstance().getResources().getDimension(id);
    }
}
