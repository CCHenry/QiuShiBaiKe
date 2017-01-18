package com.example.henryzheng.qiushibaike.M.Bean.image.api;

import com.example.henryzheng.qiushibaike.M.Bean.image.ImageRootBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by henryzheng on 2017/1/12.
 */
public interface ImageApi {
    @GET("list/imgrank")
    Observable<ImageRootBean> getLastDaily(@Query("page") int page, @Query("count") int count,
                                           @Query("rqcnt") int rqcnt) ;


}
