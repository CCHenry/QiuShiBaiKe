package com.example.henryzheng.qiushibaike.C.info.adapt;

import android.content.Context;
import android.view.View;

import com.example.henryzheng.qiushibaike.C.list.adapt.BaseListAdapt;
import com.example.henryzheng.qiushibaike.C.list.adapt.BaseViewHolder;
import com.example.henryzheng.qiushibaike.M.bean.infoComment.Items;
import com.example.henryzheng.qiushibaike.R;

/**
 * Created by henryzheng on 2017/1/22.
 */
public class BaseInfoAdapt extends BaseListAdapt {
    /**
     * @param context //上下文
     */
    public BaseInfoAdapt(Context context) {
        super(context);
    }

    /**
     * convet复写的内容是评论功能
     * @param holder
     * @param bean
     */
    @Override
    protected void convert(BaseViewHolder holder, Object bean) {

        Items data = (Items) bean;
        if (data.getUser() != null)
            if (data.getUser().getLogin() != null) {
                holder.setText(R.id.textView0, data.getUser().getLogin());
                holder.setText(R.id.textView1, data.getLikeCount());
                holder.setText(R.id.textView2, data.getContent());
                holder.setImageResource(R.id.imageView0, "http:" + data.getUser().getThumb(), true);
                if (data.getRefer() != null) {
                    if (data.getRefer().getUser() != null && data.getRefer().getUser().getLogin() !=
                            null) {
                        holder.setText(R.id.textView3, data.getRefer().getUser().getLogin());
                        holder.setText(R.id.textView4, data.getRefer().getContent());
                    }
                } else {
                    holder.setVisability(R.id.linearLayout0, View.GONE);
                }
            }
    }

    @Override
    protected int getLayoutItemLayout() {
        return R.layout.recycle_view_info_comment_item;
    }


}
