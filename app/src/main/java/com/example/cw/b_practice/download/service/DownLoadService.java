package com.example.cw.b_practice.download.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;

import com.example.cw.b_practice.R;
import com.example.cw.b_practice.download.listener.DownLoadListener;
import com.example.cw.b_practice.download.task.DownLoadTask;
import com.example.cw.b_practice.module.main.MainActivity;

/**
 * Created by cw on 2017/6/1.
 */

public class DownLoadService extends Service {

    public static final int DOWNLOAD_NOTIFICATION_ID = 1;
    private DownLoadTask mDownLoadTask;
    private DownLoadListener mDownLoadListener = new DownLoadListener() {
        @Override
        public void onProgress(int progress) {

        }

        @Override
        public void onSuccess() {

        }

        @Override
        public void onFailed() {

        }

        @Override
        public void onPause() {

        }

        @Override
        public void onCancel() {

        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private String downLoadUrl;

    private DownLoadBinder mBinder = new DownLoadBinder();

    private class DownLoadBinder extends Binder{

        public void startDownLoad(String url){
            if (mDownLoadTask ==null){
                downLoadUrl = url;
                mDownLoadTask = new DownLoadTask(mDownLoadListener);
                mDownLoadTask.execute(url);
                startForeground(DOWNLOAD_NOTIFICATION_ID, getNotification("Downloading", 0));
            }
        }

        public void pauseDownLoad(){

        }

        public void cancelDownLoad(){

        }
    }

    private NotificationManager getNotificationManager(){
        return (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    private Notification getNotification(String title, int progress){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 ,intent, 0);
        builder.setContentIntent(pendingIntent)
                .setContentTitle(title)
                .setContentText(progress + "%")
                .setProgress(100, progress, false)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        return builder.build();
    }
}

