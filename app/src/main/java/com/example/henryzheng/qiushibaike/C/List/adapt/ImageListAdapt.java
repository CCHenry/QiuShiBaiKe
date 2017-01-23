package com.example.henryzheng.qiushibaike.C.list.adapt;

import android.content.Context;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.example.henryzheng.qiushibaike.M.bean.image.Items;
import com.example.henryzheng.qiushibaike.R;

/**
 * Created by henryzheng on 2017/1/12.
 */
public class ImageListAdapt extends BaseListAdapt {

    private int width = 0;
    private int height = 0;
    boolean isFirst = false;
    /**
     * @param context  //上下文
     */
    Context context;

    public ImageListAdapt(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void convert(final BaseViewHolder holder, Object bean) {
        final Items data = (Items) bean;
        if (data.getUser() != null) {
            holder.setImageResource(R.id.imageView0, "http:" + data.getUser().getThumb(), true);
            holder.setText(R.id.textView0, data.getUser().getLogin());
        }
        holder.setText(R.id.textView1, data.getContent());
        final ImageView imageView1 = (ImageView) holder.getViewById(R.id.imageView1);

        //第一次进入计算imageView的实际宽度,回调保存之后都不参与计算
        if (!isFirst) {
            measureImageWidth(holder, imageView1, data);
        } else {
            holder.setLayoutParams(R.id.imageView1, width, (int) (width * ((float) data
                    .getImageSize().getM().get(1) / data.getImageSize().getM().get(0))));
            holder.setImageResource(R.id.imageView1, "http:" + data.getHighLoc(), false);
        }
        holder.setText(R.id.textView2,data.getVotes().getUp());
        holder.setText(R.id.textView3, data.getCommentsCount());
        holder.setText(R.id.textView4,data.getShareCount());

    }

    private void measureImageWidth(final BaseViewHolder holder, final ImageView imageView1, final
    Items
            data) {
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

    @Override
    protected int getLayoutItemLayout() {
        return R.layout.recycle_view_image_item;
    }

}
