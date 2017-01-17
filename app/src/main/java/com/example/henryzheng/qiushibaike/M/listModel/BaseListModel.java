package com.example.henryzheng.qiushibaike.M.listModel;

import java.io.Serializable;

/**
 * Created by henryzheng on 2017/1/12.
 */
public abstract class BaseListModel implements Serializable {

    public void setUrl(String url){}
    public abstract  String getUrl();

    public   String getType(){
        return getClass().getName();
    }
}
