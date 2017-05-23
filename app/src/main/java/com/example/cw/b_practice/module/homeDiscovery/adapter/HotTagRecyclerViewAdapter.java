package com.example.cw.b_practice.module.homeDiscovery.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cw.b_practice.R;
import com.example.cw.b_practice.entity.discover.HotSearchTag;

import java.util.List;

/**
 * Created by cw on 2017/5/23.
 */

public class HotTagRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<HotSearchTag.ListBean> mList;
    private Context mContext;
    private LayoutInflater mInflater;

    public HotTagRecyclerViewAdapter(List<HotSearchTag.ListBean> list, Context context) {
        mList = list;
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(mInflater.inflate(R.layout.discovery_tag_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder){
            ((MyViewHolder) holder).txt.setText(mList.get(position).getKeyword());
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView txt;
        public MyViewHolder(View itemView) {
            super(itemView);
            txt = (TextView) itemView.findViewById(R.id.discovery_tag_txt);
        }
    }
}
