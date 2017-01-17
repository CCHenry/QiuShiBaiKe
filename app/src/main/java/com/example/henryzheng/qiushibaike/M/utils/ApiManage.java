package com.example.henryzheng.qiushibaike.M.utils;

import com.example.henryzheng.qiushibaike.M.Bean.ZhuanXiang.api.ZhuanXiangApi;
import com.example.henryzheng.qiushibaike.M.Bean.video.api.VedioApi;
import com.example.henryzheng.qiushibaike.MyApplication;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by henryzheng on 2017/1/12.
 */
public class ApiManage {

    private static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Response originalResponse = chain.proceed(chain.request());
            if (NetWorkStateUtil.isNetWorkAvailable(MyApplication.getContext())) {
                int maxAge = 60; // 在线缓存在1分钟内可读取
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();
            } else {
                int maxStale = 60 * 60 * 24 * 28; // 离线时缓存保存4周
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
        }
    };
    ZhuanXiangApi zhuanXiangApi;
    VedioApi vedioApi;
    private static File httpCacheDirectory = new File(MyApplication.getContext().getCacheDir(), "zhihuCache");
    private static int cacheSize = 10 * 1024 * 1024; // 10 MiB
    private static Cache cache = new Cache(httpCacheDirectory, cacheSize);
    private static OkHttpClient client = new OkHttpClient.Builder()
            .addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
            .addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
            .cache(cache)
            .build();

    static ApiManage apiManage;
    public static ApiManage getInstence() {
        if (apiManage == null) {
            synchronized (ApiManage.class) {
                if (apiManage == null) {
                    apiManage = new ApiManage();
                }
            }
        }
        return apiManage;
    }
    public ZhuanXiangApi getTopNewsService() {
        if (zhuanXiangApi == null) {
//            synchronized (zhihuMonitor) {
                if (zhuanXiangApi == null) {
                    zhuanXiangApi = new Retrofit.Builder()
                            .baseUrl("http://m2.qiushibaike.com/article/")
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .client(client)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build().create(ZhuanXiangApi.class);
//                }
            }
        }

        return zhuanXiangApi;
    }
    public VedioApi getVedioApiService() {
        if (vedioApi == null) {
//            synchronized (zhihuMonitor) {
            if (vedioApi == null) {
                vedioApi = new Retrofit.Builder()
                        .baseUrl("http://m2.qiushibaike.com/article/")
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build().create(VedioApi.class);
//                }
            }
        }
        return vedioApi;
    }
}
