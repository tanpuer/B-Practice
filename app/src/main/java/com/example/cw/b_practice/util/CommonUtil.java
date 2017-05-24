package com.example.cw.b_practice.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.os.StatFs;

import java.io.File;

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

    /**
     * 检查SD卡是否存在
     * @return
     */
    public static boolean checkSDCard(){
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取手机SD卡总空间
     * @return
     */
    public static long getSDCardTotalSize(){
        if (checkSDCard()){
            File path = Environment.getExternalStorageDirectory();
            StatFs statFs = new StatFs(path.getPath());
            long blockingSize = statFs.getBlockSizeLong();
            long blockingCount = statFs.getBlockCountLong();
            return blockingSize * blockingCount;
        }else {
            return 0;
        }
    }

    /**
     * 获取手机SD卡剩余空间
     * @return
     */
    public static long getSDCardAvailableSize(){
        if (checkSDCard()){
            File path = Environment.getExternalStorageDirectory();
            StatFs statFs = new StatFs(path.getPath());
            long blockingSize = statFs.getBlockSizeLong();
            long availableCount = statFs.getAvailableBlocksLong();
            return blockingSize * availableCount;
        }else {
            return 0;
        }
    }

    /**
     * 获取手机内部存储总空间
     * @return
     */
    public static long getPhoneTotalSize(){
        if (!checkSDCard()){
            File path = Environment.getDataDirectory();
            StatFs statFs = new StatFs(path.getPath());
            long blockingSize = statFs.getBlockSizeLong();
            long blockingCount = statFs.getBlockCountLong();
            return blockingSize * blockingCount;
        }else {
            return getSDCardTotalSize();
        }
    }

    public static long getPhoneAvailableSize(){
        if (!checkSDCard()){
            File path = Environment.getDataDirectory();
            StatFs statFs = new StatFs(path.getPath());
            long blockingSize = statFs.getBlockSizeLong();
            long availableCount = statFs.getAvailableBlocksLong();
            return blockingSize * availableCount;
        }else {
            return getSDCardAvailableSize();
        }
    }
}
