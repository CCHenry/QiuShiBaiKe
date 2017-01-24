package com.example.henryzheng.qiushibaike.C.list.p;

import com.example.henryzheng.qiushibaike.C.list.adapt.BaseListAdapt;
import com.example.henryzheng.qiushibaike.C.list.adapt.ImageListAdapt;
import com.example.henryzheng.qiushibaike.C.list.adapt.NewsListAdapt;
import com.example.henryzheng.qiushibaike.C.list.adapt.TextListAdapt;
import com.example.henryzheng.qiushibaike.C.list.adapt.VideoListAdapt;
import com.example.henryzheng.qiushibaike.C.list.i.MainFragmentInterface;
import com.example.henryzheng.qiushibaike.M.bean.image.ImageRootBean;
import com.example.henryzheng.qiushibaike.M.bean.news.NewsRootBean;
import com.example.henryzheng.qiushibaike.M.bean.retrofitException.HttpResultFunc;
import com.example.henryzheng.qiushibaike.M.bean.text.TextRootBean;
import com.example.henryzheng.qiushibaike.M.bean.video.VideoRootBean;
import com.example.henryzheng.qiushibaike.M.utils.ApiManage;
import com.example.henryzheng.qiushibaike.M.utils.CCLog;
import com.example.henryzheng.qiushibaike.M.utils.MySharePreferences;
import com.example.henryzheng.qiushibaike.M.utils.NetWorkStateUtil;
import com.example.henryzheng.qiushibaike.MyApplication;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by henryzheng on 2016/12/20.
 */
public class MainFragmentsPresenter {
    int page = 0;
    public static int LOAD_MORE_TYPE = 0;
    public static int REFRESH_DATA_TYPE = 1;
    MainFragmentInterface mainFragmentInterface;
    BaseListAdapt adapt;
    private int recent=0;

    /**
     * @param mainFragmentInterface
     */

    public MainFragmentsPresenter(MainFragmentInterface mainFragmentInterface, BaseListAdapt
            adapt) {
        this.mainFragmentInterface = mainFragmentInterface;
        this.adapt = adapt;
    }

    /**
     * 网络请求数据
     *
     * @param load_data_type
     */
    public void loadListData(final int load_data_type) {
        handlerPage(load_data_type);
        handlerRecent(load_data_type);
        if (adapt instanceof VideoListAdapt) {
            startVideoHandle(load_data_type);
        } else if (adapt instanceof NewsListAdapt) {
            startNewsHandle(load_data_type);
        } else if (adapt instanceof TextListAdapt) {
            startTextHandle(load_data_type);
        } else if (adapt instanceof ImageListAdapt) {
            startImageHandler(load_data_type);
        }
    }



    private void startVideoHandle(final int load_data_type) {
        ApiManage.getInstence().getVedioApiService().getLastDaily(page,
                30, recent)
                .subscribeOn(Schedulers.io())
                //HttpResultFunc（）为拦截onError事件的拦截器，后面会讲到，这里先忽略
                .onErrorResumeNext(new HttpResultFunc<VideoRootBean>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<VideoRootBean>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        CCLog.print(e.getCause().toString());
                    }

                    @Override
                    public void onNext(VideoRootBean rootListBean) {
                        if (load_data_type == LOAD_MORE_TYPE) {
                            mainFragmentInterface.loadNewData(rootListBean.getItems());
                        } else if (load_data_type == REFRESH_DATA_TYPE)
                            mainFragmentInterface.refreshData(rootListBean.getItems());

                    }
                });
    }

    private void startNewsHandle(final int load_data_type) {
        ApiManage.getInstence().getNewsApiService().getLastDaily(page,
                30, recent)
                .subscribeOn(Schedulers.io())
                .onErrorResumeNext(new HttpResultFunc<NewsRootBean>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsRootBean>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        CCLog.print(e.getCause().toString());
                    }

                    @Override
                    public void onNext(NewsRootBean rootListBean) {
                        if (load_data_type == LOAD_MORE_TYPE) {
                            mainFragmentInterface.loadNewData(rootListBean.getData());
                        } else if (load_data_type == REFRESH_DATA_TYPE)
                            mainFragmentInterface.refreshData(rootListBean.getData());
                    }
                });
    }

    private void startTextHandle(final int load_data_type) {
        ApiManage.getInstence().getTextApiService().getLastDaily(page,
                30, recent)
                .subscribeOn(Schedulers.io())
                .onErrorResumeNext(new HttpResultFunc<TextRootBean>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TextRootBean>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        CCLog.print(e.getCause().toString());
                    }

                    @Override
                    public void onNext(TextRootBean rootListBean) {
//                        handlerAndShowData(load_data_type, rootListBean);
                        if (load_data_type == LOAD_MORE_TYPE) {
                            mainFragmentInterface.loadNewData(rootListBean.getItems());

                        } else if (load_data_type == REFRESH_DATA_TYPE)
                            mainFragmentInterface.refreshData(rootListBean.getItems());
                    }
                });
    }

    private void startImageHandler(final int load_data_type) {
        ApiManage.getInstence().getImageApiService().getLastDaily(page,
                30, recent)
                .subscribeOn(Schedulers.io())
                .onErrorResumeNext(new HttpResultFunc<ImageRootBean>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ImageRootBean>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        CCLog.print(e.getCause().toString());
                    }

                    @Override
                    public void onNext(ImageRootBean rootListBean) {
//                        handlerAndShowData(load_data_type, rootListBean);
                        if (load_data_type == LOAD_MORE_TYPE) {
                            mainFragmentInterface.loadNewData(rootListBean.getItems());

                        } else if (load_data_type == REFRESH_DATA_TYPE)
                            mainFragmentInterface.refreshData(rootListBean.getItems());
                    }
                });
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
    private void handlerRecent(int load_data_type) {
        if (load_data_type == REFRESH_DATA_TYPE) {
           recent= MySharePreferences.loadIntData (MyApplication.getContext(),adapt.getClass().getName());
            if (NetWorkStateUtil.isNetWorkAvailable(MyApplication.getContext())){//如果当前有网路,
                recent++;
                MySharePreferences.saveIntData(MyApplication.getContext(), adapt.getClass().getName(),recent);
            }
        }

    }


}
