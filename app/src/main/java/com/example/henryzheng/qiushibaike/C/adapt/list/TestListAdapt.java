package com.example.henryzheng.qiushibaike.C.adapt.list;

import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.henryzheng.qiushibaike.C.myInterface.MyItemClickListener;
import com.example.henryzheng.qiushibaike.M.Bean.video.Items;
import com.example.henryzheng.qiushibaike.M.utils.DensityUtils;
import com.example.henryzheng.qiushibaike.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by henryzheng on 2017/1/12.
 */
public class TestListAdapt extends BaseListAdapt<TestListAdapt.MyViewHolder> {
    /**
     * adapt创建
     *
     * @param parent
     * @param viewType
     * @return
     */
    List<Items> datas;
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HEAD_TYPE) {
            View view = _mLayoutInflater.inflate(R.layout.layout_recycle_head, parent, false);
            MyViewHolder holder = new MyViewHolder(HEAD_TYPE, view, myItemClickListener);
            return holder;
        } else if (viewType == FOOT_TYPE) {
            View view = _mLayoutInflater.inflate(R.layout.layout_recycle_foot, parent, false);
            MyViewHolder holder = new MyViewHolder(FOOT_TYPE, view, myItemClickListener);
            return holder;
        } else {
            View view = _mLayoutInflater.inflate(R.layout.recycle_view_video_item, parent, false);
            MyViewHolder holder = new MyViewHolder(DATA_TYPE, view, myItemClickListener);
            return holder;
        }
    }
    /**
     * 数据服用Handler
     */
    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        MyItemClickListener _mItemClickListener;
        @BindView(R.id.textView0)
        TextView textView0;
        @BindView(R.id.textView1)
        TextView textView1;
        @BindView(R.id.imageView0)
        ImageView imageView0;
        @BindView(R.id.imageView1)
        ImageView imageView1;
        public MyViewHolder(int viewType, final View view, MyItemClickListener _mItemClickListener) {
            super(view);
            if (viewType == HEAD_TYPE) {

            } else if (viewType == FOOT_TYPE) {

            } else {
                ButterKnife.bind(this,view);

                view.setOnClickListener(this);
//                textView0= (TextView) view.findViewById(R.id.textView0);

                this._mItemClickListener = _mItemClickListener;
            }

        }

        @Override
        public void onClick(View v) {
            _mItemClickListener.onItemClick(v, getPosition());
        }
    }
    /**
     * 数据绑定
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        //下载图片和展示
        if (getItemCount() > 0) {
            if (position > 0 &&position <getItemCount()-1) {

                holder.textView0.setText(datas.get(position-1).getUser().getLogin());
                holder.textView1.setText(datas.get(position-1).getContent());
                int width= DensityUtils.geSceenWidth(_context);
                holder.imageView1.getLayoutParams().width=width;
                holder.imageView1.getLayoutParams().height= width;
                holder.imageView1.requestLayout();

                Glide.with(_context).load(datas.get(position-1).getPic_url()).animate(R.anim.item_alpha_in).centerCrop().crossFade(1000).into(holder.imageView1);

                Glide.with(_context).load("http:"+datas.get(position-1).getUser().getMedium()).asBitmap().centerCrop().into(new BitmapImageViewTarget(holder.imageView0) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(_context.getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        holder.imageView0.setImageDrawable(circularBitmapDrawable);
                    }
                });

            }
        }
    }

}
