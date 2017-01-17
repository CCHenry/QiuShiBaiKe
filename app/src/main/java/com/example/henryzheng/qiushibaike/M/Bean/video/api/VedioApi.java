package com.example.henryzheng.qiushibaike.M.Bean.video.api;

import com.example.henryzheng.qiushibaike.M.Bean.video.VideoRootBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by henryzheng on 2017/1/12.
 */
public interface VedioApi {
    @GET("list/video")
    Observable<VideoRootBean> getLastDaily(@Query("page") int page, @Query("count") int count,
                                           @Query("rqcnt") int rqcnt) ;


}
