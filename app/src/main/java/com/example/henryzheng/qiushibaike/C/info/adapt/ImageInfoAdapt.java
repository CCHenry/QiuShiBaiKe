package com.example.henryzheng.qiushibaike.C.info.adapt;

import android.content.Context;
import android.view.View;

import com.example.henryzheng.qiushibaike.C.list.adapt.BaseViewHolder;
import com.example.henryzheng.qiushibaike.M.bean.image.Items;
import com.example.henryzheng.qiushibaike.R;

/**
 * Created by henryzheng on 2017/1/22.
 */
public class ImageInfoAdapt extends BaseInfoAdapt {

    private final Items headItemData;

    /**
     * @param context //上下文
     */
    public ImageInfoAdapt(Context context, Items headItemData) {
        super(context);
        this.headItemData=headItemData;
    }
    @Override
    public void convertByBottom(BaseViewHolder holder) {
        super.convertByBottom(holder);
        holder.setVisability(R.id.relativeLayout0, View.GONE);
    }

    @Override
    public int getHeadViewById() {
        return R.layout.activity_video_info_head;
    }
    @Override
    public void convertByHead(BaseViewHolder holder) {
        super.convertByHead(holder);
//        holder

    }


}
