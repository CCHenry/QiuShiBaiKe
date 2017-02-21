package com.example.henryzheng.qiushibaike.M.utils;

import com.example.henryzheng.qiushibaike.M.bean.image.api.ImageApi;
import com.example.henryzheng.qiushibaike.M.bean.infoComment.api.InfoCommentApi;
import com.example.henryzheng.qiushibaike.M.bean.news.api.NewsApi;
import com.example.henryzheng.qiushibaike.M.bean.test.api.ZhuanXiangApi;
import com.example.henryzheng.qiushibaike.M.bean.text.api.TextApi;
import com.example.henryzheng.qiushibaike.M.bean.video.api.VideoApi;
import com.example.henryzheng.qiushibaike.MyApplication;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
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
            Request request=chain.request();
            //设置缓存
            Response originalResponse = chain.proceed(request);
//            if (NetWorkStateUtil.isNetWorkAvailable(MyApplication.getContext())) {
                int maxAge = 30; // 在线缓存
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();

        }
    };
    private static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR_OFFLINE = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            int maxStale = 60 * 60 * 24 * 28;
            Request request = chain.request();
            //离线的时候为7天的缓存。

            if (!NetWorkStateUtil.isNetWorkAvailable(MyApplication.getContext())) {
                request = request.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale="+maxStale)
                        .build();
                ToastUtil.showNomalText("连接不到网络");
            }
            return chain.proceed(request);

        }
    };
    ZhuanXiangApi zhuanXiangApi;
    VideoApi vedioApi;
    NewsApi newsApi;
    TextApi textApi;
    ImageApi imageApi;
    InfoCommentApi infoCommentApi;
    private static File httpCacheDirectory = new File(MyApplication.getContext().getCacheDir(), "zhihuCache");
    private static int cacheSize = 10 * 1024 * 1024; // 10 MiB
    private static Cache cache = new Cache(httpCacheDirectory, cacheSize);
    private static OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(new LoggingInterceptor())
            .addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
            .addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR_OFFLINE)
            .connectTimeout(60*1000, TimeUnit.MILLISECONDS)
            .cache(cache)
            .build();

    volatile static   ApiManage apiManage;
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
    public VideoApi getVedioApiService() {
        if (vedioApi == null) {
//            synchronized (zhihuMonitor) {
            if (vedioApi == null) {
                vedioApi = new Retrofit.Builder()
                        .baseUrl("http://m2.qiushibaike.com/article/")
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build().create(VideoApi.class);
//                }
            }
        }
        return vedioApi;
    }
    public NewsApi getNewsApiService() {
        if (newsApi == null) {
//            synchronized (zhihuMonitor) {
            if (newsApi == null) {
                newsApi = new Retrofit.Builder()
                        .baseUrl("http://news.qiushibaike.com/")
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build().create(NewsApi.class);
//                }
            }
        }
        return newsApi;
    }
    public TextApi getTextApiService() {
        if (textApi == null) {
//            synchronized (zhihuMonitor) {
            if (textApi == null) {
                textApi = new Retrofit.Builder()
                        .baseUrl("http://m2.qiushibaike.com/article/")
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build().create(TextApi.class);
//                }
            }
        }
        return textApi;
    }
    public ImageApi getImageApiService() {
        if (imageApi == null) {
//            synchronized (zhihuMonitor) {
            if (imageApi == null) {
                imageApi = new Retrofit.Builder()
                        .baseUrl("http://m2.qiushibaike.com/article/")
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build().create(ImageApi.class);
//                }
            }
        }
        return imageApi;
    }
    public InfoCommentApi getInfoCommentApiService() {
        if (infoCommentApi == null) {
//            synchronized (zhihuMonitor) {
            if (infoCommentApi == null) {
                infoCommentApi = new Retrofit.Builder()
                        .baseUrl("http://m2.qiushibaike.com/")
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build().create(InfoCommentApi.class);
//                }
            }
        }
        return infoCommentApi;
    }
}
