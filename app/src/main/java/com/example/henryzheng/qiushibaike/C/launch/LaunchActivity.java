package com.example.henryzheng.qiushibaike.C.launch;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.henryzheng.qiushibaike.C.base.BaseActivity;
import com.example.henryzheng.qiushibaike.C.main.MainViewPageActivity;
import com.example.henryzheng.qiushibaike.R;

import butterknife.BindView;

public class LaunchActivity extends BaseActivity {

    @BindView(R.id.imageView0)
    ImageView imageView0;
    private long delayTime = 1000;

    @Override
    public int getContentViewById() {
        return R.layout.activity_launch;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Glide.with(this).load(R.drawable.logo).into(imageView0);
        setBgScale();
    }


    private void setBgScale() {
//        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("scaleX", 1f,
//                1f);
//        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleY", 1f,
//                1f);
//        ObjectAnimator animator=  ObjectAnimator.ofPropertyValuesHolder(imageView0, pvhX,pvhY)
// .setDuration( delayTime);
//        animator.addListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                startActivity(new Intent(context, MainViewPageActivity.class));
//
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animation) {
//
//            }
//        });

//        animator.start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(context, MainViewPageActivity.class));

            }
        },1000);
    }
}
