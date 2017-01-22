package com.example.henryzheng.qiushibaike.C.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import butterknife.ButterKnife;

/**
 * Created by henryzheng on 2017/1/11.
 */
public abstract class BaseActivity extends FragmentActivity {
    public Context context;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewById());
        ButterKnife.bind(this);
        context=this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
    public static View getContentView(Activity ac){
        ViewGroup view = (ViewGroup)ac.getWindow().getDecorView();
        FrameLayout content = (FrameLayout)view.findViewById(android.R.id.content);
        return content.getChildAt(0);
    }
    public abstract int getContentViewById();

}
