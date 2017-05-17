package com.example.cw.b_practice.module.homeRecommend.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cw.b_practice.R;
import com.example.cw.b_practice.entity.recommend.RecommendBannerInfo;
import com.example.cw.b_practice.entity.recommend.RecommendInfo;
import com.example.cw.b_practice.module.homeRecommend.banner.GlideImageLoader;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cw on 2017/5/17.
 */

public class RecommendRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "RecommendRecyclerView";
    private static final int Recommend_Banner = 1;
    private static final int Recommend_Info = 2;
    private List<RecommendInfo.ResultBean> infoList;
    private List<RecommendBannerInfo.DataBean> bannerList;
    private LayoutInflater mInflater;

    public RecommendRecyclerViewAdapter(List<RecommendInfo.ResultBean> infoList, List<RecommendBannerInfo.DataBean> bannerList, Context context) {
        this.infoList = infoList;
        this.bannerList = bannerList;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: " + viewType);
        if (viewType == Recommend_Banner){
            return new BannerViewHolder(mInflater.inflate(R.layout.recommend_banner, parent, false));
        }else if (viewType == Recommend_Info){
            return new InfoViewHolder(mInflater.inflate(R.layout.recommend_info, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BannerViewHolder){
            List<String> images = new ArrayList<>();
            for (int i=0;i<bannerList.size();i++){
                images.add(bannerList.get(i).getImage());
            }
            ((BannerViewHolder) holder).mBanner.setImages(images).setImageLoader(new GlideImageLoader()).start();
        }else if (holder instanceof InfoViewHolder){
            ((InfoViewHolder) holder).txt.setText(infoList.get(position).getHead().getTitle());
        }
    }

    @Override
    public int getItemCount() {
        //banner只算一个
        if (bannerList.size() ==0 && infoList.size() ==0){
            return 0;
        }else {
            return 1 + infoList.size();
        }
    }

    @Override
    public int getItemViewType(int position) {
        Log.d(TAG, "getItemViewType: " + position);
        if (position < 1){
            return Recommend_Banner;
        }else if (position >=1 && position< getItemCount()){
            return Recommend_Info;
        }
        return 0;
    }


    /**
     * 推荐页面 banner
     */
    public class BannerViewHolder extends RecyclerView.ViewHolder{

        Banner mBanner;
        public BannerViewHolder(View itemView) {
            super(itemView);
            mBanner = (Banner) itemView.findViewById(R.id.recommend_banner);
        }
    }

    public class InfoViewHolder extends RecyclerView.ViewHolder{

        TextView txt;
        public InfoViewHolder(View itemView) {
            super(itemView);
            txt = (TextView) itemView.findViewById(R.id.recommend_info);
        }
    }

}
