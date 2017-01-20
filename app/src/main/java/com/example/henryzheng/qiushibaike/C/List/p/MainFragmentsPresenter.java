package com.example.henryzheng.qiushibaike.C.List.p;

import android.media.Image;

import com.example.henryzheng.qiushibaike.C.List.i.MainFragmentInterface;
import com.example.henryzheng.qiushibaike.C.List.adapt.BaseListAdapt;
import com.example.henryzheng.qiushibaike.C.List.adapt.ImageListAdapt;
import com.example.henryzheng.qiushibaike.C.List.adapt.NewsListAdapt;
import com.example.henryzheng.qiushibaike.C.List.adapt.TextListAdapt;
import com.example.henryzheng.qiushibaike.C.List.adapt.VideoListAdapt;
import com.example.henryzheng.qiushibaike.M.Bean.image.ImageRootBean;
import com.example.henryzheng.qiushibaike.M.Bean.news.NewsRootBean;
import com.example.henryzheng.qiushibaike.M.Bean.text.TextRootBean;
import com.example.henryzheng.qiushibaike.M.Bean.video.VideoRootBean;
import com.example.henryzheng.qiushibaike.M.utils.ApiManage;
import com.example.henryzheng.qiushibaike.M.utils.CCLog;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by henryzheng on 2016/12/20.
 */
public class MainFragmentsPresenter {
    String realRequestUrl;
    int page = 0;
    String url;
    public static int LOAD_MORE_TYPE = 0;
    public static int REFRESH_DATA_TYPE = 1;
    MainFragmentInterface mainFragmentInterface;
    private List<Image> images;
    BaseListAdapt adapt;

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
        if (adapt instanceof VideoListAdapt) {
            startVideoHandle(load_data_type);
        } else if (adapt instanceof NewsListAdapt) {
            startNewsHandle(load_data_type);
        }else if (adapt instanceof TextListAdapt){
            startTextHandle(load_data_type);
        }else if (adapt instanceof ImageListAdapt){
            startImageHandler(load_data_type);
        }


    }


    private void startVideoHandle(final int load_data_type) {
        ApiManage.getInstence().getVedioApiService().getLastDaily(page,
                30, 0)
                .subscribeOn(Schedulers.io())
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
//                        handlerAndShowData(load_data_type, rootListBean);
                        if (load_data_type == LOAD_MORE_TYPE) {
                            mainFragmentInterface.loadNewData(rootListBean.getItems());

                        } else if (load_data_type == REFRESH_DATA_TYPE)
                            mainFragmentInterface.refreshData(rootListBean.getItems());
                    }
                });
    }

    private void startNewsHandle(final int load_data_type) {
        ApiManage.getInstence().getNewsApiService().getLastDaily(page,
                30, 0)
                .subscribeOn(Schedulers.io())
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
//                        handlerAndShowData(load_data_type, rootListBean);
                        if (load_data_type == LOAD_MORE_TYPE) {
                            mainFragmentInterface.loadNewData(rootListBean.getData());

                        } else if (load_data_type == REFRESH_DATA_TYPE)
                            mainFragmentInterface.refreshData(rootListBean.getData());
                    }
                });
    }
    private void startTextHandle(final int load_data_type) {
        ApiManage.getInstence().getTextApiService().getLastDaily(page,
                30, 0)
                .subscribeOn(Schedulers.io())
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
                30, 0)
                .subscribeOn(Schedulers.io())
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
     * 处理图片和展示
     *
     * @param load_data_type
     * @param result
     */
    private void handlerAndShowData(final int load_data_type, VideoRootBean result) {
        if (load_data_type == LOAD_MORE_TYPE) {
            mainFragmentInterface.loadNewData(result.getItems());

        } else if (load_data_type == REFRESH_DATA_TYPE)
            mainFragmentInterface.refreshData(result.getItems());
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
