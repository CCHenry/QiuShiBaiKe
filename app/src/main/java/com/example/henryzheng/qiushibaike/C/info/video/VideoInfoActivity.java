package com.example.henryzheng.qiushibaike.C.info.video;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.SurfaceView;
import android.widget.SeekBar;

import com.example.henryzheng.qiushibaike.C.list.i.MainFragmentInterface;
import com.example.henryzheng.qiushibaike.C.info.adapt.VideoInfoCommentAdapt;
import com.example.henryzheng.qiushibaike.C.info.base.BaseInfoActivity;
import com.example.henryzheng.qiushibaike.C.info.p.BaseInfoHandlerPresenter;
import com.example.henryzheng.qiushibaike.M.bean.video.Items;
import com.example.henryzheng.qiushibaike.M.utils.CCLog;
import com.example.henryzheng.qiushibaike.R;
import com.example.henryzheng.qiushibaike.V.identityView.MyRecycleView;

import java.io.File;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;

public class VideoInfoActivity extends BaseInfoActivity implements MainFragmentInterface {
    private MediaPlayer mediaPlayer;
//    @BindView(R.id.surfaceView)
    public SurfaceView surfaceView;
//    @BindView(R.id.seekBar)
    public SeekBar seekBar;
    @BindView(R.id.recycleView0)
    public MyRecycleView recycleView0;
    //读取本地文件
    private File file = new File("/storage/sdcard1/音乐/", "曾经的你.mp4");
    //访问网络视频
    private String uri = "";
    private TimerTask mTimerTask;
    private Timer mTimer;
    private boolean isChanging = false;
    BaseInfoHandlerPresenter presenter;
    private String id;
    private int count=0;
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
        uri = items.getHigh_url();
        id=items.getId();
        count = items.getComments_count();
    }


    @Override
    public int getContentViewById() {
        return R.layout.activity_video_info;
    }




    private void initComment() {
        presenter=new BaseInfoHandlerPresenter(this,this,new VideoInfoCommentAdapt(this,items),recycleView0,id,count);
        presenter.loadListData(BaseInfoHandlerPresenter.REFRESH_DATA_TYPE);
    }
    @Override
    protected void onPause() {
        super.onPause();
        isChanging = true;

    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void loadNewData(List datas) {

    }

    @Override
    public void refreshData(List datas) {

    }
}
