package com.example.henryzheng.qiushibaike.C.adapt.list;

import android.content.Context;

import com.example.henryzheng.qiushibaike.M.Bean.video.Items;
import com.example.henryzheng.qiushibaike.R;

/**
 * Created by henryzheng on 2017/1/12.
 */
public class VideoListAdapt extends BaseListAdapt {

    /**
     * @param context  //上下文
     */
    public VideoListAdapt(Context context) {
        super(context);
    }

    @Override
    protected void convert(BaseViewHolder holder, Object bean) {
        Items data= (Items) bean;
        holder.setText(R.id.textView0, data.getUser().getLogin());
        holder.setText(R.id.textView1, data.getContent());
        holder.setImageResource(R.id.imageView0,"http:"+data.getUser().getThumb(),true);
        holder.setImageResource(R.id.imageView1,data.getPic_url(),false);
    }

    @Override
    protected int getLayoutItemLayout() {
        return R.layout.recycle_view_video_item;
    }

}
