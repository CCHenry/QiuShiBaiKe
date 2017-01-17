package com.example.henryzheng.qiushibaike.C.adapt.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.henryzheng.qiushibaike.C.myInterface.MyItemClickListener;
import com.example.henryzheng.qiushibaike.M.Bean.ZhuanXiang.Items;
import com.example.henryzheng.qiushibaike.M.utils.CCLog;
import com.example.henryzheng.qiushibaike.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by henryzheng on 2017/1/12.
 */
public class VideoListAdapt extends RecyclerView.Adapter<VideoListAdapt.MyViewHolder> {
    private static final int HEAD_TYPE = 0;
    private static final int DATA_TYPE = 1;
    private static final int FOOT_TYPE = 2;

    Context _context;
    List<Items> datas;
    LayoutInflater _mLayoutInflater;
    private MyItemClickListener myItemClickListener;

    public VideoListAdapt(Context context) {
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
    private void addSrc(List<Items> datas) {
        for (int i = 0; i < datas.size(); i++) {
            this.datas.add(datas.get(i));
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
        TextView textView0;
        public MyViewHolder(int viewType, final View view, MyItemClickListener _mItemClickListener) {
            super(view);
            if (viewType == HEAD_TYPE) {

            } else if (viewType == FOOT_TYPE) {

            } else {
                view.setOnClickListener(this);
                textView0= (TextView) view.findViewById(R.id.textView0);

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
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        //下载图片和展示
        if (getItemCount() > 0) {
            if (position > 0 &&position <getItemCount()-1) {
                holder.textView0.setText(datas.get(position-1).getContent());

//                x.image().bind(holder.iv, da.get(position - 1).getImage_url(), _imageOptions, new CustomBitmap  LoadCallBack(holder));
//                int width = ((BaseActivity) _context).getWidth();
//                RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(width, width * 3 / 4);
//                holder.iv.setLayoutParams(params);
//                holder.iv1.setLayoutParams(params);
//                Image image=images.get(position-1);
//                holder.tv3.setText(image.getDescription());
//                holder.tv4.setText(image.getUp_times()+"");
//
//                if (image.getPub_time()!=null) {
//                    if (image.getPub_time().contains("-")) {
//                        String[] arr = DateUtil.getFormDateFromDate(image.getPub_time());
//                        holder.tv0.setText(arr[1]);
//                        holder.tv1.setText(arr[0]);
//                        holder.tv2.setText(arr[2]);
//                    }
////                holder.tv3.setText(image.getUp_times());
//                }else{
//                    holder.tv0.setVisibility(View.GONE);
//                    holder.tv1.setVisibility(View.GONE);
//                    holder.tv2.setVisibility(View.GONE);
//                }
//
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
    public void loadMoreData(List<Items> datas) {
        addSrc(datas);
        notifyDataSetChanged();
        CCLog.print("loadMoreData:" + this.datas.size());
    }

    /**
     * 下拉刷新图片
     *
     * @param datas 图片url的集合
     */
    public void refreshData(List<Items> datas) {
        this.datas.clear();
        notifyDataSetChanged();
        for (int i=datas.size()-1;i>=0;i--){
            this.datas.add(0,datas.get(i));
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
        if (datas.size() > 0) {
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
