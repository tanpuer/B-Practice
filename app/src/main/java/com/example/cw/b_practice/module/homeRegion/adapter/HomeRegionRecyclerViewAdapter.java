package com.example.cw.b_practice.module.homeRegion.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.cw.b_practice.R;
import com.example.cw.b_practice.entity.region.RegionTypesInfo;

import java.util.List;

/**
 * Created by cw on 2017/5/19.
 */

public class HomeRegionRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<RegionTypesInfo.DataBean> mList;
    private Context mContext;
    private LayoutInflater mInflater;

    private String[] itemNames = new String[] {
            "直播", "番剧", "动画",
            "音乐", "舞蹈", "游戏",
            "科技", "生活", "鬼畜",
            "时尚", "广告", "娱乐",
            "电影", "电视剧", "游戏中心",
    };

    private int[] itemIcons = new int[] {
            R.drawable.ic_category_live, R.drawable.ic_category_t13,
            R.drawable.ic_category_t1, R.drawable.ic_category_t3,
            R.drawable.ic_category_t129, R.drawable.ic_category_t4,
            R.drawable.ic_category_t36, R.drawable.ic_category_t160,
            R.drawable.ic_category_t119, R.drawable.ic_category_t155,
            R.drawable.ic_category_t165, R.drawable.ic_category_t5,
            R.drawable.ic_category_t23, R.drawable.ic_category_t11,
            R.drawable.ic_category_game_center
    };

    public HomeRegionRecyclerViewAdapter(List<RegionTypesInfo.DataBean> list, Context context) {
        mList = list;
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(mInflater.inflate(R.layout.home_region_recyclerview_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Glide.with(mContext).load(itemIcons[position]).into(((MyViewHolder)holder).img);
        ((MyViewHolder) holder).txt.setText(itemNames[position]);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView txt;
        public MyViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.home_region_img);
            txt = (TextView) itemView.findViewById(R.id.home_region_txt);
        }
    }
}
