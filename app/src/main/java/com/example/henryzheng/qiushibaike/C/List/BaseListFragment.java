package com.example.henryzheng.qiushibaike.C.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.example.henryzheng.qiushibaike.C.base.BaseFragment;
import com.example.henryzheng.qiushibaike.C.list.adapt.BaseListAdapt;
import com.example.henryzheng.qiushibaike.C.list.adapt.NewsListAdapt;
import com.example.henryzheng.qiushibaike.C.list.adapt.VideoListAdapt;
import com.example.henryzheng.qiushibaike.C.list.i.MainFragmentInterface;
import com.example.henryzheng.qiushibaike.C.list.p.MainFragmentsPresenter;
import com.example.henryzheng.qiushibaike.C.info.news.NewsInfoActivity;
import com.example.henryzheng.qiushibaike.C.info.video.VideoInfoActivity;
import com.example.henryzheng.qiushibaike.M.listModel.BaseListModel;
import com.example.henryzheng.qiushibaike.M.utils.CCLog;
import com.example.henryzheng.qiushibaike.M.utils.DensityUtils;
import com.example.henryzheng.qiushibaike.R;
import com.example.henryzheng.qiushibaike.V.identityView.MyRecycleView;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;

public class BaseListFragment extends BaseFragment implements
        MainFragmentInterface, BaseListAdapt.OnItemClickListner {
    BaseListModel baseListModel;
    MainFragmentsPresenter presenter;
    public BaseListAdapt recycleAdapter;
    private LinearLayoutManager lin;
    @BindView(R.id.recycleView0)
    MyRecycleView recyclerView;
    @BindView(R.id.swipeRefreshLayout0)
    SwipeRefreshLayout swipeRefreshLayout;

    public static BaseListFragment newInstance(BaseListModel imageListBaseModel) {
        BaseListFragment f = new BaseListFragment();
        f.baseListModel = imageListBaseModel;
        return f;
    }

    public static BaseListFragment newInstance(BaseListAdapt adapt) {
        BaseListFragment f = new BaseListFragment();
        f.recycleAdapter = adapt;
        return f;
    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_base_list;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new MainFragmentsPresenter(this, recycleAdapter);
        lin = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(lin);
        recyclerView.setItemAnimator(new DefaultItemAnimator());// 设置增加或删除条目的动画
        recycleAdapter.setOnItemClickListner(this);
        recyclerView.setAdapter(recycleAdapter); // 设置Adapter
        recyclerView.setIsFooterEnable(true);//启动下拉刷新
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R
                .color.holo_red_light, android.R.color.holo_orange_light, android.R.color
                .holo_green_light);
        swipeRefreshLayout.setProgressViewOffset(false, DensityUtils.dp2px(context, 10), (int)
                getResources().getDimension(R.dimen
                        .h1) + DensityUtils.dp2px(context, 50));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.loadListData(presenter.REFRESH_DATA_TYPE);
            }
        });
        recyclerView.setLoadMoreListener(new MyRecycleView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                presenter.loadListData(presenter.LOAD_MORE_TYPE);
            }
        });
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
            }
        });
        presenter.loadListData(presenter.REFRESH_DATA_TYPE);

    }



    @Override
    public void onResume() {
        super.onResume();
        CCLog.print("onResume");

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        CCLog.print("onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }




    @Override
    public void loadNewData(List datas) {
        swipeRefreshLayout.setRefreshing(false);
        recycleAdapter.loadMoreData(datas);
        recyclerView.setLoadingMore(false);
    }

    @Override
    public void refreshData(List datas) {
        swipeRefreshLayout.setRefreshing(false);
        recycleAdapter.refreshData(datas);
    }

    @Override
    public void onItemClickListner(View v, int position) {
        Intent intent = null;
        if (recycleAdapter instanceof VideoListAdapt) {
            intent = new Intent(getActivity(), VideoInfoActivity.class);
            intent.putExtra("data", (Serializable) recycleAdapter.getData().get(position - 1));
            intent.putExtra("position", position - 1);
        } else if (recycleAdapter instanceof NewsListAdapt) {
            intent = new Intent(getActivity(), NewsInfoActivity.class);
            intent.putExtra("data", (Serializable) recycleAdapter.getData().get(position - 1));
            intent.putExtra("position", position - 1);
        }

        startActivity(intent);
    }
}