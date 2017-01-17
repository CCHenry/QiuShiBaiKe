package com.example.henryzheng.qiushibaike.M.NewsListModel;

/**
 * Created by henryzheng on 2017/1/12.
 */
public class VedioListModel extends NewBaseListModel{
    @Override
    public String getUrl() {
        return "video?page=1&count=30&rqcnt=25";
    }
}
