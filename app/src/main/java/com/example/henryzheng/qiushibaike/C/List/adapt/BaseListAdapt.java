package com.example.henryzheng.qiushibaike.C.list.adapt;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.henryzheng.qiushibaike.M.utils.CCLog;
import com.example.henryzheng.qiushibaike.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by henryzheng on 2017/1/12.
 */
public abstract class BaseListAdapt<T> extends RecyclerView.Adapter<BaseViewHolder> {
    private List<T> data;
    protected Context context;
    private OnItemClickListner onItemClickListner;//单击事件
    private OnItemLongClickListner onItemLongClickListner;//长按单击事件
    private boolean clickFlag = true;//单击事件和长单击事件的屏蔽标识
    private static final int HEAD_TYPE = 0;
    private static final int DATA_TYPE = 1;
    private static final int FOOT_TYPE = 2;
    LayoutInflater _mLayoutInflater;
    private BaseViewHolder holder;
    boolean isEnd = false;

    /**
     * @param context //上下文
     */
    public BaseListAdapt(Context context) {
        this.context = context;
        data = new ArrayList<>();
        _mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HEAD_TYPE) {
            View view = _mLayoutInflater.inflate(getHeadViewById(), parent, false);
            holder = new BaseViewHolder(context, HEAD_TYPE, view, onItemClickListner);
            return holder;
        } else if (viewType == FOOT_TYPE) {
            View view = _mLayoutInflater.inflate(getFootViewById(), parent, false);
            holder = new BaseViewHolder(context, FOOT_TYPE, view, onItemClickListner);
            return holder;
        } else {
            View view = _mLayoutInflater.inflate(getLayoutItemLayout(), parent, false);
            holder = new BaseViewHolder(context, DATA_TYPE, view, onItemClickListner);

            return holder;
        }
    }

    public int getHeadViewById() {
        return R.layout.layout_recycle_head;
    }

    public int getFootViewById() {
        return R.layout.layout_recycle_foot;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if (getItemCount() > 0) {

            if (position > 0 && position < getItemCount() - 1) {
//                PropertyValuesHolder pvh = PropertyValuesHolder.ofFloat(View.SCALE_X, 0
//                        , 1);
//                PropertyValuesHolder pvw = PropertyValuesHolder.ofFloat(View.SCALE_Y, 0
//                        , 1);
//                ObjectAnimator.ofPropertyValuesHolder(holder.getConvertView(), pvh,pvw).setDuration(300).start();

//                PropertyValuesHolder pvh = PropertyValuesHolder.ofFloat(View.TRANSLATION_X, -DensityUtils.getSceenWidth(context)
//                        , 1);
                convert(holder, data.get(position - 1));

            } else if (position == getItemCount() - 1) {
                convertByFloor(holder);
            } else if (position == 0) {
                convertByHead(holder);
            }
        }
    }

    protected abstract void convert(BaseViewHolder holder, T bean);

    public void convertByHead(BaseViewHolder holder) {

    }

    public void convertByFloor(BaseViewHolder holder) {
        if (isEnd) {
            holder.setVisability(R.id.relativeLayout0, View.GONE);
        }
    }

    public void setIsEnd(boolean isEnd) {
        this.isEnd = isEnd;
    }

    public void setOnItemClickListner(OnItemClickListner onItemClickListner) {
        this.onItemClickListner = onItemClickListner;
    }

    public void setOnItemLongClickListner(OnItemLongClickListner onItemLongClickListner) {
        this.onItemLongClickListner = onItemLongClickListner;
    }

    /**
     * @return
     */
    protected abstract int getLayoutItemLayout();

    public interface OnItemClickListner {
        void onItemClickListner(View v, int position);
    }

    public interface OnItemLongClickListner {
        void onItemLongClickListner(View v, int position);
    }

    @Override
    public int getItemCount() {
        if (data.size() > 0) {
            return data.size() + 2;
        } else
            return 2;

    }

    @Override
    public int getItemViewType(int position) {
        if (getItemCount() > 0) {
            if (position == 0) {
                return HEAD_TYPE;
            } else if (position == getItemCount() - 1) {
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
    public List<T> getData() {
        return data;
    }

    /**
     * @param data
     */
    public void loadMoreData(List<T> data) {
        int start = this.data.size() + 2;
        for (int i = 0; i < data.size(); i++) {
            this.data.add(data.get(i));
            notifyItemInserted(start);
        }

        CCLog.print("loadMoreData:" + this.data.size());
    }

    /**
     * 下拉刷新图片
     *
     * @param data 图片url的集合
     */
    public void refreshData(List<T> data) {
        this.data.clear();
        notifyDataSetChanged();
        for (int i = (data).size() - 1; i >= 0; i--) {
            this.data.add(0, data.get(i));
            notifyItemInserted(0);
        }
    }
}
