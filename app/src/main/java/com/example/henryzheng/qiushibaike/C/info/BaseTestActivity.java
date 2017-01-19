package com.example.henryzheng.qiushibaike.C.info;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import butterknife.ButterKnife;

/**
 * Created by henryzheng on 2017/1/19.
 */
public abstract class BaseTestActivity extends FragmentActivity {
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewById());
        ButterKnife.bind(this);
    }

    public abstract int getContentViewById();
}
