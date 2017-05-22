package com.example.cw.b_practice.module.homeLive.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.cw.b_practice.R;
import com.example.cw.b_practice.entity.live.LiveAppIndexInfo;
import com.example.cw.b_practice.module.homeRecommend.banner.GlideImageLoader;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by cw on 2017/5/22.
 */

public class HomeLiveAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "HomeLiveAdapter";

    private static final int Type_Banner = 1;
    private static final int Type_Entrance = 2;
    private static final int Type_Partition_Title = 3;
    private static final int Type_Partition_Card = 4;


    private List<LiveAppIndexInfo.DataBean.BannerBean> mBannerBeen;
    private List<LiveAppIndexInfo.DataBean.EntranceIconsBean> mEntranceIconBeen;
    private List<LiveAppIndexInfo.DataBean.RecommendDataBean.LivesBean> mLivesBeen;
    private List<LiveAppIndexInfo.DataBean.PartitionsBean> mPartitionsBeen;
    private Context mContext;
    private LayoutInflater mInflater;

    public HomeLiveAdapter(List<LiveAppIndexInfo.DataBean.BannerBean> bannerBeen, List<LiveAppIndexInfo.DataBean.EntranceIconsBean> entranceIconBeen, List<LiveAppIndexInfo.DataBean.PartitionsBean> partitionsBeen, Context context) {
        mBannerBeen = bannerBeen;
        mEntranceIconBeen = entranceIconBeen;
        mPartitionsBeen = partitionsBeen;
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case Type_Banner:
                return new MyBannerViewHolder(mInflater.inflate(R.layout.recommend_banner, parent, false));
            case Type_Entrance:
                return new MyEntranceViewHolder(mInflater.inflate(R.layout.live_entrance, parent, false));
            case Type_Partition_Title:{
                return new MyPartitionTitleViewHolder(mInflater.inflate(R.layout.live_partition_title, parent, false));
            }
            case Type_Partition_Card:
                return new MyPartitionCardViewHolder(mInflater.inflate(R.layout.live_partition_card, parent,false));
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyBannerViewHolder){
            List<String> images = new ArrayList<>();
            for (int i=0;i<mBannerBeen.size();i++){
                images.add(mBannerBeen.get(i).getImg());
            }
            ((MyBannerViewHolder) holder).mBanner.setImages(images).setImageLoader(new GlideImageLoader()).start();
        }else if (holder instanceof MyEntranceViewHolder) {
            LiveAppIndexInfo.DataBean.EntranceIconsBean bean = mEntranceIconBeen.get(position -1);
            Glide.with(mContext).load(bean.getEntrance_icon().getSrc()).into(((MyEntranceViewHolder) holder).img);
            ((MyEntranceViewHolder) holder).txt.setText(bean.getName());
        }
        else if (holder instanceof MyPartitionTitleViewHolder){
            LiveAppIndexInfo.DataBean.PartitionsBean.PartitionBean bean = mPartitionsBeen.get((position-5)/5).getPartition();
            ((MyPartitionTitleViewHolder) holder).txt.setText(bean.getName());
            ((MyPartitionTitleViewHolder) holder).desc.setText(String.valueOf(bean.getId()));
            Glide.with(mContext).load(bean.getSub_icon().getSrc()).into(((MyPartitionTitleViewHolder) holder).img);
        }
        else if (holder instanceof MyPartitionCardViewHolder){
            LiveAppIndexInfo.DataBean.PartitionsBean.LivesBean bean = mPartitionsBeen.get((position-6)/5).getLives().get((position-7)%5);
            Glide.with(mContext).load(bean.getCover().getSrc()).into(((MyPartitionCardViewHolder) holder).img);
            Glide.with(mContext).load(bean.getOwner().getFace()).into(((MyPartitionCardViewHolder) holder).avatar);
            ((MyPartitionCardViewHolder) holder).title.setText(bean.getTitle());
            ((MyPartitionCardViewHolder) holder).name.setText(bean.getOwner().getName());
            ((MyPartitionCardViewHolder) holder).num.setText(String.valueOf(bean.getOnline()));
        }

    }

    @Override
    public int getItemCount() {
        return 1 + mEntranceIconBeen.size() + mPartitionsBeen.size()*5;
    }

    @Override
    public int getItemViewType(int position) {
        if (position ==0){
            return Type_Banner;
        }else if (position<=mEntranceIconBeen.size()){
            return Type_Entrance;
        }else if (position>mEntranceIconBeen.size() && position%5==1){
            return Type_Partition_Title;
        }else {
            return Type_Partition_Card;
        }
    }

    public int getSpanSize(int position){
        switch (getItemViewType(position)){
            case Type_Banner:
                return 10;
            case Type_Entrance:
                return 2;
            case Type_Partition_Title:
                return 10;
            case Type_Partition_Card:
                return 5;
            default:
                return 0;
        }
    }

    public class MyBannerViewHolder extends RecyclerView.ViewHolder{

        private Banner mBanner;
        public MyBannerViewHolder(View itemView) {
            super(itemView);
            mBanner = (Banner) itemView.findViewById(R.id.recommend_banner);
        }
    }

    public class MyEntranceViewHolder extends RecyclerView.ViewHolder{

        private ImageView img;
        private TextView txt;
        public MyEntranceViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.live_entrance_img);
            txt = (TextView) itemView.findViewById(R.id.live_entrance_txt);
        }
    }

    public class MyPartitionTitleViewHolder extends RecyclerView.ViewHolder{

        private ImageView img;
        private TextView txt;
        private TextView desc;
        public MyPartitionTitleViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.live_partition_title_img);
            txt = (TextView) itemView.findViewById(R.id.live_partition_title_txt);
            desc = (TextView) itemView.findViewById(R.id.live_partition_title_num);
        }
    }

    public class MyPartitionCardViewHolder extends RecyclerView.ViewHolder{

        private ImageView img;
        private CircleImageView avatar;
        private TextView title, name, num;
        public MyPartitionCardViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.live_partition_card_bg);
            avatar = (CircleImageView) itemView.findViewById(R.id.live_partition_card_circle_image);
            title = (TextView) itemView.findViewById(R.id.live_partition_card_title);
            name = (TextView) itemView.findViewById(R.id.live_partition_card_name);
            num = (TextView) itemView.findViewById(R.id.live_partition_card_num);
        }
    }
}
