package com.example.henryzheng.qiushibaike.M.utils;

import android.util.Log;

/**
 * Created by henryzheng on 2016/9/30.
 */
public class CCLog {
    private static String tag = "cctag";

    public static void print(String msg) {
        Log.i(tag, msg);
    }
}
