package com.example.henryzheng.qiushibaike.C.main;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import com.example.henryzheng.qiushibaike.C.base.BaseActivity;
import com.example.henryzheng.qiushibaike.MyApplication;
import com.example.henryzheng.qiushibaike.R;

public class MainViewPageActivity extends BaseActivity {


    private long exitTime=0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public int getContentViewById() {
        return R.layout.activity_main_view_page;
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {

            if ((System.currentTimeMillis() - exitTime) > 2000)  //System.currentTimeMillis()
            // 无论何时调用，肯定大于2000
            {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                exitOtherActivity();
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exitOtherActivity() {
        if (MyApplication.activitys != null) {
            for (int i = 0; i < MyApplication.activitys.size(); i++) {
                MyApplication.activitys.get(i).finish();
            }
        }
    }
}
