package com.example.cw.b_practice.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cw.b_practice.R;
import com.example.cw.b_practice.util.ResourceUtil;

/**
 * Created by cw on 2017/5/25.
 */

public class DialogTips {

    private static final String TAG = "DialogTips";
    private static Dialog mDialog;

    private static final int DIALOG_TYPE_VERT_LOADING = 0;
    private static final int DIALOG_TYPE_VERT_ICON = 1;

    public static void showTipsDialog(Activity activity, int imageId, String text, int type){
        dismissDialog();
        switch (type){
            case 0:{
                mDialog = new TipsDialog(activity, imageId, text, true);
                break;
            }
            case 1:{
                mDialog = new TipsDialog(activity, imageId, text, false);
                break;
            }
            default:
                break;
        }
        mDialog.show();
        mDialog.setOnDismissListener(dialog -> {
            if (mDialog != null && !mDialog.isShowing())  {
                mDialog = null;
            }
        });
        mDialog.setOnKeyListener((dialog, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_BACK){
                dismissDialog();
                return true;
            }
            return false;
        });
    }

    public static void dismissDialog(){
        if (mDialog != null && mDialog.isShowing()){
            mDialog.dismiss();
        }
        mDialog = null;
    }

    private static class TipsDialog extends Dialog{

        private String mText;
        private int mImageId;
        private boolean isLoading;
        private LayoutInflater mInflater;
        private Activity mActivity;
        TipsDialog(Activity activity, int imageId, String text, boolean isLoading){
            super(activity, R.style.VerticalDialog);
            mActivity = activity;
            mText = text;
            mImageId = imageId;
            this.isLoading = isLoading;
            mInflater = LayoutInflater.from(activity);
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            View view = mInflater.inflate(R.layout.dialog_tips, null, false);
            ImageView imageView = (ImageView) view.findViewById(R.id.dialog_image);
            ProgressBar progressView = (ProgressBar) view.findViewById(R.id.dialog_progress);
            TextView textView = (TextView) view.findViewById(R.id.dialog_txt);
            if (imageView != null){
                imageView.setVisibility(isLoading? View.INVISIBLE: View.VISIBLE);
                AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getDrawable();
                animationDrawable.start();
            }
            if (progressView != null){
                progressView.setVisibility(isLoading? View.VISIBLE: View.INVISIBLE);
                progressView.getIndeterminateDrawable().setColorFilter(ResourceUtil.getColorById(R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
            }
            if (!TextUtils.isEmpty(mText)){
                textView.setText(mText);
            }
            setContentView(view);
        }
    }

    public static void showImageToast(Context context, Drawable drawable, int duration){
        if (context == null || drawable ==null){
            return;
        }
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.toast_tips, null, false);
        if (view != null){
            ImageView imageView = (ImageView) view.findViewById(R.id.toast_image);
            if (imageView != null){
                imageView.setImageDrawable(drawable);
            }
            Toast toast = new Toast(context);
            toast.setView(view);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.setDuration(duration);
            toast.show();
        }
    }
}
