package com.example.henryzheng.qiushibaike.C.adapt.list;

import android.content.Context;

import com.example.henryzheng.qiushibaike.M.Bean.image.Items;
import com.example.henryzheng.qiushibaike.M.utils.DensityUtils;
import com.example.henryzheng.qiushibaike.R;

/**
 * Created by henryzheng on 2017/1/12.
 */
public class ImageListAdapt extends BaseListAdapt {

    private  int width=0;
    private  int height=0;

    /**
     * @param context  //上下文
     */
    Context context;

    public ImageListAdapt(Context context) {
        super(context);
        this.context=context;
         width=(int) (DensityUtils.geSceenWidth(context));
         height= (int) (DensityUtils.geSceenHeight(context));
    }

    @Override
    protected void convert(BaseViewHolder holder, Object bean) {
        Items data= (Items) bean;
        if (data.getUser()!=null){
            holder.setImageResource(R.id.imageView0,"http:"+data.getUser().getThumb(),true);
            holder.setText(R.id.textView0, data.getUser().getLogin());
        }
        holder.setText(R.id.textView1, data.getContent());
        holder.setLayoutParams(R.id.imageView1,width, (int) (width*((float)data.getImageSize().getM().get(1)/data.getImageSize().getM().get(0))));
        holder.setImageResource(R.id.imageView1,"http:"+data.getHighLoc(),false);
    }

    @Override
    protected int getLayoutItemLayout() {
        return R.layout.recycle_view_image_item;
    }

}
