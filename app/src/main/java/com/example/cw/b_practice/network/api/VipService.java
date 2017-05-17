package com.example.cw.b_practice.network.api;

import com.example.cw.b_practice.entity.discover.VipGameInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by cw on 2016/10/31 23:27
 * 284619466@qq.com
 * <p>
 * 大会员相关api
 */

public interface VipService {

  @GET(
      "api/v1/games/gift?access_key=19946e1ef3b5cad1a756c475a67185bb&actionKey=appkey&appkey=27eb53fc9058f8c3&build=3940&device=phone&mobi_app=iphone&platform=ios&sign=f6a995f30f3d4362a628191d523e3012&ts=1477922329")
  Observable<VipGameInfo> getVipGame();
}
