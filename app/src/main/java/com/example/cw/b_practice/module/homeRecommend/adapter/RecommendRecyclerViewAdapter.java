package com.example.cw.b_practice.module.homeRecommend.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.example.cw.b_practice.module.webview.WebViewActivity;
import com.example.cw.b_practice.util.ConstantsUtil;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cw on 2017/5/17.
 */

public class RecommendRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "RecommendRecyclerView";
    private static final int Recommend_Banner = 1;
    private static final int Recommend_Info_Card = 2;
    private static final int Recommend_Info_Image = 3;
    private List<RecommendInfo.ResultBean> infoList;
    private List<RecommendBannerInfo.DataBean> bannerList;
    private LayoutInflater mInflater;
    private Context mContext;
    private int[] icons = new int[] {
            R.drawable.ic_header_hot, R.drawable.ic_head_live,
            R.drawable.ic_category_t13, R.drawable.ic_category_t1,
            R.drawable.ic_category_t3, R.drawable.ic_category_t129,
            R.drawable.ic_category_t4, R.drawable.ic_category_t119,
            R.drawable.ic_category_t36, R.drawable.ic_category_t160,
            R.drawable.ic_category_t155, R.drawable.ic_category_t5,
            R.drawable.ic_category_t11, R.drawable.ic_category_t23,
            R.drawable.ic_category_t11, R.drawable.ic_category_t23,

    };

    public RecommendRecyclerViewAdapter(List<RecommendInfo.ResultBean> infoList, List<RecommendBannerInfo.DataBean> bannerList, Context context) {
        this.infoList = infoList;
        this.bannerList = bannerList;
        mInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: " + viewType);
        switch (viewType){
            case Recommend_Banner:
                return new BannerViewHolder(mInflater.inflate(R.layout.recommend_banner, parent, false));
            case Recommend_Info_Card:
                return new InfoViewHolder(mInflater.inflate(R.layout.recommend_info, parent, false));
            case Recommend_Info_Image:
                return new InfoImageHolder(mInflater.inflate(R.layout.recommend_image, parent, false));
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BannerViewHolder){
            List<String> images = new ArrayList<>();
            for (int i=0;i<bannerList.size();i++){
                images.add(bannerList.get(i).getImage());
            }
            ((BannerViewHolder) holder).mBanner.setImages(images).setImageLoader(new GlideImageLoader()).start();
            // TODO: 2017/5/25 这个组件库缺少getCurrentItem方法。
            ((BannerViewHolder) holder).mBanner.setOnBannerListener(view->{
                Intent intent = new Intent(mContext, WebViewActivity.class);
                intent.putExtra(ConstantsUtil.EXTRA_URL, bannerList.get(0).getValue());
                intent.putExtra(ConstantsUtil.EXTRA_TITLE, bannerList.get(0).getTitle());
                mContext.startActivity(intent);
            });
        }else if (holder instanceof InfoViewHolder){
            RecommendInfo.ResultBean.HeadBean headBean = infoList.get(position-1).getHead();
            ((InfoViewHolder) holder).txt.setText(headBean.getTitle());
            Glide.with(mContext).load(icons[position]).into(((InfoViewHolder) holder).titleImage);
            for (int i=0; i<((InfoViewHolder) holder).mGridLayout.getChildCount();i++){
                Log.d(TAG, "onBindViewHolder: " + holder);
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
        }else if (holder instanceof InfoImageHolder){
            List<RecommendInfo.ResultBean.BodyBean> resultBean = infoList.get(position).getBody();
            if (resultBean.size()>=1){
                RecommendInfo.ResultBean.HeadBean headBean = infoList.get(position-1).getHead();
                Glide.with(mContext).load(resultBean.get(0).getCover()).into(((InfoImageHolder) holder).img);
                Glide.with(mContext).load(icons[position]).into(((InfoImageHolder) holder).titleImage);
                ((InfoImageHolder) holder).txt.setText(headBean.getTitle());
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
//            if (infoList.get(position).getBody().size() == 1) {
//                return Recommend_Info_Image;
//            }else {
//                return Recommend_Info_Card;
//            }
            return Recommend_Info_Card;
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
            // TODO: 2017/5/25 这个banner缺少getCurrentPage的方法,默认第一个...
        }
    }

    public class InfoViewHolder extends RecyclerView.ViewHolder{

        TextView txt;
        GridLayout mGridLayout;
        ImageView titleImage;
        public InfoViewHolder(View itemView) {
            super(itemView);
            txt = (TextView) itemView.findViewById(R.id.recommend_info_title);
            titleImage = (ImageView) itemView.findViewById(R.id.recommend_title_img);
            mGridLayout = (GridLayout) itemView.findViewById(R.id.recommend_grid_layout);
        }
    }

    public class InfoImageHolder extends RecyclerView.ViewHolder{

        private ImageView titleImage;
        TextView txt;
        private ImageView img;
        public InfoImageHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.recommend_image);
            txt = (TextView) itemView.findViewById(R.id.recommend_info_title);
            titleImage = (ImageView) itemView.findViewById(R.id.recommend_title_img);
        }
    }

}
