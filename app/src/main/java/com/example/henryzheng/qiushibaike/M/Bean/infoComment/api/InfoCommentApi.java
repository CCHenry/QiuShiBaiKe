package com.example.henryzheng.qiushibaike.M.bean.infoComment.api;

import com.example.henryzheng.qiushibaike.M.bean.infoComment.InfoCommentRootBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by henryzheng on 2017/1/20.
 */
public interface InfoCommentApi {
    @GET("article/{id}/latest/comments")
    Observable<InfoCommentRootBean> getLastDaily(@Path("id") String id, @Query("page") int page, @Query("count") int count,
                                                 @Query("rqcnt") int rqcnt) ;

}
