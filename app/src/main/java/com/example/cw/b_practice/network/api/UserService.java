package com.example.cw.b_practice.network.api;


import com.example.cw.b_practice.entity.user.UserChaseBangumiInfo;
import com.example.cw.b_practice.entity.user.UserCoinsInfo;
import com.example.cw.b_practice.entity.user.UserContributeInfo;
import com.example.cw.b_practice.entity.user.UserPlayGameInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by cw on 2016/10/12 22:40
 * 284619466@qq.com
 * <p>
 * 用户相关api
 */

public interface UserService {

  /**
   * 用户所玩游戏
   */
  @GET("ajax/game/GetLastPlay")
  Observable<UserPlayGameInfo> getUserPlayGames(@Query("mid") int mid);

  /**
   * 用户投币视频
   */
  @GET("ajax/member/getCoinVideos")
  Observable<UserCoinsInfo> getUserCoinVideos(@Query("mid") int mid);

  /**
   * 用户追番
   */
  @GET("ajax/Bangumi/getList")
  Observable<UserChaseBangumiInfo> getUserChaseBangumis(@Query("mid") int mid);

  /**
   * 用户投稿视频
   */
  @GET("ajax/member/getSubmitVideos")
  Observable<UserContributeInfo> getUserContributeVideos(
          @Query("mid") int mid, @Query("page") int page, @Query("pagesize") int pageSize);
}
