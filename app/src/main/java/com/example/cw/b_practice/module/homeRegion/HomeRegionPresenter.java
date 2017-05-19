package com.example.cw.b_practice.module.homeRegion;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.cw.b_practice.MyApplication;
import com.example.cw.b_practice.entity.region.RegionTypesInfo;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by cw on 2017/5/19.
 */

public class HomeRegionPresenter implements IHomeRegionContract.IHomeRegionPresenter{

    private static final String TAG = "HomeRegionPresenter";
    private WeakReference<IHomeRegionContract.IHomeRegionView> mViews;

    public HomeRegionPresenter(IHomeRegionContract.IHomeRegionView view) {
        mViews = new WeakReference<>(view);
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
    public void loadData() {
        Observable.just(readAssetJson())
                .map(s -> new Gson().fromJson(s, RegionTypesInfo.class))
                .map(RegionTypesInfo::getData)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(dataBeen -> {
                    final IHomeRegionContract.IHomeRegionView view = mViews.get();
                    if (view !=null){
                        Log.d(TAG, "loadData: " + dataBeen.size());
                        view.setData(dataBeen);
                    }
                });
    }

    private String readAssetJson(){
        AssetManager assetManager = MyApplication.getInstance().getAssets();
        try {
            InputStream is = assetManager.open("region.json");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
            StringBuffer stringBuffer = new StringBuffer();
            String str;
            while ((str = bufferedReader.readLine())!=null){
                stringBuffer.append(str);
            }
            is.close();
            bufferedReader.close();
            return stringBuffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
