package com.example.cw.b_practice.widget.mzbanner.holder;

import android.content.Context;

/**
 * Created by cw on 2017/6/5.
 */

public interface MZViewHolder<T> {

    void createView(Context context);

    void bindView(Context context, int position, T data);

}
