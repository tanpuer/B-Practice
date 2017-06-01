package com.example.cw.b_practice.download.task;

import android.os.AsyncTask;

import com.example.cw.b_practice.download.listener.DownLoadListener;

/**
 * Created by cw on 2017/6/1.
 */

public class DownLoadTask extends AsyncTask<String, Integer, Integer> {

    private DownLoadListener mDownLoadListener;

    public DownLoadTask(DownLoadListener downLoadListener) {
        mDownLoadListener = downLoadListener;
    }

    @Override
    protected Integer doInBackground(String... params) {
        return null;
    }

}
