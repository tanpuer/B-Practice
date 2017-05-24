package com.example.cw.b_practice.module.offLine;

import android.os.Bundle;
import android.text.format.Formatter;

import com.example.cw.b_practice.R;
import com.example.cw.b_practice.base.RxBaseActivity;
import com.example.cw.b_practice.util.CommonUtil;

/**
 * Created by cw on 2017/5/24.
 */

public class OffLineActivity extends RxBaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_offline;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        long phoneAvailableSize = CommonUtil.getPhoneAvailableSize();
        long phoneTotalSize = CommonUtil.getPhoneTotalSize();
        String availableSizeStr = Formatter.formatFileSize(this, phoneAvailableSize);
        String totalSizeStr = Formatter.formatFileSize(this, phoneTotalSize);
        int progress = countProgress(phoneAvailableSize, phoneTotalSize);
    }

    private int countProgress(long phoneAvailableSize, long phoneTotalSize) {
        double availableSize = phoneAvailableSize/(1024*3);
        double totalSize = phoneTotalSize/(1024*3);
        int size = (int) (Math.floor(totalSize) - Math.floor(availableSize));
        return (int) (size/Math.floor(totalSize) * 100);
    }

    @Override
    protected void initToolBar() {

    }
}
