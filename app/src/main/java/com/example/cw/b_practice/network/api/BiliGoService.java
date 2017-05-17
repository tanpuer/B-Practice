package com.example.cw.b_practice.network.api;


import com.example.cw.b_practice.entity.bangumi.NewBangumiSerialInfo;
import com.example.cw.b_practice.entity.video.HDVideoInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by cw on 16/8/30 19:52
 * 284619466@qq.com
 * <p>
 * bilibili-go相关api
 */
public interface BiliGoService {

  /**
   * b站高清视频
   * quailty:清晰度(1~2，根据视频有不同)
   * type: 格式(mp4/flv)
   */
  @GET("/video/{cid}")
  Observable<HDVideoInfo> getHDVideoUrl(@Path("cid") int cid,
                                        @Query("quailty") int quailty,
                                        @Query("type") String type);

  /**
   * 新番连载
   */
  @GET("bangumi")
  Observable<NewBangumiSerialInfo> getNewBangumiSerialList();
}