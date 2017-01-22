package com.example.henryzheng.qiushibaike.C.list.adapt;

import android.content.Context;

import com.example.henryzheng.qiushibaike.M.bean.video.Items;
import com.example.henryzheng.qiushibaike.M.utils.DensityUtils;
import com.example.henryzheng.qiushibaike.R;

/**
 * Created by henryzheng on 2017/1/12.
 */
public class VideoListAdapt extends BaseListAdapt {

    /**
     * @param context //上下文
     */
    public VideoListAdapt(Context context) {
        super(context);
    }

    @Override
    protected void convert(BaseViewHolder holder, Object bean) {
        Items data = (Items) bean;
        if (data.getUser() != null) {
            if(data.getUser().getLogin() != null)
            holder.setText(R.id.textView0, data.getUser().getLogin());

            holder.setImageResource(R.id.imageView0, "http:" + data.getUser().getThumb(), true);
        }
        holder.setText(R.id.textView1, data.getContent());
        int lw = DensityUtils.getSceenWidth(context);
        float vWidth=lw*data.getImage_size().getM().get(0);
        float vHeight=lw*data.getImage_size().getM().get(1);
        int lh= (int) (lw/vWidth*vHeight);
        holder.setLayoutParams(R.id.imageView1,lw,lh);
        holder.setImageResource(R.id.imageView1, data.getPic_url(), false);
        holder.setText(R.id.textView3, String.valueOf(data.getComments_count()));
    }

    @Override
    protected int getLayoutItemLayout() {
        return R.layout.recycle_view_video_item;
    }

}
