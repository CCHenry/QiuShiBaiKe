package com.example.henryzheng.qiushibaike.C.info;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.example.henryzheng.qiushibaike.C.Base.BaseActivity;
import com.example.henryzheng.qiushibaike.R;

import butterknife.BindView;

public abstract class BaseInfoActivity extends BaseActivity {
    @BindView(R.id.linearLayout0)
    private LinearLayout linearLayout0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_info);
        linearLayout0.addView(LayoutInflater.from(this).inflate(getContentView(),null));
    }

    public abstract int getContentView();
}
