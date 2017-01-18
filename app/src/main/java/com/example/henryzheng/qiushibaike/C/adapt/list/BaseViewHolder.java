package com.example.henryzheng.qiushibaike.C.adapt.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by henryzheng on 2017/1/18.
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {
    View convertView;
    Context context;

    public BaseViewHolder(Context context, int itemType,View itemView) {
        super(itemView);
        this.convertView = itemView;
        this.context = context;
    }

    public void setText(int id, String text) {
        TextView tx = (TextView) convertView.findViewById(id);
        tx.setText(text);
    }

    public void setImageResource(int id, int resouceId) {
        ImageView img= (ImageView) convertView.findViewById(id);
        img.setImageResource(resouceId);
    }

}