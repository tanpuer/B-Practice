package com.example.cw.b_practice.entity.recommend;

/**
 * Created by cw on 2017/5/17.
 */

public class RecommendBean {

    private RecommendBannerInfo mRecommendBannerInfo;
    private RecommendInfo mRecommendInfo;

    public RecommendBean(RecommendBannerInfo recommendBannerInfo, RecommendInfo recommendInfo) {
        mRecommendBannerInfo = recommendBannerInfo;
        mRecommendInfo = recommendInfo;
    }

    public RecommendBannerInfo getRecommendBannerInfo() {
        return mRecommendBannerInfo;
    }

    public void setRecommendBannerInfo(RecommendBannerInfo recommendBannerInfo) {
        mRecommendBannerInfo = recommendBannerInfo;
    }

    public RecommendInfo getRecommendInfo() {
        return mRecommendInfo;
    }

    public void setRecommendInfo(RecommendInfo recommendInfo) {
        mRecommendInfo = recommendInfo;
    }
}
