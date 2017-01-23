package com.example.henryzheng.qiushibaike.C.info.adapt;

import android.content.Context;

import com.example.henryzheng.qiushibaike.C.list.adapt.BaseViewHolder;
import com.example.henryzheng.qiushibaike.M.bean.text.Items;
import com.example.henryzheng.qiushibaike.R;

/**
 * Created by henryzheng on 2017/1/22.
 */
public class TextInfoAdapt extends BaseInfoAdapt {


    private Items headBean;

    /**
     * @param context //上下文
     */
    public TextInfoAdapt(Context context,Items headBean) {
        super(context);
        this.headBean=headBean;
    }

    @Override
    public int getHeadViewById() {
        return R.layout.activity_text_info;
    }

    @Override
    public void convertByHead(BaseViewHolder holder) {
        super.convertByHead(holder);
        Items data = (Items) headBean;
        if (data.getUser() != null)
            if (data.getUser().getLogin() != null) {
                holder.setText(R.id.textView0, data.getUser().getLogin());
                holder.setImageResource(R.id.imageView0, "http:" + data.getUser().getThumb(), true);

            }
        holder.setText(R.id.textView1, data.getContent());
    }
}
