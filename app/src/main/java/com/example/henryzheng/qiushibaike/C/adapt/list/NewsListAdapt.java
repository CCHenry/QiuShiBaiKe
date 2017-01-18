package com.example.henryzheng.qiushibaike.C.adapt.list;

import android.content.Context;
import android.view.View;

import com.example.henryzheng.qiushibaike.M.Bean.news.Data;
import com.example.henryzheng.qiushibaike.M.utils.DensityUtils;
import com.example.henryzheng.qiushibaike.R;

/**
 * Created by henryzheng on 2017/1/12.
 */
public class NewsListAdapt extends BaseListAdapt {

    private  int width=0;
    private  int height=0;

    /**
     * @param context  //上下文
     */
    Context context;

    public NewsListAdapt(Context context) {
        super(context);
        this.context=context;
         width=(int) (DensityUtils.geSceenWidth(context)/3-context.getResources().getDimension(R.dimen.margin1)*2);
         height= (int) (DensityUtils.geSceenWidth(context)/3*0.6-context.getResources().getDimension(R.dimen.margin1)*2);
    }

    @Override
    protected void convert(BaseViewHolder holder, Object bean) {
        Data data= (Data) bean;
        holder.setText(R.id.textView0, data.getTitle());


        if (data.getCovers().size()<3&&data.getCovers().size()>0){
            holder.setVisability(R.id.imageView0, View.VISIBLE);
            holder.setVisability(R.id.relativeLayout0, View.GONE);

            holder.setLayoutParams(R.id.imageView0,width,height);
            holder.setImageResource(R.id.imageView0,data.getCovers().get(0),false);
        }else if (data.getCovers().size()>=3)
        {
            holder.setVisability(R.id.imageView0, View.GONE);
            holder.setVisability(R.id.relativeLayout0, View.VISIBLE);

            holder.setLayoutParams(R.id.imageView1,width,height);
            holder.setLayoutParams(R.id.imageView2,width,height);
            holder.setLayoutParams(R.id.imageView3,width,height);

            holder.setImageResource(R.id.imageView1,data.getCovers().get(0),false);
            holder.setImageResource(R.id.imageView2,data.getCovers().get(1),false);
            holder.setImageResource(R.id.imageView3,data.getCovers().get(2),false);

        }else if (data.getCovers().size()==0)
        {  holder.setVisability(R.id.imageView0, View.GONE);
            holder.setVisability(R.id.relativeLayout0, View.GONE);
        }
//        holder.setText(R.id.textView1, data.getContent());
//        holder.setImageResource(R.id.imageView0,"http:"+data.getUser().getThumb(),true);
//        holder.setImageResource(R.id.imageView1,data.getPic_url(),false);
    }

    @Override
    protected int getLayoutItemLayout() {
        return R.layout.recycle_view_news_item;
    }

}
