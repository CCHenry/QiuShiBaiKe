package com.example.henryzheng.qiushibaike.C.info;

import android.content.Intent;
import android.os.Bundle;

import com.example.henryzheng.qiushibaike.C.info.adapt.VideoInfoAdapt;
import com.example.henryzheng.qiushibaike.C.info.base.BaseInfoActivity;
import com.example.henryzheng.qiushibaike.C.info.p.BaseInfoHandlerPresenter;
import com.example.henryzheng.qiushibaike.M.bean.video.Items;
import com.example.henryzheng.qiushibaike.M.utils.CCLog;
import com.example.henryzheng.qiushibaike.R;
import com.example.henryzheng.qiushibaike.V.identityView.MyRecycleView;

import butterknife.BindView;

/**
 * Created by henryzheng on 2017/1/22.
 */
public class BaseInfoTestActivity extends BaseInfoActivity {

    @BindView(R.id.recycleView0)
    public MyRecycleView recycleView0;
    private String uri = "";
    BaseInfoHandlerPresenter presenter;
    private String id;
    private int count = 0;
    private Items items;

    public static class ActivityInfoType {
        public static final int video = 0;
        public static final int news = 1;
        public static final int text = 2;
        public static final int image = 3;
    }

   public static int myActivityType=0;

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
        uri = items.getHigh_url();
        id = items.getId();
        count = items.getComments_count();
    }


    @Override
    public int getContentViewById() {
        return R.layout.activity_layout_info;
    }


    private void initComment() {
        switch (myActivityType){
            case ActivityInfoType.video:
                presenter = new BaseInfoHandlerPresenter(this, new VideoInfoAdapt(this, items),
                        recycleView0, id, count);
                presenter.loadListData(BaseInfoHandlerPresenter.REFRESH_DATA_TYPE);
                break;
            case ActivityInfoType.image:
                presenter = new BaseInfoHandlerPresenter(this, new VideoInfoAdapt(this, items),
                        recycleView0, id, count);
                presenter.loadListData(BaseInfoHandlerPresenter.REFRESH_DATA_TYPE);
                break;
//            case ActivityInfoType.text:
//                presenter = new BaseInfoHandlerPresenter(this, new TextInfoAdapt(this, items),
//                        recycleView0, id, count);
//                presenter.loadListData(BaseInfoHandlerPresenter.REFRESH_DATA_TYPE);
//                break;
        }

    }
}