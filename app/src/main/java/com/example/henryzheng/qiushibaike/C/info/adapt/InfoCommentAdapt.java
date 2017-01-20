package com.example.henryzheng.qiushibaike.C.info.adapt;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.example.henryzheng.qiushibaike.C.List.adapt.BaseListAdapt;
import com.example.henryzheng.qiushibaike.C.List.adapt.BaseViewHolder;
import com.example.henryzheng.qiushibaike.M.Bean.infoComment.Items;
import com.example.henryzheng.qiushibaike.R;

/**
 * Created by henryzheng on 2017/1/20.
 */
public class InfoCommentAdapt extends BaseListAdapt {
    /**
     * @param context //上下文
     */
    public InfoCommentAdapt(Context context) {
        super(context);
    }

    @Override
    protected void convert(BaseViewHolder holder, Object bean) {
        Items data = (Items) bean;
        if (data.getUser() != null)
            if (data.getUser().getLogin() != null) {
                holder.setText(R.id.textView0, data.getUser().getLogin());
            }
    }

    @Override
    protected int getLayoutItemLayout() {
        return R.layout.recycle_view_info_comment_item;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

//    @Override
//    public int getHeadViewById() {
//        return R.layout.activity_video_info;
//
//    }
//
//    @Override
//    public void convertByHead(BaseViewHolder holder) {
//        super.convertByHead(holder);
//
//    }
}
