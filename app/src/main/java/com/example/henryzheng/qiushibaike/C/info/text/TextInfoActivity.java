package com.example.henryzheng.qiushibaike.C.info.text;

import android.content.Intent;
import android.os.Bundle;

import com.example.henryzheng.qiushibaike.C.info.adapt.TextInfoAdapt;
import com.example.henryzheng.qiushibaike.C.info.base.BaseInfoActivity;
import com.example.henryzheng.qiushibaike.C.info.p.BaseInfoHandlerPresenter;
import com.example.henryzheng.qiushibaike.M.bean.text.Items;
import com.example.henryzheng.qiushibaike.M.utils.CCLog;
import com.example.henryzheng.qiushibaike.R;
import com.example.henryzheng.qiushibaike.V.identityView.MyRecycleView;

import butterknife.BindView;

public class TextInfoActivity extends BaseInfoActivity {

    @BindView(R.id.recycleView0)
    public MyRecycleView recycleView0;
    BaseInfoHandlerPresenter presenter;
    private String id;
    private int count = 0;
    private Items items;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CCLog.print(Thread.currentThread().getName());
        handlerData();
        initComment();
    }

    private void handlerData() {
        Intent intent = getIntent();
        items = (Items) intent.getSerializableExtra("data");
        id = items.getId();
        count = items.getCommentsCount();
    }


    @Override
    public int getContentViewById() {
        return R.layout.activity_layout_info;
    }


    private void initComment() {
        presenter = new BaseInfoHandlerPresenter(this, new TextInfoAdapt(this, items),
                recycleView0, id, count);
        presenter.loadListData(BaseInfoHandlerPresenter.REFRESH_DATA_TYPE);
    }

}
