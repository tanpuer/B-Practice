package com.example.cw.b_practice.test.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cw.b_practice.R;

/**
 * Created by cw on 2017/5/24.
 */

public class RecyclerTestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int size;
    private Context mContext;
    private LayoutInflater mInflater;

    public RecyclerTestAdapter(int size, Context context) {
        this.size = size;
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(mInflater.inflate(R.layout.recyclerview_test_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof RecyclerTestAdapter.MyViewHolder){
            for (int i=0;i<60;i++){
                TextView textView = new TextView(mContext);
                textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                textView.setText("1");
                ((MyViewHolder) holder).mLinearLayout.addView(textView);
            }
        }
    }

    @Override
    public int getItemCount() {
        return size;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout mLinearLayout;
        public MyViewHolder(View itemView) {
            super(itemView);
            mLinearLayout = (LinearLayout) itemView.findViewById(R.id.linear_layout);
        }
    }
}
