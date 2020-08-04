package com.xiniu.datarecycle.utils.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.xiniu.datarecycle.R;

/**
 * 创建者：wyz
 * 创建时间：2020-07-31
 * 功能描述：
 * 更新者：
 * 更新时间：
 * 更新描述：
 */
public class CartonView extends SurfaceView implements SurfaceHolder.Callback2 {


    private Canvas canvas;
    private SurfaceHolder holder;
    private Paint paint;
    private int width, height;
    private int radius;
    private Context context;
    private float scaleFace = 0.3f;
    private float faceDimissionHeight;
    private float eyeDimension;
    private float eyewidth;
    private float eyeHeight;
    private float scaleEyeWidth = 0.15f;
    private float scaleEyeHeight = 0.16f;
    private float scaleDivide = 0.25f;
    private float padding;
    private HandlerThread handlerThread;
    private Handler worker;

    private float xDimission=0f;
    private double delta = Math.PI / 100;
    private int num = 0;

    private float eyeAutoPosition=0f;


    public CartonView(Context context) {
        this(context, null);
    }

    public CartonView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CartonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        radius = dp2px(context, 80f);
        faceDimissionHeight = getDimission(scaleFace);
        eyewidth = getDimission(scaleEyeWidth);
        eyeDimension = getDimission(scaleDivide);
        eyeHeight = getDimission(scaleEyeHeight);
        padding = 25;
    }


    public void init(Context context) {
        this.context = context;
        holder = getHolder();
        holder.addCallback(this);
        setZOrderOnTop(true);
        holder.setFormat(PixelFormat.TRANSPARENT);
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setAntiAlias(true);
        paint.setShadowLayer(50, 10, 10, getResources().getColor(R.color.color_a9c6d6));
    }

    private int dp2px(Context context, float px) {
        float step = context.getResources().getDisplayMetrics().density;
        return (int) (step * px + 0.5f);
    }

    @Override
    public void surfaceRedrawNeeded(SurfaceHolder holder) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        initThread();
        sendDrawMessage();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        handlerThread.quit();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    public void initThread() {
        handlerThread = new HandlerThread("surfaceView");
        handlerThread.start();
        worker = new Handler(handlerThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 0) {
                    draw();
                    sendDrawMessage();
                }
            }
        };
    }

    public void sendDrawMessage() {
        num = num + 1;
        if (num > 200) {
            num = 0;
        }
        xDimission = 50 * (float) Math.sin(num * delta);
        eyeAutoPosition = 50 * (float) Math.sin(num * delta);
        Message msg1 = Message.obtain();
        msg1.what = 0;
        worker.sendMessageDelayed(msg1, 13);
    }

    public void draw() {
        canvas = getHolder().lockCanvas();
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        canvas.translate(width / 2 +xDimission, height / 2);
        paint.setColor(getResources().getColor(R.color.blue));
        canvas.drawCircle(0, 0, radius, paint);
        paint.setColor(getResources().getColor(R.color.black));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            canvas.drawRoundRect(-radius + padding, -faceDimissionHeight, radius - padding, faceDimissionHeight, faceDimissionHeight, faceDimissionHeight, paint);
        } else {
            RectF rect = new RectF(-radius + padding, -faceDimissionHeight, radius - padding, faceDimissionHeight);
            canvas.drawRoundRect(rect, faceDimissionHeight, faceDimissionHeight, paint);
        }
        paint.setColor(getResources().getColor(R.color.blue));
        RectF rectLeft = new RectF(-eyeDimension - eyewidth + eyeAutoPosition, -eyeHeight, -eyeDimension + eyeAutoPosition, eyeHeight);
        RectF rectRight = new RectF(eyeDimension + eyeAutoPosition, -eyeHeight, eyeDimension + eyewidth + eyeAutoPosition, eyeHeight);
        canvas.drawRoundRect(rectLeft, eyewidth, eyewidth, paint);
        canvas.drawRoundRect(rectRight, eyewidth, eyewidth, paint);
        getHolder().unlockCanvasAndPost(canvas);
    }



    private int getDimission(float scale) {
        return (int) (radius * scale);
    }


}
