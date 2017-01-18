package com.example.henryzheng.qiushibaike.C.List.i;

import java.util.List;

/**
 * Created by henryzheng on 2016/12/20.
 */
public interface MainFragmentInterface<T> {
    public  void loadNewImages(List<T> datas);
    public  void refreshImages(List<T>  datas);
}
