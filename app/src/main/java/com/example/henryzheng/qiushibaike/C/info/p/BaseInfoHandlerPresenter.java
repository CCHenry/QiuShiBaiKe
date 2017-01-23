package com.example.henryzheng.qiushibaike.C.info.p;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.example.henryzheng.qiushibaike.C.list.adapt.BaseListAdapt;
import com.example.henryzheng.qiushibaike.M.bean.infoComment.InfoCommentRootBean;
import com.example.henryzheng.qiushibaike.M.utils.ApiManage;
import com.example.henryzheng.qiushibaike.M.utils.CCLog;
import com.example.henryzheng.qiushibaike.V.identityView.MyRecycleView;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by henryzheng on 2017/1/20.
 */
public class BaseInfoHandlerPresenter {
    private int count = 0;
    private MyRecycleView recycleView;
    private final Context context;
    int page = 0;
    public static int LOAD_MORE_TYPE = 0;
    public static int REFRESH_DATA_TYPE = 1;
    private List<Image> images;
    BaseListAdapt adapt;
    private String id;

    /**
     */

    public BaseInfoHandlerPresenter(Context context,
                                    BaseListAdapt
                                            recycleAdapter, MyRecycleView recyclerView, String
                                            id, int count) {
        this.adapt = recycleAdapter;
        this.context = context;
        this.id = id;
        LinearLayoutManager lin = new LinearLayoutManager(context);
        this.recycleView = recyclerView;
        recycleView.setLayoutManager(lin);
        recycleView.setItemAnimator(new DefaultItemAnimator());// 设置增加或删除条目的动画
        recycleView.setAdapter(adapt); // 设置Adapter
        recycleView.setIsFooterEnable(true);//启动下拉刷新
        adapt.setOnItemClickListner(new BaseListAdapt.OnItemClickListner() {
            @Override
            public void onItemClickListner(View v, int position) {

            }
        });
        adapt.setOnItemLongClickListner(new BaseListAdapt.OnItemLongClickListner() {
            @Override
            public void onItemLongClickListner(View v, int position) {

            }
        });
        this.count = count;
        recycleView.setLoadMoreListener(new MyRecycleView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                loadListData(LOAD_MORE_TYPE);
            }
        });
    }

    /**
     * 网络请求数据
     *
     * @param load_data_type
     */
    public void loadListData(final int load_data_type) {
        handlerPage(load_data_type);

        startInfoCommentHandle(load_data_type);
    }


    private void startInfoCommentHandle(final int load_data_type) {

        int onceLoadCommentCount = ((count - adapt.getData().size()) >= 30) ? 30 : (count - adapt
                .getData().size());
        if (onceLoadCommentCount > 0) {
            ApiManage.getInstence().getInfoCommentApiService().getLastDaily(id, page,
                    onceLoadCommentCount, 0)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<InfoCommentRootBean>() {
                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable e) {
                            CCLog.print(e.getMessage().toString());
                        }

                        @Override
                        public void onNext(InfoCommentRootBean rootListBean) {
//                        handlerAndShowData(load_data_type, rootListBean);
                            if (rootListBean != null) {
                                if (rootListBean.getCount() > 0) {
                                    CCLog.print(Thread.currentThread().getName());
                                    if (load_data_type == LOAD_MORE_TYPE) {
                                        loadNewData(rootListBean.getItems());
                                    } else if (load_data_type == REFRESH_DATA_TYPE)
                                        refreshData(rootListBean.getItems());
                                }
                            }
                        }
                    });
        } else {
//            adapt.notifyDataSetChanged();
            adapt.setIsEnd(true);
            adapt.notifyDataSetChanged();
        }
    }


    public void loadNewData(List datas) {
        adapt.loadMoreData(datas);
        recycleView.setLoadingMore(false);
    }

    public void refreshData(List datas) {
        adapt.refreshData(datas);
    }

    /**
     * 根据刷新和加载处理page
     *
     * @param load_data_type
     */
    private void handlerPage(int load_data_type) {
        if (load_data_type == REFRESH_DATA_TYPE) {
            page = 1;
        } else
            page++;
    }
}
