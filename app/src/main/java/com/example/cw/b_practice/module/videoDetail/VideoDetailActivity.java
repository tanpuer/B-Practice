package com.example.cw.b_practice.module.videoDetail;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.cw.b_practice.R;
import com.example.cw.b_practice.base.RxBaseActivity;
import com.example.cw.b_practice.entity.video.VideoDetailsInfo;
import com.example.cw.b_practice.module.videoDetail.adapter.ViewPagerAdapter;
import com.example.cw.b_practice.module.videoDetail.listener.AppBarLayoutStateChangeListener;
import com.example.cw.b_practice.module.videoDetail.videoComments.VideoCommentsFragment;
import com.example.cw.b_practice.module.videoDetail.videoIntroduction.VideoIntroductionFragment;
import com.example.cw.b_practice.network.auxiliary.UrlHelper;
import com.example.cw.b_practice.util.ConstantsUtil;
import com.example.cw.b_practice.util.DisplayUtil;
import com.example.cw.b_practice.util.ResourceUtil;
import com.example.cw.b_practice.util.StatusBarUtil;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by cw on 2017/5/26.
 */

public class VideoDetailActivity extends RxBaseActivity implements IVideoDetailContract.iVideoDetailView {


    @BindView(R.id.preview_img)
    ImageView mPreviewImg;
    @BindView(R.id.collapsed_layout)
    CollapsingToolbarLayout mCollapsedLayout;
    @BindView(R.id.tv_av)
    TextView mTvAv;
    @BindView(R.id.tv_play)
    TextView mTvPlay;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.sliding_tabs)
    SlidingTabLayout mSlidingTabs;
    @BindView(R.id.app_bar_layout)
    AppBarLayout mAppBarLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.fab)
    FloatingActionButton mFab;

    private IVideoDetailContract.iVideoDetailPresenter mPresenter;
    private String url;
    private int aid;
    private VideoDetailsInfo.DataBean mBean;

    @Override
    public void setPresenter(IVideoDetailContract.iVideoDetailPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_video_detail;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        if (mPresenter == null) {
            setPresenter(new VideDetailPresenter(this));
        }
        Intent intent = getIntent();
        if (intent != null) {
            url = intent.getStringExtra(ConstantsUtil.EXTRA_IMG_URL);
            aid = intent.getIntExtra(ConstantsUtil.EXTRA_AV, -1);
            mTvAv.setText(String.valueOf(aid));
            Glide.with(this)
                    .load(UrlHelper.getClearVideoPreviewUrl(url))
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.bili_default_image_tv)
                    .into(mPreviewImg);
        }
        mAppBarLayout.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> showViewTranslationY(verticalOffset));
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayoutStateChangeListener() {
            @Override
            protected void onStateChange(AppBarLayout appBarLayout, State state, int verticalOffset) {
                if (state == State.EXPANDED) {
                    mTvPlay.setVisibility(View.VISIBLE);
                    mTvAv.setVisibility(View.GONE);
                    mToolbar.setContentInsetsRelative(DisplayUtil.dp2px(VideoDetailActivity.this, 15), 0);
                } else if (state == State.COLLAPSED) {
                    mTvPlay.setVisibility(View.GONE);
                    mTvAv.setVisibility(View.VISIBLE);
                    mToolbar.setContentInsetsRelative(DisplayUtil.dp2px(VideoDetailActivity.this, 150),0);
                } else if (state == State.IDLE) {
                    mTvPlay.setVisibility(View.VISIBLE);
                    mTvAv.setVisibility(View.GONE);
                    mToolbar.setContentInsetsRelative(DisplayUtil.dp2px(VideoDetailActivity.this, 15), 0);
                }
            }
        });
        mPresenter.loadData(aid);
    }

    private void showViewTranslationY(int verticalOffset) {
        mFab.setTranslationY(verticalOffset);
        if (verticalOffset == 0) {
            showFab();
        } else if (verticalOffset < 0) {
            hideFab();
        }
    }

    private void hideFab() {
        mFab.animate().scaleX(0).scaleY(0)
                .setInterpolator(new LinearInterpolator())
                .start();
        mFab.setClickable(false);
    }

    private void showFab() {
        mFab.animate().scaleX(1).scaleY(1)
                .setInterpolator(new LinearInterpolator())
                .start();
        mFab.setClickable(true);
    }

    @Override
    protected void initToolBar() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        //还没收缩时的字体颜色
        mCollapsedLayout.setExpandedTitleColor(Color.TRANSPARENT);
        //收缩后toolbar上字体颜色
        mCollapsedLayout.setCollapsedTitleTextColor(Color.WHITE);
        StatusBarUtil.setStatusBarTransparent(this);
        mTvAv.setText("av" + aid);
    }

    public static void launch(Activity activity, int aid, String urlImage) {
        Intent intent = new Intent(activity, VideoDetailActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(ConstantsUtil.EXTRA_AV, aid);
        intent.putExtra(ConstantsUtil.EXTRA_IMG_URL, urlImage);
        activity.startActivity(intent);
    }

    @Override
    public void showErrorImage() {
        mPreviewImg.setImageResource(R.drawable.img_tips_error_load_error);
    }

    @Override
    public void setFabClickable(boolean clickable) {
        if (clickable){
            mFab.setBackgroundTintList(ColorStateList.valueOf(ResourceUtil.getColorById(R.color.colorPrimary)));
            mFab.setClickable(true);
        }else {
            mFab.setBackgroundTintList(ColorStateList.valueOf(ResourceUtil.getColorById(R.color.gray_20)));
            mFab.setClickable(false);
        }
    }

    @Override
    public void setData(VideoDetailsInfo.DataBean bean) {
        mBean = bean;
        setViewPager(mBean);
    }

    private void setViewPager(VideoDetailsInfo.DataBean bean) {
        List<Fragment> mFragments = new ArrayList<>();
        mFragments.add(VideoIntroductionFragment.newInstance(aid));
        mFragments.add(VideoCommentsFragment.newInstance(aid));
        List<String> mTitles = new ArrayList<>();
        mTitles.add(ResourceUtil.getStringById(R.string.introduction));
        mTitles.add(ResourceUtil.getStringById(R.string.commend ) + "(" +bean.getStat().getReply() + ")");
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(),mTitles,mFragments);
        mViewPager.setAdapter(adapter);
        mSlidingTabs.setViewPager(mViewPager);
    }


}
