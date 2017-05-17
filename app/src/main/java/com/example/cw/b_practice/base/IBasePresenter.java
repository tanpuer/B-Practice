package com.example.cw.b_practice.base;

import android.os.Bundle;
import android.view.View;

/**
 * Created by cw on 2017/5/15.
 */

public interface IBasePresenter {

    void onCreate(Bundle args);

    void onCreateView(Bundle savedInstanceState);

    void onViewCreated(View view, Bundle savedInstanceState);

    void onResume();

    void onPause();

    void onDestroy();

}
