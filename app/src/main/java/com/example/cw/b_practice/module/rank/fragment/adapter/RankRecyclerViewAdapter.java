package com.example.cw.b_practice.module.rank.fragment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.cw.b_practice.R;
import com.example.cw.b_practice.entity.discover.AllareasRankInfo;
import com.example.cw.b_practice.util.ResourceUtil;

import java.util.List;

/**
 * Created by cw on 2017/5/24.
 */

public class RankRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<AllareasRankInfo.RankBean.ListBean> mList;
    private Context mContext;
    private LayoutInflater mInflater;

    public RankRecyclerViewAdapter(List<AllareasRankInfo.RankBean.ListBean> list, Context context) {
        mList = list;
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(mInflater.inflate(R.layout.rank_recyclerview_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AllareasRankInfo.RankBean.ListBean bean = mList.get(position);
        if (holder instanceof RankRecyclerViewAdapter.MyViewHolder){
            ((MyViewHolder) holder).sortNum.setText(String.valueOf(position+1));
            Glide.with(mContext).load(bean.getPic()).placeholder(R.drawable.bili_default_image_tv).into(((MyViewHolder) holder).mImageView);
            ((MyViewHolder) holder).userName.setText(bean.getAuthor());
            ((MyViewHolder) holder).title.setText(bean.getTitle());
            ((MyViewHolder) holder).playNum.setText(bean.getPlay());
            ((MyViewHolder) holder).reviewNum.setText(String.valueOf(bean.getReview()));
            if (position<3){
                ((MyViewHolder) holder).sortNum.setTextSize(22);
                ((MyViewHolder) holder).sortNum.setTextColor(ResourceUtil.getColorById(R.color.colorPrimary));
            }
        }
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView sortNum, title, userName, playNum, reviewNum;
        private ImageView mImageView;
        public MyViewHolder(View itemView) {
            super(itemView);
            sortNum = (TextView) itemView.findViewById(R.id.sort_num);
            mImageView = (ImageView) itemView.findViewById(R.id.rank_image);
            title = (TextView) itemView.findViewById(R.id.rank_title);
            userName = (TextView) itemView.findViewById(R.id.rank_user);
            playNum = (TextView) itemView.findViewById(R.id.rank_play);
            reviewNum = (TextView) itemView.findViewById(R.id.rank_review);
        }
    }
}
