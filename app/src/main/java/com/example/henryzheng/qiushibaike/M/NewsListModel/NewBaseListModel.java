package com.example.henryzheng.qiushibaike.M.NewsListModel;

import java.io.Serializable;

/**
 * Created by henryzheng on 2017/1/12.
 */
public abstract class NewBaseListModel implements Serializable {

    public void setUrl(String url){}
    public abstract  String getUrl();

    public   String getType(){
        return getClass().getName();
    }
}
