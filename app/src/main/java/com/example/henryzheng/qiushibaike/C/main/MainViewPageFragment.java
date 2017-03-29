package com.example.henryzheng.qiushibaike.C.main;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.henryzheng.qiushibaike.C.base.BaseFragment;
import com.example.henryzheng.qiushibaike.C.list.BaseListFragment;
import com.example.henryzheng.qiushibaike.C.list.adapt.ImageListAdapt;
import com.example.henryzheng.qiushibaike.C.list.adapt.NewsListAdapt;
import com.example.henryzheng.qiushibaike.C.list.adapt.TextListAdapt;
import com.example.henryzheng.qiushibaike.C.list.adapt.VideoListAdapt;
import com.example.henryzheng.qiushibaike.R;
import com.example.henryzheng.qiushibaike.V.identityView.NavigationView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainViewPageFragment extends BaseFragment implements View.OnTouchListener {
    @BindView(R.id.viewPage0)
    ViewPager viewPage0;
@BindView(R.id.navigation_view)
    NavigationView navigationView;
    List<Fragment> fragments=new ArrayList<>();
    private GestureDetector gestureDetector;

    @Override
    public int getContentViewId() {
        return R.layout.fragment_main_view_page;
    }

          @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return super.onCreateView(inflater, container, savedInstanceState);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        navigationView.setMainPage(viewPage0);
//        viewPage0.setOnTouchListener(this);
        gestureDetector=new GestureDetector(new DefaultGestureDetector());
//        viewPage0.setPageTransformer(true,new DepthPageTransformer());
    }

    private void initView() {
        fragments.add(BaseListFragment.newInstance(new VideoListAdapt(getActivity())));
        fragments.add(BaseListFragment.newInstance(new NewsListAdapt(getActivity())));
        fragments.add(BaseListFragment.newInstance(new TextListAdapt(getActivity())));
        fragments.add(BaseListFragment.newInstance(new ImageListAdapt(getActivity())));

//        fragments.add(BaseListFragment.newInstance(new VedioListModel()));
        viewPage0.setAdapter(new FragmentPagerAdapter(getFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    class DefaultGestureDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY){
            final int FLING_MIN_DISTANCE=100;//X或者y轴上移动的距离(像素)
            final int FLING_MIN_VELOCITY=200;//x或者y轴上的移动速度(像素/秒)
            if((e1.getY()-e2.getY())>FLING_MIN_DISTANCE && Math.abs(velocityY)>FLING_MIN_VELOCITY)
                Toast.makeText(getActivity(), "向左滑动", Toast.LENGTH_SHORT).show();
            else if((e2.getY()-e1.getY())>FLING_MIN_DISTANCE && Math.abs(velocityY)>FLING_MIN_VELOCITY)
                Toast.makeText(getActivity(), "向右滑动", Toast.LENGTH_SHORT).show();
            return false;
        }
    }



}
