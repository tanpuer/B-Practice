package com.example.cw.b_practice.module.rank;

import android.os.Bundle;
import android.view.View;

import com.example.cw.b_practice.R;
import com.example.cw.b_practice.util.ResourceUtil;

import java.lang.ref.WeakReference;

/**
 * Created by cw on 2017/5/23.
 */

public class RankPresenter implements IRankContract.IRankPresenter{

    private WeakReference<IRankContract.IRankView> mView;
    private String[] titles;
    private String[] types;
    private String toolbarTitle;


    public RankPresenter(IRankContract.IRankView view) {
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
    public void getTypeInfo(int type) {
        final IRankContract.IRankView view = mView.get();
        if (view != null){
            switch (type){
                case 1:{
                    titles = new String[] {
                            "番剧", "动画", "音乐", "舞蹈", "游戏",
                            "科技", "生活", "鬼畜", "时尚", "娱乐", "电影", "电视剧"
                    };
                    types = new String[] {
                            "all-03-13.json", "all-03-1.json", "all-03-3.json",
                            "all-03-129.json", "all-03-4.json", "all-03-36.json",
                            "all-03-160.json", "all-03-155.json", "all-03-5.json",
                            "all-03-119.json", "all-03-23.json", "all-03-11.json"
                    };
                    toolbarTitle = ResourceUtil.getStringById(R.string.all_rank);
                    break;
                }
                case 2:{
                    titles = new String[] { "原创", "全站", "番剧" };
                    types = new String[] { "origin-03.json", "all-03.json", "all-3-33.json" };
                    toolbarTitle = ResourceUtil.getStringById(R.string.original_rank);
                    break;
                }
                default:
                    break;
            }
            view.setTypeInfo(titles, types, toolbarTitle);
        }
    }
}
