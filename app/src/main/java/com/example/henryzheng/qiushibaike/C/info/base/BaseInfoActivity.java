package com.example.henryzheng.qiushibaike.C.info.base;

import android.os.Bundle;
import android.view.View;

import com.example.henryzheng.qiushibaike.C.base.BaseActivity;

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

    @Override
    protected void onStop() {
        super.onStop();
//        ExplosionField mExplosionField = ExplosionField.attach2Window(this);
//        ViewGroup view = (ViewGroup) getWindow().getDecorView();
//        final FrameLayout content = (FrameLayout) view.findViewById(android.R.id.content);
//        final View contentView = content.getChildAt(0);
//        mExplosionField.explode(contentView);


    }

//    public boolean onKeyDown(int keyCode, KeyEvent event) {// TODOAuto-generated method stub
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            ExplosionField mExplosionField = ExplosionField.attach2Window(this);
//            ViewGroup view = (ViewGroup) getWindow().getDecorView();
//            final FrameLayout content = (FrameLayout) view.findViewById(android.R.id.content);
//            final View contentView = content.getChildAt(0);
//            mExplosionField.explode(contentView);
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    finish();
//                }
//            }, 500);
//            return true;
//        }
//        return false;
//    }
}
