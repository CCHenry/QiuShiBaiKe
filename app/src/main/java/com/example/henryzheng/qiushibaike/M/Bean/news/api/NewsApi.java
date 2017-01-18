package com.example.henryzheng.qiushibaike.M.Bean.news.api;

import com.example.henryzheng.qiushibaike.M.Bean.news.NewsRootBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by henryzheng on 2017/1/12.
 */
public interface NewsApi {
    @GET("article/list")
    Observable<NewsRootBean> getLastDaily(@Query("page") int page, @Query("count") int count,
                                          @Query("rqcnt") int rqcnt) ;


}
