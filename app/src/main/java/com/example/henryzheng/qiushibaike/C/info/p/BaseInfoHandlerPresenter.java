package com.example.henryzheng.qiushibaike.C.info.p;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;

import com.example.henryzheng.qiushibaike.C.List.adapt.BaseListAdapt;
import com.example.henryzheng.qiushibaike.M.Bean.infoComment.InfoCommentRootBean;
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

    public BaseInfoHandlerPresenter(Context context, BaseListAdapt
            recycleAdapter, MyRecycleView recyclerView,String id) {
        this.adapt = recycleAdapter;
        this.context = context;
        this.id=id;
        LinearLayoutManager lin = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(lin);
        recyclerView.setItemAnimator(new DefaultItemAnimator());// 设置增加或删除条目的动画
        recyclerView.setAdapter(adapt); // 设置Adapter
        recyclerView.setIsFooterEnable(true);//启动下拉刷新
//        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLoadMoreListener(new MyRecycleView.LoadMoreListener() {
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
//        if (adapt instanceof VideoListAdapt) {
//            startVideoHandle(load_data_type);
//        } else if (adapt instanceof NewsListAdapt) {
//            startNewsHandle(load_data_type);
//        } else if (adapt instanceof TextListAdapt) {
//            startTextHandle(load_data_type);
//        } else if (adapt instanceof ImageListAdapt) {
//            startImageHandler(load_data_type);
//        }

        startInfoCommentHandle(load_data_type);
    }


    private void startInfoCommentHandle(final int load_data_type) {
        ApiManage.getInstence().getInfoCommentApiService().getLastDaily(id,page,
                30, 0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<InfoCommentRootBean>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        CCLog.print(e.getCause().toString());
                    }

                    @Override
                    public void onNext(InfoCommentRootBean rootListBean) {
//                        handlerAndShowData(load_data_type, rootListBean);
                        if (load_data_type == LOAD_MORE_TYPE) {
                            loadNewData(rootListBean.getItems());

                        } else if (load_data_type == REFRESH_DATA_TYPE)
                            refreshData(rootListBean.getItems());
                    }
                });
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
