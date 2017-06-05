package com.example.cw.b_practice.module.videoDetail.videoComments.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.cw.b_practice.R;
import com.example.cw.b_practice.entity.video.VideoCommentInfo;
import com.example.cw.b_practice.util.DateDetail;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by cw on 2017/5/30.
 */

public class CommentsRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int DEFAULT_TYPE = 1;
    private static final int FOOTER_TYPE = 2;
    private List<VideoCommentInfo.List> mLists;
    private List<VideoCommentInfo.HotList> mHotLists;
    private Context mContext;
    private LayoutInflater mInflater;

    public CommentsRecyclerAdapter(List<VideoCommentInfo.List> lists, List<VideoCommentInfo.HotList> hotLists, Context context) {
        mLists = lists;
        mHotLists = hotLists;
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(mInflater.inflate(R.layout.video_comments_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CommentsRecyclerAdapter.MyViewHolder) {
            VideoCommentInfo.List mList = mLists.get(position);
            Glide.with(mContext).load(mList.face).into(((MyViewHolder) holder).mCircleImage);
            checkLevel(mList.level_info.current_level, (MyViewHolder)holder);
            ((MyViewHolder) holder).mAuthor.setText(mList.fbid);
            ((MyViewHolder) holder).mFloor.setText("#" + mList.lv);

            switch (mList.sex) {
                case "女":
                    ((MyViewHolder) holder).mUserSex.setImageResource(R.drawable.ic_user_female);
                    break;
                case "男":
                    ((MyViewHolder) holder).mUserSex.setImageResource(R.drawable.ic_user_male);
                    break;
                default:
                    ((MyViewHolder) holder).mUserSex.setImageResource(R.drawable.ic_user_gay_border);
                    break;
            }

            ((MyViewHolder) holder).mNum.setText(String.valueOf(mList.reply_count));
            ((MyViewHolder) holder).mPraise.setText(String.valueOf(mList.good));
            long l = DateDetail.stringToLong(mList.create_at, "yyyy-MM-dd HH:mm");
            ((MyViewHolder) holder).mTime.setText(DateDetail.getDescriptionTimeFromTimestamp(l));
            ((MyViewHolder) holder).mDetail.setText(mList.msg);
        }
    }

    private void checkLevel(int currentLevel, MyViewHolder mHolder) {

        if (currentLevel == 0) {
            mHolder.mUserLv.setImageResource(R.drawable.ic_lv0);
        } else if (currentLevel == 1) {
            mHolder.mUserLv.setImageResource(R.drawable.ic_lv1);
        } else if (currentLevel == 2) {
            mHolder.mUserLv.setImageResource(R.drawable.ic_lv2);
        } else if (currentLevel == 3) {
            mHolder.mUserLv.setImageResource(R.drawable.ic_lv3);
        } else if (currentLevel == 4) {
            mHolder.mUserLv.setImageResource(R.drawable.ic_lv4);
        } else if (currentLevel == 5) {
            mHolder.mUserLv.setImageResource(R.drawable.ic_lv5);
        } else if (currentLevel == 6) {
            mHolder.mUserLv.setImageResource(R.drawable.ic_lv6);
        }
    }

    @Override
    public int getItemCount() {
//        return mLists.size() + mHotLists.size();
        return mLists.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.circle_image)
        CircleImageView mCircleImage;
        @BindView(R.id.label)
        ImageView mUserLv;
        @BindView(R.id.user_avatar_layout)
        LinearLayout mUserAvatarLayout;
        @BindView(R.id.author)
        TextView mAuthor;
        @BindView(R.id.author_sex)
        ImageView mUserSex;
        @BindView(R.id.floor)
        TextView mFloor;
        @BindView(R.id.time)
        TextView mTime;
        @BindView(R.id.detail)
        TextView mDetail;
        @BindView(R.id.comment_more)
        ImageView mCommentMore;
        @BindView(R.id.praise)
        TextView mPraise;
        @BindView(R.id.num)
        TextView mNum;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
