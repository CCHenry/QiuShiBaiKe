package com.example.henryzheng.qiushibaike.C.main;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
public class MainViewPageFragment extends BaseFragment {
    @BindView(R.id.viewPage0)
    ViewPager viewPage0;
@BindView(R.id.navigation_view)
    NavigationView navigationView;
    List<Fragment> fragments=new ArrayList<>();
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
}
