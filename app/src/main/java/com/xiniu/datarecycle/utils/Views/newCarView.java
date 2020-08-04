package com.xiniu.datarecycle.utils.Views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.xiniu.datarecycle.CarHandApplication;
import com.xiniu.datarecycle.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建者：wyz
 * 创建时间：2020-08-04
 * 功能描述：
 * 更新者：
 * 更新时间：
 * 更新描述：
 */
public class newCarView extends SurfaceView implements SurfaceHolder.Callback2 {
    private HandlerThread handlerThread;
    private Handler worker;
    private Canvas canvas;
    private SurfaceHolder holder;
    private Bitmap carView;
    private Bitmap[] buildings;
    private Paint paint;
    private int width, height;
    private int buindingWidth = 100;
    private List<Buildings> lists;
    private int step = 3;
    private boolean isStop;

    private Paint sunPaint;
    private float sunRadius;
    private double sunDelta;
    private double sunStep = 0;

    public newCarView(Context context) {
        this(context, null);
    }

    public newCarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public newCarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        holder = getHolder();
        holder.addCallback(this);
        setZOrderOnTop(true);
        holder.setFormat(PixelFormat.TRANSPARENT);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        carView = getBitmap(R.drawable.car);
        TypedArray typeArray = CarHandApplication.getContext().getResources().obtainTypedArray(R.array.road_buildings);
        buildings = new Bitmap[typeArray.length()];
        for (int i = 0; i < typeArray.length(); i++) {
            buildings[i] = getBitmap(typeArray.getResourceId(i, R.drawable.tree1));
        }
        typeArray.recycle();
        lists = new ArrayList<>();

        sunPaint = new Paint();
        sunPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        sunPaint.setAntiAlias(true);
        sunPaint.setColor(getResources().getColor(R.color.yellow));
        sunPaint.setShadowLayer(100, 0, 0, getResources().getColor(R.color.white));
        sunRadius = 60;
        sunDelta = Math.PI / 300;
        sunStep = sunDelta * 30;


    }

    @Override
    public void surfaceRedrawNeeded(SurfaceHolder holder) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        isStop = false;
        initThread();
        sendDrawMessage();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isStop = true;
        handlerThread.quit();
        carView.recycle();
        carView = null;
        for (int i = 0; i < buildings.length; i++) {
            buildings[i].recycle();
            buildings[i] = null;
        }
    }


    public void initThread() {
        handlerThread = new HandlerThread("surfaceView");
        handlerThread.start();
        worker = new Handler(handlerThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 1) {
                    if (!isStop) {
                        draw();
                        sendDrawMessage();
                    }
                }
            }
        };
    }

    public void sendDrawMessage() {
        sunStep = sunStep + sunDelta;
        if (sunStep > (Math.PI - sunDelta * 30)) {
            sunStep = sunDelta * 30;
        }
        Message msg1 = Message.obtain();
        msg1.what = 1;
        worker.sendMessageDelayed(msg1, 16);
    }

    public void draw() {
        canvas = getHolder().lockCanvas();
        if (canvas != null) {
            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

            for (Buildings list : lists) {
                canvas.drawBitmap(list.getBitmap(), null, list.getPosition(), paint);
                list.updata(step);
            }
            canvas.translate(width / 2, height * 0.8f);
            RectF rect = new RectF(-carView.getWidth() / 2, -carView.getHeight(), carView.getWidth() / 2, carView.getHeight() / 4);
            canvas.drawBitmap(carView, null, rect, paint);
            canvas.drawCircle((float) ((width / 2) * Math.cos(sunStep)), -(float) ((width / 2) * Math.sin(sunStep)), sunRadius -(float) (sunRadius/3 * Math.cos(sunStep*2)), sunPaint);
            getHolder().unlockCanvasAndPost(canvas);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        buindingWidth = width / buildings.length;
        if (lists.size() == 0) {
            for (int i = 0; i < buildings.length; i++) {
                RectF rect = new RectF(i * buindingWidth, height * 0.8f - carView.getHeight(), i * buindingWidth + buindingWidth, height * 0.8f + carView.getHeight() / 4);
                lists.add(new Buildings(rect, buildings[i], width, buindingWidth));
            }
        }
        Log.e("onMeasure: ", width + "|" + height);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.e("onLayout: ", width + "|" + height);
    }

    public Bitmap getBitmap(int ResId) {
        return BitmapFactory.decodeResource(getResources(), ResId);
    }


}
