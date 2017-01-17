package com.example.henryzheng.qiushibaike.C.adapt.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.henryzheng.qiushibaike.C.myInterface.MyItemClickListener;

/**
 * Created by henryzheng on 2017/1/12.
 */
public  class BaseListAdapt<M> extends  RecyclerView.Adapter {
    static final int HEAD_TYPE = 0;
    static final int DATA_TYPE = 1;
    static final int FOOT_TYPE = 2;
    Context _context;
    LayoutInflater _mLayoutInflater;
    protected MyItemClickListener myItemClickListener;



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
convert(holder,position);
    }

    private void convert(RecyclerView.ViewHolder holder, int position) {
    }

    @Override
    public int getItemViewType(int position) {
        if (getItemCount() > 0) {
            if (position == 0) {
                return HEAD_TYPE;
            } else if (position == getItemCount() - 1) {
                return FOOT_TYPE;
            } else {
                return DATA_TYPE;
            }
        } else {
            return DATA_TYPE;
        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }




}
