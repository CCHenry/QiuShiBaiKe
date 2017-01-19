package com.example.henryzheng.qiushibaike.V.identityView;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.henryzheng.qiushibaike.R;


/**
 * Created by henryzheng on 2016/12/13.
 */
public class NavigationView extends RelativeLayout implements View.OnClickListener {
    Context context;
    LinearLayout lin0;// title的layout
    LinearLayout bottomLin;//底部的线的布局
    public ViewPager viewPager;
    TextView tv0;
    TextView tv1;
    TextView tv2;
    float mPositionOffset = 0;
    int titleWidth = 0;
    float instanceX = 0;
    LayoutParams bottomLinLayoutParams;

    public NavigationView(Context context) {
        super(context);
    }

    public NavigationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        addView(context);
    }

    private void addView(Context context) {
        LayoutInflater mInflater = LayoutInflater.from(context);
        RelativeLayout nv = (RelativeLayout) mInflater.inflate(R.layout
                .relativelayout_navigation1, null);
        tv0 = (TextView) nv.findViewById(R.id.textView0);
        tv1 = (TextView) nv.findViewById(R.id.textView1);
        tv2 = (TextView) nv.findViewById(R.id.textView2);
        lin0 = (LinearLayout) nv.findViewById(R.id.lin0);
        bottomLin = (LinearLayout) nv.findViewById(R.id.linearLayout5);
        tv0.setOnClickListener(this);
        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        int width = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        int height = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        lin0.measure(width, height);
        height = lin0.getMeasuredHeight();
        width = lin0.getMeasuredWidth();
        titleWidth = width / 3;
        LayoutParams layoutParams = (LayoutParams) bottomLin
                .getLayoutParams();
        layoutParams.width = titleWidth;
        bottomLin.setLayoutParams(layoutParams);
        final int[] location = new int[2];
        lin0.getLocationOnScreen(location);
        instanceX = location[0];
        bottomLinLayoutParams = (LayoutParams) bottomLin.getLayoutParams();
        addView(nv);

    }

    /**
     * 设置viewpager，监听viewpager，让标识移动
     *
     * @param viewPager
     */
    public void setMainPage(final ViewPager viewPager) {
        this.viewPager = viewPager;
        final int selectColor = getResources().getColor(R.color.gray2);
        final int nomalColor = getResources().getColor(R.color.gray);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        tv0.setTextColor(selectColor);
                        tv1.setTextColor(nomalColor);
                        tv2.setTextColor(nomalColor);
                        break;
                    case 1:
                        tv1.setTextColor(selectColor);
                        tv0.setTextColor(nomalColor);
                        tv2.setTextColor(nomalColor);
                        break;
                    case 2:
                        tv2.setTextColor(selectColor);
                        tv0.setTextColor(nomalColor);
                        tv1.setTextColor(nomalColor);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int
                    positionOffsetPixels) {
                scrollOnListener(position, positionOffset);
            }

            /**
             * 滚动处理
             * @param position
             * @param positionOffset
             */
            private void scrollOnListener(int position, float positionOffset) {


                mPositionOffset = positionOffset;
                switch (position) {
                    case 0:
                        bottomLinLayoutParams.leftMargin = (int) (titleWidth * positionOffset);
                        bottomLin.setLayoutParams(bottomLinLayoutParams);
                        bottomLin.requestLayout();
                        break;
                    case 1:
                        bottomLinLayoutParams.leftMargin = titleWidth + (int) (titleWidth *
                                positionOffset);
                        bottomLin.setLayoutParams(bottomLinLayoutParams);
                        bottomLin.requestLayout();
                        break;
                    case 2:
                        bottomLinLayoutParams.leftMargin = titleWidth * 2 + (int) (titleWidth *
                                positionOffset);
                        bottomLin.setLayoutParams(bottomLinLayoutParams);
                        bottomLin.requestLayout();
                        break;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textView0:
                viewPager.setCurrentItem(0);
                break;
            case R.id.textView1:
                viewPager.setCurrentItem(1);
                break;
            case R.id.textView2:
                viewPager.setCurrentItem(2);
                break;
        }
    }
}
