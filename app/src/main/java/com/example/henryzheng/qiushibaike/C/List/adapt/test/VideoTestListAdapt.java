package com.example.henryzheng.qiushibaike.C.List.adapt.test;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.henryzheng.qiushibaike.C.i.MyItemClickListener;
import com.example.henryzheng.qiushibaike.M.Bean.video.Items;
import com.example.henryzheng.qiushibaike.M.Bean.video.VideoRootBean;
import com.example.henryzheng.qiushibaike.M.utils.CCLog;
import com.example.henryzheng.qiushibaike.M.utils.DensityUtils;
import com.example.henryzheng.qiushibaike.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by henryzheng on 2017/1/12.
 */
public class VideoTestListAdapt extends RecyclerView.Adapter<VideoTestListAdapt.MyViewHolder> {
    private static final int HEAD_TYPE = 0;
    private static final int DATA_TYPE = 1;
    private static final int FOOT_TYPE = 2;

    Context _context;
    List<Items> datas;
    LayoutInflater _mLayoutInflater;
    private MyItemClickListener myItemClickListener;

    public VideoTestListAdapt(Context context) {
        _context = context;
        _mLayoutInflater = LayoutInflater.from(context);
        datas = new ArrayList<>();
        //设置imageload的加载属性
    }

    /**
     *
     *
     * @param datas
     */
    private void addSrc(VideoRootBean datas) {
        for (int i = 0; i < datas.getItems().size(); i++) {
            this.datas.add(datas.getItems().get(i));
        }

    }

    /**
     * adapt创建
     *
     * @param parent
     * @param viewType
     * @return
     */
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
                int width=DensityUtils.getSceenWidth(_context);
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

    @Override
    public int getItemCount() {
        if (datas.size()>0){
            return datas.size() + 2;}
        else
            return 0;
    }




    /**
     *
     * @param datas
     */
    public void loadMoreData(Object datas) {
        addSrc((VideoRootBean) datas);
        notifyDataSetChanged();
        CCLog.print("loadMoreData:" + this.datas.size());
    }

    /**
     * 下拉刷新图片
     *
     * @param datas 图片url的集合
     */
    public void refreshData(Object datas) {
        this.datas.clear();
        notifyDataSetChanged();
        for (int i=((VideoRootBean)datas).getItems().size()-1;i>=0;i--){
            this.datas.add(0,((VideoRootBean)datas).getItems().get(i));
            notifyItemInserted(0);
        }
    }


    public void setOnItemClickListener(MyItemClickListener listener) {
        this.myItemClickListener = listener;
    }

    /**
     * 清除url的缓存
     */
    public void clear() {
        datas.clear();
    }

    /**
     * 返回url的缓存列表
     *
     * @return
     */
    public List<Items> getImages() {
        return datas;
    }


    @Override
    public long getItemId(int position) {
        CCLog.print("getItemId:" + position);
        return super.getItemId(position);
    }

    @Override
    public int getItemViewType(int position) {
        if (getItemCount() > 0) {
            if (position == 0) {
                return HEAD_TYPE;
            } else if (position == getItemCount()-1) {
                return FOOT_TYPE;
            } else {
                return DATA_TYPE;
            }
        } else {
            return DATA_TYPE;
        }
    }
}
