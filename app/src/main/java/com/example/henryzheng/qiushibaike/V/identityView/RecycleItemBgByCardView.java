package com.example.henryzheng.qiushibaike.V.identityView;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;

import com.example.henryzheng.qiushibaike.M.utils.DensityUtils;

/**
 * Created by henryzheng on 2017/1/22.
 */
public class RecycleItemBgByCardView extends CardView {
    public RecycleItemBgByCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setRadius(DensityUtils.dp2px(context,10));//设置图片圆角的半径大小
        setCardElevation(DensityUtils.dp2px(context,10));//设置阴影部分大小
        setContentPadding(DensityUtils.dp2px(context,10), DensityUtils.dp2px(context,10), DensityUtils.dp2px(context,10),DensityUtils.dp2px(context,10));//设置图片距离阴影大小
    }
}
