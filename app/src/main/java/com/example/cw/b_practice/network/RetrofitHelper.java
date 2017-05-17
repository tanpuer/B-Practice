package com.example.cw.b_practice.network;

import com.example.cw.b_practice.MyApplication;
import com.example.cw.b_practice.network.api.AccountService;
import com.example.cw.b_practice.network.api.BangumiService;
import com.example.cw.b_practice.network.api.BiliApiService;
import com.example.cw.b_practice.network.api.BiliAppService;
import com.example.cw.b_practice.network.api.BiliGoService;
import com.example.cw.b_practice.network.api.Im9Service;
import com.example.cw.b_practice.network.api.LiveService;
import com.example.cw.b_practice.network.api.RankService;
import com.example.cw.b_practice.network.api.SearchService;
import com.example.cw.b_practice.network.api.UserService;
import com.example.cw.b_practice.network.api.VipService;
import com.example.cw.b_practice.network.auxiliary.ApiConstants;
import com.example.cw.b_practice.util.CommonUtil;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by cw on 2017/5/15.
 */

public class RetrofitHelper {

    private static OkHttpClient mOkHttpClient;

    static {
        initOkHttpClient();
    }

    private static void initOkHttpClient() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        if (mOkHttpClient == null){
            synchronized (RetrofitHelper.class){
                Cache cache = new Cache(new File(MyApplication.getInstance().getCacheDir(), "http_cache"),
                        10*1024*1024);
                mOkHttpClient = new OkHttpClient.Builder()
                        .cache(cache)
                        .addInterceptor(httpLoggingInterceptor)
                        .addNetworkInterceptor(new CacheInterceptor())
                        .addNetworkInterceptor(new StethoInterceptor())
                        .retryOnConnectionFailure(false)
                        .connectTimeout(30, TimeUnit.SECONDS)
                        .writeTimeout(20, TimeUnit.SECONDS)
                        .readTimeout(20, TimeUnit.SECONDS)
                        .addInterceptor(new UserAgentInterceptor())
                        .build();
            }
        }
    }

    private static <T> T createApi(Class<T> clazz, String baseUrl){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(clazz);
    }

    public static LiveService getLiveAPI() {

        return createApi(LiveService.class, ApiConstants.LIVE_BASE_URL);
    }


    public static BiliAppService getBiliAppAPI() {

        return createApi(BiliAppService.class, ApiConstants.APP_BASE_URL);
    }


    public static BiliApiService getBiliAPI() {

        return createApi(BiliApiService.class, ApiConstants.API_BASE_URL);
    }


    public static BiliGoService getBiliGoAPI() {

        return createApi(BiliGoService.class, ApiConstants.BILI_GO_BASE_URL);
    }


    public static RankService getRankAPI() {

        return createApi(RankService.class, ApiConstants.RANK_BASE_URL);
    }


    public static UserService getUserAPI() {

        return createApi(UserService.class, ApiConstants.USER_BASE_URL);
    }


    public static VipService getVipAPI() {

        return createApi(VipService.class, ApiConstants.VIP_BASE_URL);
    }


    public static BangumiService getBangumiAPI() {

        return createApi(BangumiService.class, ApiConstants.BANGUMI_BASE_URL);
    }


    public static SearchService getSearchAPI() {

        return createApi(SearchService.class, ApiConstants.SEARCH_BASE_URL);
    }


    public static AccountService getAccountAPI() {

        return createApi(AccountService.class, ApiConstants.ACCOUNT_BASE_URL);
    }


    public static Im9Service getIm9API() {

        return createApi(Im9Service.class, ApiConstants.IM9_BASE_URL);
    }

    /**
     * okhttp 缓存配置
     */
    private static class CacheInterceptor implements Interceptor{

        @Override
        public Response intercept(Chain chain) throws IOException {
            //有网络时，设置缓存超时时间一小时
            int maxAge = 60 * 60;
            //无网络时，设置缓存超时时间一天
            int maxStale = 24 * maxAge;
            Request request = chain.request();
            if (CommonUtil.isNetWorkAvailable(MyApplication.getInstance())){
                //有网络时，只从网络获取
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_NETWORK)
                        .build();
            }else {
                //没有网络时，从缓存获取
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }
            Response response = chain.proceed(request);
            if (CommonUtil.isNetWorkAvailable(MyApplication.getInstance())){
                response = response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();
            }else {
                response = response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
            return response;
        }
    }


    /**
     * okhttp UA拦截器
     */
    private static class UserAgentInterceptor implements Interceptor{
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            request = request.newBuilder()
                    .removeHeader("User-Agent")
                    .addHeader("User-Agent", ApiConstants.COMMON_UA_STR)
                    .build();
            return chain.proceed(request);
        }
    }
}
