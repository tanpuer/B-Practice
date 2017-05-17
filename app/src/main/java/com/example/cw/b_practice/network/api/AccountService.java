package com.example.cw.b_practice.network.api;


import com.example.cw.b_practice.entity.user.UserDetailsInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by cw on 16/8/8 20:48
 * 284619466@qq.com
 *
 * 用户个人账号相关api
 */
public interface AccountService {

  /**
   * 用户详情数据
   */
  @GET("api/member/getCardByMid")
  Observable<UserDetailsInfo> getUserInfoById(@Query("mid") int mid);
}
