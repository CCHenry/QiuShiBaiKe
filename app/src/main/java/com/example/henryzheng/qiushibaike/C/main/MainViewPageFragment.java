package com.example.henryzheng.qiushibaike.C.main;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.henryzheng.qiushibaike.C.Base.BaseFragment;
import com.example.henryzheng.qiushibaike.C.List.BaseListFragment;
import com.example.henryzheng.qiushibaike.M.NewsListModel.VedioListModel;
import com.example.henryzheng.qiushibaike.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainViewPageFragment extends BaseFragment {
    @BindView(R.id.viewPage0)
    ViewPager viewPage0;
    @BindView(R.id.textView0)
    TextView textView0;
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
    }

    private void initView() {
        fragments.add(BaseListFragment.newInstance(new VedioListModel()));
        fragments.add(BaseListFragment.newInstance(new VedioListModel()));
        fragments.add(BaseListFragment.newInstance(new VedioListModel()));
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