package com.example.henryzheng.qiushibaike.C.info;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.example.henryzheng.qiushibaike.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class BaseInfoActivity extends FragmentActivity {
    @BindView(R.id.linearLayout0)
    public LinearLayout linearLayout0;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_base_info);
        ButterKnife.bind(this);

        linearLayout0.addView(LayoutInflater.from(this).inflate(getContentView(),null));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_info);
        ButterKnife.bind(this);

        linearLayout0.addView(LayoutInflater.from(this).inflate(getContentView(),null));
    }

    public abstract int getContentView();
}
