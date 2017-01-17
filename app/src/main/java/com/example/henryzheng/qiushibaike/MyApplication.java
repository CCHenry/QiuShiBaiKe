package com.example.henryzheng.qiushibaike;

import android.app.Application;

/**
 * Created by henryzheng on 2017/1/12.
 */
public class MyApplication extends Application {
    public static MyApplication myApplication;

    public static Application getContext() {

        return myApplication;

    }

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
    }
}
