package com.example.henryzheng.qiushibaike.C.list.adapt;

import android.content.Context;

import com.example.henryzheng.qiushibaike.M.bean.text.Items;
import com.example.henryzheng.qiushibaike.R;

/**
 * Created by henryzheng on 2017/1/12.
 */
public class TextListAdapt extends BaseListAdapt {

    /**
     * @param context //上下文
     */
    public TextListAdapt(Context context) {
        super(context);
    }

    @Override
    protected void convert(BaseViewHolder holder, Object bean) {
        Items data = (Items) bean;
        if (data.getUser() != null)
            if (data.getUser().getLogin() != null) {
                holder.setText(R.id.textView0, data.getUser().getLogin());
                holder.setImageResource(R.id.imageView0, "http:" + data.getUser().getThumb(), true);
            }
        holder.setText(R.id.textView1, data.getContent());
        holder.setText(R.id.textView2,data.getVotes().getUp());
        holder.setText(R.id.textView3, data.getCommentsCount());
        holder.setText(R.id.textView4,data.getShareCount());
    }

    @Override
    protected int getLayoutItemLayout() {
        return R.layout.recycle_view_text_item;
    }

}
