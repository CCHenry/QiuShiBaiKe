package com.example.henryzheng.qiushibaike.C.adapt.list;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.henryzheng.qiushibaike.R;

/**
 * Created by henryzheng on 2017/1/18.
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {
    View convertView;
    Context context;

    public BaseViewHolder(Context context, int itemType, View itemView) {
        super(itemView);
        this.convertView = itemView;
        this.context = context;
    }

    public void setText(int id, String text) {
        TextView tx = (TextView) convertView.findViewById(id);
        tx.setText(text);
    }

    public void setLayoutParams(int id, Integer width, Integer height) {
        final View view = convertView.findViewById(id);

        if (width != null) {
            view.getLayoutParams().width = width;
        }
        if (height != null) {
            view.getLayoutParams().height = height;
        }
        view.requestLayout();
    }

    public void setImageResource(int id, String url, boolean isCircle) {
        final ImageView iv = (ImageView) convertView.findViewById(id);
        if (isCircle) {
            Glide.with(context).load(url).asBitmap().fitCenter().into(new BitmapImageViewTarget
                    (iv) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    iv.setImageDrawable(circularBitmapDrawable);
                }
            });
        } else {
            Glide.with(context).load(url).animate(R.anim.item_alpha_in).crossFade
                    (1000).into(iv);
        }
    }

    public void setVisability(int id, int visability) {
        final View view = convertView.findViewById(id);
        view.setVisibility(visability);
    }

}