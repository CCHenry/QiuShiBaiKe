package com.example.henryzheng.qiushibaike.C.adapt.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.henryzheng.qiushibaike.M.Bean.video.VideoRootBean;
import com.example.henryzheng.qiushibaike.M.utils.CCLog;
import com.example.henryzheng.qiushibaike.R;

import java.util.List;

/**
 * Created by henryzheng on 2017/1/12.
 */
public abstract class BaseListAdapt<T> extends RecyclerView.Adapter<BaseViewHolder> {
    private int layoutId;
    private List<T> data;
    private Context context;
    private OnItemClickListner onItemClickListner;//单击事件
    private OnItemLongClickListner onItemLongClickListner;//长按单击事件
    private boolean clickFlag = true;//单击事件和长单击事件的屏蔽标识
    private static final int HEAD_TYPE = 0;
    private static final int DATA_TYPE = 1;
    private static final int FOOT_TYPE = 2;
   LayoutInflater _mLayoutInflater;
    /**
     * @param context  //上下文
     * @param layoutId //布局id
     * @param data     //数据源
     */
    public BaseListAdapt(Context context, int layoutId, List data) {
        this.layoutId = layoutId;
        this.data = data;
        this.context = context;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HEAD_TYPE) {
            View view = _mLayoutInflater.inflate(R.layout.layout_recycle_head, parent, false);
            BaseViewHolder holder = new BaseViewHolder(context,HEAD_TYPE, view);
            return holder;
        } else if (viewType == FOOT_TYPE) {
            View view = _mLayoutInflater.inflate(R.layout.layout_recycle_foot, parent, false);
            BaseViewHolder holder = new BaseViewHolder(context,FOOT_TYPE, view);
            return holder;
        } else {
            View view = _mLayoutInflater.inflate(R.layout.recycle_view_video_item, parent, false);
            BaseViewHolder holder = new BaseViewHolder(context,DATA_TYPE, view);
            return holder;
        }
//        View v = LayoutInflater.from(context).inflate(layoutId, parent, false);
//        final BaseViewHolder holder = new BaseViewHolder(v, context,viewType);

//        //单击事件回调
//        v.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (clickFlag) {
//                    onItemClickListner.onItemClickListner(v, holder.getLayoutPosition());
//                }
//                clickFlag = true;
//            }
//        });
//        //单击长按事件回调
//        v.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                onItemLongClickListner.onItemLongClickListner(v, holder.getLayoutPosition());
//                clickFlag = false;
//                return false;
//            }
//        });
//        return holder;
    }


    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        convert(holder, data.get(position));
    }

    protected abstract void convert(BaseViewHolder holder, T bean);



    public void setOnItemClickListner(OnItemClickListner onItemClickListner) {
        this.onItemClickListner = onItemClickListner;
    }

    public void setOnItemLongClickListner(OnItemLongClickListner onItemLongClickListner) {
        this.onItemLongClickListner = onItemLongClickListner;
    }

    public interface OnItemClickListner {
        void onItemClickListner(View v, int position);
    }

    public interface OnItemLongClickListner {
        void onItemLongClickListner(View v, int position);
    }
    @Override
    public int getItemCount() {
        if (data.size()>0){
            return data.size() + 2;}
        else
            return 0;
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
    /**
     * 清除url的缓存
     */
    public void clear() {
        data.clear();
    }

    /**
     * 返回url的缓存列表
     *
     * @return
     */
    public List<T> getImages() {
        return data;
    }
    /**
     *
     * @param datas
     */
    public void loadMoreData(Object datas) {
        for (int i = 0; i < data.size(); i++) {
            this.data.add(data.get(i));
        }        notifyDataSetChanged();
        CCLog.print("loadMoreData:" + this.data.size());
    }

    /**
     * 下拉刷新图片
     *
     * @param datas 图片url的集合
     */
    public void refreshData(Object datas) {
        this.data.clear();
        notifyDataSetChanged();
        for (int i=((VideoRootBean)datas).getItems().size()-1;i>=0;i--){
            this.data.add(0,data.get(i));
            notifyItemInserted(0);
        }
    }
}
