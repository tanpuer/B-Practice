package com.example.cw.b_practice.module.homeRecommend.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
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
    private Context mContext;

    public RecommendRecyclerViewAdapter(List<RecommendInfo.ResultBean> infoList, List<RecommendBannerInfo.DataBean> bannerList, Context context) {
        this.infoList = infoList;
        this.bannerList = bannerList;
        mInflater = LayoutInflater.from(context);
        mContext = context;
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
            RecommendInfo.ResultBean.HeadBean headBean = infoList.get(position-1).getHead();
            ((InfoViewHolder) holder).txt.setText(headBean.getTitle());
            for (int i=0; i<((InfoViewHolder) holder).mGridLayout.getChildCount();i++){
                RecommendInfo.ResultBean.BodyBean bodyBean = infoList.get(position-1).getBody().get(i);
                CardView cardView = (CardView) ((InfoViewHolder) holder).mGridLayout.getChildAt(i);

                cardView.setOnClickListener((v)-> Toast.makeText(mContext, bodyBean.getGotoX(), Toast.LENGTH_SHORT).show());

                ImageView imageView = (ImageView) cardView.findViewById(R.id.recommend_image);
                TextView playNum = (TextView) cardView.findViewById(R.id.recommend_play_num);
                TextView reviewNum = (TextView) cardView.findViewById(R.id.recommend_review_num);
                TextView desc = (TextView) cardView.findViewById(R.id.recommend_desc);
                desc.setText(bodyBean.getTitle());
                playNum.setText(bodyBean.getPlay());
                reviewNum.setText(bodyBean.getDanmaku());
                Glide.with(mContext).load(bodyBean.getCover()).into(imageView);

            }
        }
    }

    @Override
    public int getItemCount() {
        //banner只算一个
        if (bannerList.size() ==0 && infoList.size() ==0){
            return 0;
        }else {
            return 1+infoList.size();
        }
    }

    @Override
    public int getItemViewType(int position) {
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
        GridLayout mGridLayout;
        public InfoViewHolder(View itemView) {
            super(itemView);
            txt = (TextView) itemView.findViewById(R.id.recommend_info_title);
            mGridLayout = (GridLayout) itemView.findViewById(R.id.recommend_grid_layout);
        }
    }

}
