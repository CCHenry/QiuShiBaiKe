package com.example.henryzheng.qiushibaike.C.info.video;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import com.example.henryzheng.qiushibaike.C.List.i.MainFragmentInterface;
import com.example.henryzheng.qiushibaike.C.info.base.BaseInfoActivity;
import com.example.henryzheng.qiushibaike.C.info.adapt.InfoCommentAdapt;
import com.example.henryzheng.qiushibaike.C.info.p.BaseInfoHandlerPresenter;
import com.example.henryzheng.qiushibaike.M.Bean.video.Items;
import com.example.henryzheng.qiushibaike.M.utils.CCLog;
import com.example.henryzheng.qiushibaike.R;
import com.example.henryzheng.qiushibaike.V.identityView.MyRecycleView;

import java.io.File;
import java.io.IOException;
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CCLog.print(Thread.currentThread().getName());

        handlerData();
//        initDataAndListener();
        initComment();
    }



    private void handlerData() {
        Intent intent = getIntent();
        Items items = (Items) intent.getSerializableExtra("data");
        uri = items.getHigh_url();
        id=items.getId();
        count = items.getComments_count();
    }


    @Override
    public int getContentViewById() {
        return R.layout.activity_video_info;
    }

    private void initDataAndListener() {
        mediaPlayer = new MediaPlayer();

        initMediaPlayer();

        initSurfaceHolder();

        initSurfaceView();

        initSeekBar();

    }

    private void initMediaPlayer() {
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                //准备完成后播放
                // 首先取得video的宽和高
                int vWidth = mediaPlayer.getVideoWidth();
                int vHeight = mediaPlayer.getVideoHeight();
                seekBar.setMax(mediaPlayer.getDuration());

                // 该LinearLayout的父容器 android:orientation="vertical" 必须
                LinearLayout linearLayout = (LinearLayout) findViewById(R.id.lin1);
                int lw = linearLayout.getWidth();
                int lh = linearLayout.getHeight();

//                if (vWidth > lw || vHeight > lh) {
                // 如果video的宽或者高超出了当前屏幕的大小，则要进行缩放
                float wRatio = (float) vWidth / (float) lw;
                float hRatio = (float) vHeight / (float) lh;

                // 选择大的一个进行缩放
                float ratio = Math.max(wRatio, hRatio);
                vWidth = (int) Math.ceil((float) vWidth / ratio);
                vHeight = (int) Math.ceil((float) vHeight / ratio);

                // 设置surfaceView的布局参数
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(vWidth, vHeight);
                lp.gravity = Gravity.CENTER;
                surfaceView.setLayoutParams(lp);
//                }
                // 然后开始播放视频
                mediaPlayer.start();
            }
        });

    }

    private void initSurfaceHolder() {
        //获取SurfaceHolder 可以通过该接口来操作SurfaceView中的Surface
        SurfaceHolder surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            //当SurfaceView中Surface创建时回掉
            //该方法表示Surface已经创建完成，可以在该方法中进行绘图操作
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                mediaPlayer.reset();
                try {
                    //设置视屏文件图像的显示参数
                    mediaPlayer.setDisplay(holder);

                    //file.getAbsolutePath()本地视频
                    //uri 网络视频
                    mediaPlayer.setDataSource(VideoInfoActivity.this, Uri.parse(uri));
                    //prepare();表示准备工作同步进行，（准备工作在UI线程中进行）
                    //当播放网络视频时，如果网络不要 会报ARN 所以不采用该方法
                    //mediaPlayer.prepare();
                    //异步准备 准备工作在子线程中进行 当播放网络视频时候一般采用此方法
                    mediaPlayer.prepareAsync();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            //当SurfaceView的大小发生改变时候触发该方法
            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            //Surface销毁时回掉
            //当Surface销毁时候，同时把MediaPlayer也销毁
            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                    //释放资源
                    mediaPlayer.release();
                }
            }
        });
    }

    private void initSurfaceView() {
        //设置 surfaceView点击监听
        surfaceView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (mediaPlayer.isPlaying()) {
                            mediaPlayer.pause();
                        } else {
                            mediaPlayer.start();
                        }
                        break;
                }
                //返回True代表事件已经处理了
                return true;
            }
        });
    }


    private void initSeekBar() {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                                               @Override
                                               public void onProgressChanged(SeekBar seekBar, int
                                                       progress, boolean fromUser) {
                                                   if (isChanging) {
                                                       CCLog.print("progressChange:"+progress);

                                                       mediaPlayer.seekTo(progress);
                                                       CCLog.print("mediaPlayer seekTo:"+mediaPlayer.getCurrentPosition());
                                                   }
                                               }

                                               @Override
                                               public void onStartTrackingTouch(SeekBar seekBar) {
                                                   isChanging = true;
                                                   CCLog.print("total:"+seekBar.getMax());

                                                   CCLog.print("start:"+seekBar.getProgress());
                                               }

                                               @Override
                                               public void onStopTrackingTouch(SeekBar seekBar) {
                                                   CCLog.print("stop:"+seekBar.getProgress());

                                                   isChanging = false;
                                               }

                                           }

        );
        seekBar.setMax(mediaPlayer.getDuration());//设置进度条
        //----------定时器记录播放进度---------//
        mTimer = new Timer();
        mTimerTask = new TimerTask() {
            @Override
            public void run() {
                if (isChanging == true) {
                    return;
                }
                CCLog.print("player position:"+mediaPlayer.getCurrentPosition());
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
            }
        };
        mTimer.schedule(mTimerTask, 0, 10);
    }
    private void initComment() {
        presenter=new BaseInfoHandlerPresenter(this,this,new InfoCommentAdapt(this,uri),recycleView0,id,count);
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
