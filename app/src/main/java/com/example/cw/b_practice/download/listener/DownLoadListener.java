package com.example.cw.b_practice.download.listener;

/**
 * Created by cw on 2017/6/1.
 */

public interface DownLoadListener {

    void onProgress(int progress);

    void onSuccess();

    void onFailed();

    void onPause();

    void onCancel();

}
