package com.example.henryzheng.qiushibaike.M.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by henryzheng on 2017/1/24.
 */
public class MySharePreferences {
    public static void saveData(Context context, String name,String value){
        SharedPreferences sp = context.getSharedPreferences("sharePreferencesConfig", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(name, value);
        editor.commit();
    }
    public static String loadData(Context context, String name) {
        SharedPreferences sp = context.getSharedPreferences("sharePreferencesConfig",  Context.MODE_PRIVATE);
       return sp.getString(name, "").toString();
    }
    public static void saveIntData(Context context, String name,int value){
        SharedPreferences sp = context.getSharedPreferences("sharePreferencesConfig", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(name, value);
        editor.commit();
    }
    public static int loadIntData(Context context, String name) {
        SharedPreferences sp = context.getSharedPreferences("sharePreferencesConfig",  Context.MODE_PRIVATE);
        return sp.getInt(name,0);
    }
}
