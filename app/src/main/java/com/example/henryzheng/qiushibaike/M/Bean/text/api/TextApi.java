package com.example.henryzheng.qiushibaike.M.Bean.text.api;

import com.example.henryzheng.qiushibaike.M.Bean.text.TextRootBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by henryzheng on 2017/1/12.
 */
public interface TextApi {
    @GET("list/text")
    Observable<TextRootBean> getLastDaily(@Query("page") int page, @Query("count") int count,
                                          @Query("rqcnt") int rqcnt) ;


}
