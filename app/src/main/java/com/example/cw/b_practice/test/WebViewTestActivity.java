package com.example.cw.b_practice.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.cw.b_practice.R;

/**
 * Created by cw on 2017/5/16.
 */

public class WebViewTestActivity extends AppCompatActivity{

    private WebView mWebView;
    private ScrollView mScrollView;
    private LinearLayout mLinearLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_test);
        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl("http://10.4.32.210:3000");
//        mWebView.loadUrl("http://www.baidu.com");
        mLinearLayout = (LinearLayout) findViewById(R.id.linear_layout);
//        addViews();
    }

    private void addViews() {
        for (int i=0; i<500; i++){
            TextView textView = new TextView(this);
            textView.setText("111111");
            mLinearLayout.addView(textView);
        }
    }

}
