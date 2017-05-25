package com.example.cw.b_practice.util;

import android.annotation.TargetApi;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

import com.example.cw.b_practice.MyApplication;

/**
 * 剪切板工具类
 * Created by cw on 2017/5/25.
 */

@TargetApi(11)
public class ClipBoardUtil {

    private static ClipboardManager sClipboardManager;

    public static ClipboardManager getInstance(){
        if (sClipboardManager == null){
            sClipboardManager = (ClipboardManager) MyApplication.getInstance().getSystemService(Context.CLIPBOARD_SERVICE);

        }
        return sClipboardManager;
    }

    public static void setText(CharSequence text){
        ClipData clipData = ClipData.newPlainText("simple text", text);
        getInstance().setPrimaryClip(clipData);
    }

    public static String getText(){
        StringBuilder stringBuilder = new StringBuilder();
        if (getInstance().hasPrimaryClip()) {
            ClipData clipData = getInstance().getPrimaryClip();
            int count = clipData.getItemCount();
            for (int i = 0; i < count; i++) {
                ClipData.Item item = clipData.getItemAt(count);
                CharSequence str = item.coerceToText(MyApplication.getInstance());
                stringBuilder.append(str);
            }
        }
        return stringBuilder.toString();
    }
}
