package com.example.henryzheng.qiushibaike;

import android.app.Activity;
import android.app.Application;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by henryzheng on 2017/1/12.
 */
public class MyApplication extends Application {
    public static MyApplication myApplication;
    public static List<Activity> activitys;

    public static Application getContext() {

        return myApplication;

    }

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        activitys=new ArrayList<>();
    }
    public static void  addActivity(Activity activity){
        activitys.add(activity);
    }
    public static void  removeActivity(Activity activity){
        activitys.remove(activity);
    }
}
