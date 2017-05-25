package com.example.cw.b_practice.module.webview;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.cw.b_practice.MyApplication;
import com.example.cw.b_practice.R;
import com.example.cw.b_practice.util.ClipBoardUtil;
import com.example.cw.b_practice.util.ResourceUtil;
import com.example.cw.b_practice.widget.DialogTips;

import java.lang.ref.WeakReference;

/**
 * Created by cw on 2017/5/25.
 */

public class WebViewPresenter implements IWebViewContract.IWebViewPresenter {

    private WeakReference<IWebViewContract.IWebViewView> mView;

    public WebViewPresenter(IWebViewContract.IWebViewView view) {
        mView = new WeakReference<>(view);
    }

    @Override
    public void onCreate(Bundle args) {

    }

    @Override
    public void onCreateView(Bundle savedInstanceState) {

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void copyToBoard(String url) {
        ClipBoardUtil.setText(url);
        DialogTips.showImageToast(MyApplication.getInstance(), ResourceUtil.getDrawableById(R.drawable.icon_up), Toast.LENGTH_SHORT);
    }
}
