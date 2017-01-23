package com.example.henryzheng.qiushibaike.C.info.adapt;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.SeekBar;

import com.example.henryzheng.qiushibaike.C.list.adapt.BaseViewHolder;
import com.example.henryzheng.qiushibaike.M.utils.CCLog;
import com.example.henryzheng.qiushibaike.M.utils.DensityUtils;
import com.example.henryzheng.qiushibaike.R;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by henryzheng on 2017/1/22.
 */
public class VideoInfoAdapt extends BaseInfoAdapt {
    private SurfaceView surfaceView;
    private SeekBar seekBar;
    private MediaPlayer mediaPlayer;
    public String uri;
    private TimerTask mTimerTask;
    private Timer mTimer;
    private boolean isChanging = false;
    com.example.henryzheng.qiushibaike.M.bean.video.Items headItemData;
    /**
     * @param context //上下文
     */
    public VideoInfoAdapt(Context context, com.example.henryzheng.qiushibaike.M.bean.video.Items headItemData) {
        super(context);
        this.uri =headItemData.getHigh_url();
        this.headItemData=headItemData;
    }
    @Override
    public void convertByFloor(BaseViewHolder holder) {
        super.convertByFloor(holder);
        holder.setVisability(R.id.relativeLayout0, View.GONE);
    }

    @Override
    public int getHeadViewById() {
        return R.layout.activity_video_info;
    }
    @Override
    public void convertByHead(BaseViewHolder holder) {
        super.convertByHead(holder);
        initView(holder);
        initDataAndListener();
    }

    private void initView(BaseViewHolder holder) {
        surfaceView = (SurfaceView) holder.getViewById(R.id.surfaceView);
        seekBar = (SeekBar) holder.getViewById(R.id.seekBar);
        int lw = DensityUtils.getSceenWidth(context);
        float vWidth=lw*headItemData.getImage_size().getM().get(0);
        float vHeight=lw*headItemData.getImage_size().getM().get(1);
        int lh= (int) (lw/vWidth*vHeight);
        holder.setLayoutParams(R.id.surfaceView,lw,lh);
        if (headItemData.getUser() != null) {
            if(headItemData.getUser().getLogin() != null)
                holder.setText(R.id.textView0, headItemData.getUser().getLogin());

            holder.setImageResource(R.id.imageView0, "http:" + headItemData.getUser().getThumb(), true);
        }
        holder.setText(R.id.textView1, headItemData.getContent());
        holder.setText(R.id.textView2,headItemData.getVotes().getUp());
        holder.setText(R.id.textView3, String.valueOf(headItemData.getComments_count()));
        holder.setText(R.id.textView4,headItemData.getShare_count());
        holder.setText(R.id.textView5,headItemData.getLoop());
        holder.setText(R.id.textView6,headItemData.getComments_count());

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
                seekBar.setMax(mediaPlayer.getDuration());

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
                    mediaPlayer.setDataSource(context, Uri.parse(uri));
                    //prepare();表示准备工作同步进行，（准备工作在UI线程中进行）
                    //当播放网络视频时，如果网络不要 会报ARN 所以不采用该方法
                    //mediaPlayer.prepare();
                    //异步准备 准备工作在子线程中进行 当播放网络视频时候一般采用此方法
                    mediaPlayer.prepareAsync();
                } catch (IOException e) {
                    e.printStackTrace();
                }catch (IllegalStateException e){
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
                    try {
                        mediaPlayer.stop();

                        //释放资源
                        mediaPlayer.release();
                    } catch (IllegalStateException e) {

                    }
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
                                                       CCLog.print("progressChange:" + progress);

                                                       mediaPlayer.seekTo(progress);
                                                       CCLog.print("mediaPlayer seekTo:" +
                                                               mediaPlayer.getCurrentPosition());
                                                   }
                                               }

                                               @Override
                                               public void onStartTrackingTouch(SeekBar seekBar) {
                                                   isChanging = true;
                                                   CCLog.print("total:" + seekBar.getMax());

                                                   CCLog.print("start:" + seekBar.getProgress());
                                               }

                                               @Override
                                               public void onStopTrackingTouch(SeekBar seekBar) {
                                                   CCLog.print("stop:" + seekBar.getProgress());

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
                try {
                    CCLog.print("player position:" + mediaPlayer.getCurrentPosition());
                    seekBar.setProgress(mediaPlayer.getCurrentPosition());
                } catch (IllegalStateException e) {

                }

            }
        };
        mTimer.schedule(mTimerTask, 0, 10);
    }
}
