package com.example.henryzheng.qiushibaike.C.list.i;

import java.util.List;

/**
 * Created by henryzheng on 2016/12/20.
 */
public interface MainFragmentInterface<T> {
    public  void loadNewData(List<T> datas);
    public  void refreshData(List<T>  datas);
}
