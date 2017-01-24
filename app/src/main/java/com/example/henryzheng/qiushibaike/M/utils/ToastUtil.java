package com.example.henryzheng.qiushibaike.M.utils;

import android.widget.Toast;

import com.example.henryzheng.qiushibaike.MyApplication;

/**
 * Created by henryzheng on 2017/1/24.
 */
public class ToastUtil {
public static void showNomalText(final String name){
    MyApplication.activitys.get(MyApplication.activitys.size()-1).runOnUiThread(new Runnable() {

        @Override
        public void run() {
            Toast.makeText(MyApplication.activitys.get(MyApplication.activitys.size()-1),name,Toast.LENGTH_SHORT).show();

        }
    });
}
}
