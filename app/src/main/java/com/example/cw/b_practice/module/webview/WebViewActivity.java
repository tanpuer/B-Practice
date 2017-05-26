package com.example.cw.b_practice.module.webview;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.cw.b_practice.R;
import com.example.cw.b_practice.base.RxBaseActivity;
import com.example.cw.b_practice.util.ConstantsUtil;
import com.example.cw.b_practice.util.ResourceUtil;

/**
 * Created by cw on 2017/5/25.
 */

public class WebViewActivity extends RxBaseActivity implements IWebViewContract.IWebViewView{

    private static final String TAG = "WebViewActivity";
    private IWebViewContract.IWebViewPresenter mPresenter;
    private Toolbar mToolbar;
    private WebView mWebView;
    private String title;
    private String url;
    private View loadingView;
    private ImageView mImageView;
    private AnimationDrawable mAnimationDrawable;
    private WebViewClientBase webViewClient;
    private ProgressBar mProgressBar;

    @Override
    public void setPresenter(IWebViewContract.IWebViewPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_webview;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        if (mPresenter == null){
            mPresenter = new WebViewPresenter(this);
        }
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mWebView = (WebView) findViewById(R.id.webview);
        loadingView = findViewById(R.id.loading_view);
        mImageView = (ImageView) loadingView.findViewById(R.id.loading_image);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        Intent intent = getIntent();
        if (intent !=null) {
            title = intent.getStringExtra(ConstantsUtil.EXTRA_TITLE);
            url = intent.getStringExtra(ConstantsUtil.EXTRA_URL);
        }
        initWebView();
    }

    private void initWebView() {
        webViewClient = new WebViewClientBase();
        final WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        settings.setBlockNetworkImage(true);
        settings.setDomStorageEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setGeolocationEnabled(true);
        settings.setDefaultTextEncodingName("UTF-8");
        mWebView.setWebViewClient(webViewClient);
        mWebView.requestFocus(View.FOCUS_DOWN);
        mWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                AlertDialog.Builder build = new AlertDialog.Builder(WebViewActivity.this)
                        .setTitle(R.string.app_name)
                        .setMessage(message)
                        .setPositiveButton(R.string.positive_btn, (dialog, which) -> result.confirm());
                build.setCancelable(false);
                build.create();
                build.show();
                return true;
            }
        });
        mWebView.setWebChromeClient(new WebChromeClient(){

            @Override
            public void onReceivedTitle(WebView view, String title) {
                Log.d(TAG, "onReceivedTitle: " + title);
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                Log.d(TAG, "onProgressChanged: " + newProgress);
                mProgressBar.setProgress(newProgress);
            }
        });
        mWebView.loadUrl(url);
    }

    @Override
    protected void initToolBar() {
        mToolbar.setTitle(TextUtils.isEmpty(title)? ResourceUtil.getStringById(R.string.title_default): title);
        setSupportActionBar(mToolbar);
    }

    @Override
    public void showOrHideLoadingView(boolean show) {
        if (mAnimationDrawable == null){
            mAnimationDrawable = (AnimationDrawable) mImageView.getDrawable();
        }
        if (show){
            mAnimationDrawable.start();
            loadingView.setVisibility(View.VISIBLE);
        }else {
            mAnimationDrawable.stop();
            mAnimationDrawable = null;
            loadingView.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_webview, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.webview_share:{
                share();
                break;
            }
            case R.id.webview_browser:{
                openInBrowser();
                break;
            }
            case R.id.webview_copy:{
                mPresenter.copyToBoard(url);
                break;
            }
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack() && mWebView.copyBackForwardList().getSize()>0 &&
                !mWebView.getUrl().equals(mWebView.copyBackForwardList().getItemAtIndex(0).getOriginalUrl())){
            mWebView.goBack();
        }else {
            this.finish();
        }
    }

    private void share() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, ResourceUtil.getStringById(R.string.share_title));
        intent.putExtra(Intent.EXTRA_TEXT, ResourceUtil.getStringById(R.string.app_name) + url);
        startActivity(Intent.createChooser(intent, title));
    }

    /**
     * 使用浏览器打开
     */
    private void openInBrowser() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri content_url = Uri.parse(url);
        intent.setData(content_url);
        startActivity(Intent.createChooser(intent, ResourceUtil.getStringById(R.string.choose_browser)));
    }

    public class WebViewClientBase extends WebViewClient{

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            showOrHideLoadingView(true);
            mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            showOrHideLoadingView(false);
            view.getSettings().setBlockNetworkImage(false);
            mProgressBar.setVisibility(View.GONE);
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
            String errorHtml = "<html><body><h2>没有找到网页</h2></body></html>";
            view.loadDataWithBaseURL(null, errorHtml, "text/html", "UTF-8", null);
        }
    }
}
