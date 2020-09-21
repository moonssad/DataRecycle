package com.xiniu.datarecycle.mediaplay;

/**
 * Data 2020/9/8
 * author wyz
 **/

import android.graphics.Outline;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Bundle;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.xiniu.datarecycle.R;

import java.io.IOException;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class mediaTestActivity extends AppCompatActivity {

    private Button play,pause,stop;
    private Boolean noPlay=true;//定义播放状态
    private MediaPlayer mediaPlayer;
    private SurfaceHolder surfaceHolder;
    SurfaceView surfaceView;
    private  VideoSurfaceView surface;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.media_layout);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
                ,WindowManager.LayoutParams.FLAG_FULLSCREEN);//全屏显示
        //控制视频的按钮
        play=findViewById(R.id.play);
        pause=findViewById(R.id.pasue);
        stop=findViewById(R.id.stop);
        surfaceView=findViewById(R.id.surfaceView);
        surface = findViewById(R.id.video);
        surfaceHolder=surfaceView.getHolder();//获取surfaceHolder
        mediaPlayer=new MediaPlayer();//创建MediaPlayer对象
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);//设置多媒体类型
        mediaPlayer.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mediaPlayer, int i, int i1) {
                return false;
            }
        });
        try {
            mediaPlayer.setDataSource(getApplicationContext(), Uri.parse("https://dss0.bdstatic.com/-0U0bnSm1A5BphGlnYG/cae-legoup-video-target/9eacd07c-ac8f-472b-a4e2-ffee1a47a348.mp4"));
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    Log.e( "onPrepared: ","开始播放");
//                    Animation animations = new AlphaAnimation(0,1);
//                    animations.setDuration(60);
//                    surface.startAnimation(animations);
                    mediaPlayer.start();
                    surface.setVisibility(View.VISIBLE);
                    surface.setVideoAspectRatio((float) mediaPlayer.getVideoWidth() /
                            (float) mediaPlayer.getVideoHeight());
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        surface.setCornerRadius(30f);
        surface.setMediaPlayer(mediaPlayer);
        mediaPlayer.prepareAsync();
        surface.setVisibility(View.INVISIBLE);
        setOutline(play);
        setOutline(pause);
        setOutline(stop);
        setOutline(surfaceView);


//        surface = new SurfaceView(this);
//        WindowManager windowManager =(WindowManager) getSystemService(WINDOW_SERVICE);
//        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
//        layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
//        layoutParams.width = WRAP_CONTENT;
//        layoutParams.height = 300;
//        layoutParams.x =0;
//        layoutParams.y = 0;
//        windowManager.addView(surface,layoutParams);
//        setOutline(surface);
//        surfaceHolder=surface.getHolder();//获取surfaceHolder
        //播放
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Toast.makeText(mediaTestActivity.this,"dianji",Toast.LENGTH_SHORT).show();
                if(noPlay){

                    noPlay=false;
                    play();
                }else{
                    mediaPlayer.start();//继续播放视频
                }
            }
        });
        //暂停
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                }
            }
        });
        //停止
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                    noPlay=true;//视频处于没有播放状态
                }
            }
        });
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(mediaTestActivity.this, "视频播放完毕", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void play(){

        mediaPlayer.reset();//重置MediaPlayer
//        mediaPlayer.setDisplay(surfaceHolder);//把视频画面输出到SurfaceView中
        try {
//            mediaPlayer.setDataSource(Environment.
//                    getExternalStorageDirectory()+"/a.mp4");//设置要播放的内容在根目录下的位置
            mediaPlayer.setDataSource(getApplicationContext(), Uri.parse("https://dss0.bdstatic.com/-0U0bnSm1A5BphGlnYG/cae-legoup-video-target/9eacd07c-ac8f-472b-a4e2-ffee1a47a348.mp4"));
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                    mediaPlayer.setDisplay(surfaceHolder);
                    surface.setVideoAspectRatio((float) mediaPlayer.getVideoWidth() /
                            (float) mediaPlayer.getVideoHeight());
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    @Override
    protected void onDestroy() {
        if(mediaPlayer!=null){
            if(mediaPlayer.isPlaying()){
                mediaPlayer.stop();
            }
            mediaPlayer.release();
        }
        super.onDestroy();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void setOutline(View view){
        view.setOutlineProvider(new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                Rect rect = new Rect();
                view.getGlobalVisibleRect(rect);
                Log.e( "getOutline: ", rect.bottom+"|"+rect.top);
                int leftMargin = 0;
                int topMargin = 0;
                Rect selfRect = new Rect(leftMargin+30, topMargin+30,
                        rect.right - rect.left - leftMargin-30, rect.bottom - rect.top - topMargin-30);
                outline.setRoundRect(selfRect, 30);
//                view.setClipBounds(new Rect(leftMargin+30, topMargin+30,
//                        rect.right - rect.left - leftMargin-30, rect.bottom - rect.top - topMargin-30));
            }
        });
        view.setClipToOutline(true);
    }
}
