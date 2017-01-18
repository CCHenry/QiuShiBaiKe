package com.example.henryzheng.qiushibaike.C.adapt.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Created by henryzheng on 2017/1/12.
 */
public class TestListAdapt extends BaseListAdapt {


    /**
     * @param context  //上下文
     * @param layoutId //布局id
     * @param data     //数据源
     */
    public TestListAdapt(Context context, int layoutId, List data) {
        super(context, layoutId, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, Object bean) {
        
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }
}
