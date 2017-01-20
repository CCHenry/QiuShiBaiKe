package com.example.henryzheng.qiushibaike.M.Bean.test.api;

import com.example.henryzheng.qiushibaike.M.Bean.test.RootListBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by henryzheng on 2017/1/12.
 */
public interface ZhuanXiangApi {
    @GET("list/suggest")
    Observable<RootListBean> getLastDaily(@Query("page") int page, @Query("count") int count, @Query("rqcnt") int rqcnt) ;


}
