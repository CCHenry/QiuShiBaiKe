package com.example.henryzheng.qiushibaike.C.info.adapt;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import com.example.henryzheng.qiushibaike.C.List.adapt.BaseListAdapt;
import com.example.henryzheng.qiushibaike.C.List.adapt.BaseViewHolder;
import com.example.henryzheng.qiushibaike.M.Bean.infoComment.Items;
import com.example.henryzheng.qiushibaike.M.utils.CCLog;
import com.example.henryzheng.qiushibaike.R;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by henryzheng on 2017/1/20.
 */
public class InfoCommentAdapt extends BaseListAdapt {
    private SurfaceView surfaceView;
    private SeekBar seekBar;
    private MediaPlayer mediaPlayer;
    public String uri;
    private TimerTask mTimerTask;
    private Timer mTimer;
    private boolean isChanging = false;

    /**
     * @param context //上下文
     */
    public InfoCommentAdapt(Context context, String uri) {
        super(context);
        this.uri = uri;
    }

    @Override
    protected void convert(BaseViewHolder holder, Object bean) {
        Items data = (Items) bean;
        if (data.getUser() != null)
            if (data.getUser().getLogin() != null) {
                holder.setText(R.id.textView0, data.getUser().getLogin());
                holder.setText(R.id.textView1, data.getLikeCount());
                holder.setText(R.id.textView2, data.getContent());
                holder.setImageResource(R.id.imageView0,"http:"+data.getUser().getThumb(),true);
                if (data.getRefer()!=null){
                if (data.getRefer().getUser() != null && data.getRefer().getUser().getLogin() !=
                        null) {
                    holder.setText(R.id.textView3, data.getRefer().getUser().getLogin());
                    holder.setText(R.id.textView4, data.getRefer().getContent());
                }}else{
                    holder.setVisability(R.id.linearLayout0, View.GONE);
                }
            }
    }

    @Override
    protected int getLayoutItemLayout() {
        return R.layout.recycle_view_info_comment_item;
    }


    @Override
    public int getHeadViewById() {
        return R.layout.activity_video_info_head;
    }

    @Override
    public void convertByHead(BaseViewHolder holder) {
        super.convertByHead(holder);
        surfaceView = (SurfaceView) holder.getViewById(R.id.surfaceView);
        seekBar = (SeekBar) holder.getViewById(R.id.seekBar);
        initDataAndListener();
    }

    @Override
    public void convertByBottom(BaseViewHolder holder) {
        super.convertByBottom(holder);
        holder.setVisability(R.id.relativeLayout0, View.GONE);
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
                LinearLayout linearLayout = (LinearLayout) ((Activity) context).findViewById(R.id
                        .lin1);
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
                    mediaPlayer.setDataSource(context, Uri.parse(uri));
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
