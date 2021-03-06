package com.example.henryzheng.qiushibaike.M.bean.video.api;

import com.example.henryzheng.qiushibaike.M.bean.video.VideoRootBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by henryzheng on 2017/1/12.
 */
public interface VideoApi {
    @GET("list/video")
    Observable<VideoRootBean> getLastDaily(@Query("page") int page, @Query("count") int count,
                                           @Query("rqcnt") int rqcnt) ;


}
