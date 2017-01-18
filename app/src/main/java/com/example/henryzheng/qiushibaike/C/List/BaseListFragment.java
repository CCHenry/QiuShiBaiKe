package com.example.henryzheng.qiushibaike.C.List;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.example.henryzheng.qiushibaike.C.Base.BaseFragment;
import com.example.henryzheng.qiushibaike.C.List.i.MainFragmentInterface;
import com.example.henryzheng.qiushibaike.C.List.p.MainFragmentsPresenter;
import com.example.henryzheng.qiushibaike.C.adapt.list.VideoListAdapt;
import com.example.henryzheng.qiushibaike.C.myInterface.MyItemClickListener;
import com.example.henryzheng.qiushibaike.M.listModel.BaseListModel;
import com.example.henryzheng.qiushibaike.M.utils.CCLog;
import com.example.henryzheng.qiushibaike.M.utils.DensityUtils;
import com.example.henryzheng.qiushibaike.R;
import com.example.henryzheng.qiushibaike.V.identityView.MyRecycleView;

import java.util.List;

import butterknife.BindView;

public class BaseListFragment extends BaseFragment implements MyItemClickListener,
        MainFragmentInterface {
    BaseListModel baseListModel;
    MainFragmentsPresenter presenter;
    private VideoListAdapt recycleAdapter;
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

    @Override
    public int getContentViewId() {
        return R.layout.fragment_base_list;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new MainFragmentsPresenter(this, baseListModel.getUrl(), baseListModel
                .getType());
        recycleAdapter = new VideoListAdapt(getActivity());
        lin = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(lin);
        recyclerView.setItemAnimator(new DefaultItemAnimator());// 设置增加或删除条目的动画
        recycleAdapter.setOnItemClickListener(this);
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
    public void onItemClick(View view, int postion) {
//        Intent intent = new Intent(getActivity(), BigImageShowActivity.class);
//        intent.putExtra("images", (Serializable) recycleAdapter.getImages());
//        intent.putExtra("imageListBaseModel", imageListBaseModel);
//        intent.putExtra("position", postion - 1);
//        presenter.loadImageToCacheForBG(postion, recycleAdapter.getImages().get(postion - 1)
// .getImage_url());
//        startActivity(intent);
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

//    @Override
//    public void loadNewImages(List<Image> images) {
//        swipeRefreshLayout.setRefreshing(false);
//        recycleAdapter.loadMoreData(images);
//        recyclerView.setLoadingMore(false);
//    }
//
//    @Override
//    public void refreshImages(List<Image> images1) {
//        swipeRefreshLayout.setRefreshing(false);
//        recycleAdapter.refreshData(images1);
//    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


//    @Override
//    public void loadNewImages(RootListBean images) {
//        swipeRefreshLayout.setRefreshing(false);
//        recycleAdapter.loadMoreData(images.getItems());
//        recyclerView.setLoadingMore(false);
//    }
//
//    @Override
//    public void refreshImages(RootListBean data) {
//        swipeRefreshLayout.setRefreshing(false);
//        recycleAdapter.refreshData(data.getItems());
//    }


    @Override
    public void loadNewImages(List datas) {
        swipeRefreshLayout.setRefreshing(false);
        recycleAdapter.loadMoreData(datas);
        recyclerView.setLoadingMore(false);
    }

    @Override
    public void refreshImages(List datas) {
        swipeRefreshLayout.setRefreshing(false);
        recycleAdapter.refreshData(datas);
    }
}