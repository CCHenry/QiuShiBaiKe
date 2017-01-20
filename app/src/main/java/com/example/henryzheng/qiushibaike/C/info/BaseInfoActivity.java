package com.example.henryzheng.qiushibaike.C.info;

import android.os.Bundle;
import android.view.View;

import com.example.henryzheng.qiushibaike.C.Base.BaseActivity;

public abstract class BaseInfoActivity extends BaseActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    /**
     * actionBar返回
     *
     * @param view
     */
    public void actionBatToBack(View view) {
        finish();
    }
}
