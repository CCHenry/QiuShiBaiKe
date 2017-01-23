package com.example.henryzheng.qiushibaike.C.info.adapt;

import android.content.Context;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.example.henryzheng.qiushibaike.C.list.adapt.BaseViewHolder;
import com.example.henryzheng.qiushibaike.M.bean.image.Items;
import com.example.henryzheng.qiushibaike.R;

/**
 * Created by henryzheng on 2017/1/22.
 */
public class ImageInfoAdapt extends BaseInfoAdapt {

    private final Items headItemData;
    private boolean isFirst=false;
    private Integer width=0;

    /**
     * @param context //上下文
     */
    public ImageInfoAdapt(Context context, Items headItemData) {
        super(context);
        this.headItemData=headItemData;
    }
    @Override
    public void convertByFloor(BaseViewHolder holder) {
        super.convertByFloor(holder);
        holder.setVisability(R.id.relativeLayout0, View.GONE);
    }

    @Override
    public int getHeadViewById() {
        return R.layout.activity_image_info;
    }
    @Override
    public void convertByHead(BaseViewHolder holder) {
        super.convertByHead(holder);
        Items data = headItemData;
        if (data.getUser() != null)
            if (data.getUser().getLogin() != null) {
                holder.setText(R.id.textView0, data.getUser().getLogin());
                holder.setImageResource(R.id.imageView0, "http:" + data.getUser().getThumb(), true);

            }
        final ImageView imageView1 = (ImageView) holder.getViewById(R.id.imageView1);

        //第一次进入计算imageView的实际宽度,回调保存之后都不参与计算
        if (!isFirst) {
            measureImageWidth(holder, imageView1, data);
        } else {
            holder.setLayoutParams(R.id.imageView1, width, (int) (width * ((float) data
                    .getImageSize().getM().get(1) / data.getImageSize().getM().get(0))));
            holder.setImageResource(R.id.imageView1, "http:" + data.getHighLoc(), false);
        }
        holder.setText(R.id.textView1, data.getContent());
        holder.setText(R.id.textView5, data.getCommentsCount());
        holder.setText(R.id.textView2,data.getVotes().getUp());
        holder.setText(R.id.textView3, data.getCommentsCount());
        holder.setText(R.id.textView4,data.getShareCount());

    }

    private void measureImageWidth(final BaseViewHolder holder, final ImageView imageView1, final Items data) {
        ViewTreeObserver vto = imageView1.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                imageView1.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                imageView1.getHeight();
                width = imageView1.getWidth();
                holder.setLayoutParams(R.id.imageView1, width, (int) (width * ((float) data
                        .getImageSize().getM().get(1) / data.getImageSize().getM().get(0))));
                holder.setImageResource(R.id.imageView1, "http:" + data.getHighLoc(), false);
                isFirst = true;
            }
        });
    }


}
