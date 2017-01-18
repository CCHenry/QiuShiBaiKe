package com.example.henryzheng.qiushibaike.C.Base;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;

import butterknife.ButterKnife;

/**
 * Created by henryzheng on 2017/1/11.
 */
public abstract class BaseActivity extends FragmentActivity {
    public Context context;
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        ButterKnife.bind(this);
//        context=this;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        ButterKnife.bind(this);
        context=this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
