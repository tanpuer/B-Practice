package com.example.cw.b_practice.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by cw on 2017/5/15.
 */

public class CommonUtil {

    public static NetworkInfo getNetWorkInfo(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo();
    }

    /**
     * @param context
     * 判断是否有网络
     * @return
     */
    public static boolean isNetWorkAvailable(Context context){
        NetworkInfo networkInfo = getNetWorkInfo(context);
        return networkInfo != null && networkInfo.isAvailable();
    }

    /**
     * @param context
     * 判断网络是否是wifi
     * @return
     */
    public static boolean isNetWorkWiFi(Context context){
        NetworkInfo networkInfo = getNetWorkInfo(context);
        if (networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_WIFI){
            return true;
        }
        return false;
    }
}
