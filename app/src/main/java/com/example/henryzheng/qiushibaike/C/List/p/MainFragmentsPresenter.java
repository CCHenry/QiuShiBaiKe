package com.example.henryzheng.qiushibaike.C.List.p;

import android.media.Image;

import com.example.henryzheng.qiushibaike.C.List.i.MainFragmentInterface;
import com.example.henryzheng.qiushibaike.M.Bean.video.VideoRootBean;
import com.example.henryzheng.qiushibaike.M.utils.ApiManage;
import com.example.henryzheng.qiushibaike.M.utils.CCLog;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
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
    String type;

    /**
     * @param mainFragmentInterface
     * @param url                   加载url
     * @param type                  类型，用加载的fragment的类名表示
     */

    public MainFragmentsPresenter(MainFragmentInterface mainFragmentInterface, String url, String
            type) {
        this.mainFragmentInterface = mainFragmentInterface;
        this.url = url;
        this.type = type;
    }

    /**
     * 网络请求数据
     *
     * @param load_data_type
     */
    public void loadListData(final int load_data_type) {
        handlerPage(load_data_type);
        handlerUrl(type);
                ApiManage.getInstence().getVedioApiService().getLastDaily(page,
                30, 0)
                .map(new Func1<VideoRootBean, VideoRootBean>() {
                    @Override
                    public VideoRootBean call(VideoRootBean datas) {
                        CCLog.print(datas.toString());
                        return datas;
                    }
                })
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
                        handlerAndShowData(load_data_type,rootListBean);
                    }
                });
    }

    /**
     * 处理图片和展示
     *
     * @param load_data_type
     * @param result
     */
    private void handlerAndShowData(final int load_data_type,VideoRootBean result) {
        if (load_data_type == LOAD_MORE_TYPE) {
            mainFragmentInterface.loadNewImages(result.getItems());

        } else if (load_data_type == REFRESH_DATA_TYPE)
            mainFragmentInterface.refreshImages(result.getItems());
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

    /**
     * 不同的mode用不同的url处理方式
     */
    private void handlerUrl(String type) {
//        if (type == TodayZuiMeiModel.class.getName()) {
//            realRequestUrl = String.format(url, System.currentTimeMillis() / 1000 - 86400 * 30 *
//                    (page - 1));
//        } else {
//            realRequestUrl = String.format(url, page);
//        }
    }




}
