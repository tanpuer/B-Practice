package com.example.cw.b_practice.network.api;


import com.example.cw.b_practice.entity.discover.HotSearchTag;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by cw on 2016/10/3 11:53
 * 284619466@qq.com
 * <p>
 * 搜索相关api
 */

public interface SearchService {

  /**
   * 首页发现热搜词
   */
  @GET(
      "main/hotword?access_key=ec0f54fc369d8c104ee1068672975d6a&actionKey=appkey&appkey=27eb53fc9058f8c3")
  Observable<HotSearchTag> getHotSearchTags();
}
