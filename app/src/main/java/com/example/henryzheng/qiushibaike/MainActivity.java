package com.example.henryzheng.qiushibaike;

import android.os.Bundle;
import android.widget.TextView;

import com.example.henryzheng.qiushibaike.C.base.BaseActivity;
import com.example.henryzheng.qiushibaike.M.bean.test.RootListBean;
import com.example.henryzheng.qiushibaike.M.utils.ApiManage;
import com.example.henryzheng.qiushibaike.M.utils.CCLog;

import butterknife.BindView;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivity {

    @BindView(R.id.text)
    TextView text;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ApiManage.getInstence().getTopNewsService();
    }

    @Override
    public int getContentViewById() {
        return R.layout.activity_main;
    }

    @Override
    protected void onResume() {
        super.onResume();
        text.setText("asdadsdasdasd");
        getLastZhihuNews();

    }

    public void getLastZhihuNews() {

        Subscription subscription = ApiManage.getInstence().getTopNewsService().getLastDaily(1,
                30, 0)
                .map(new Func1<RootListBean, RootListBean>() {
                    @Override
                    public RootListBean call(RootListBean zhuanXiangBean) {
                        CCLog.print(zhuanXiangBean.toString());
                        return zhuanXiangBean;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RootListBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(RootListBean zhuanXiangBean) {
                        CCLog.print(zhuanXiangBean.toString());
                    }
                });
    }
}
