package com.xiniu.datarecycle.utils.Views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.xiniu.datarecycle.CarHandApplication;
import com.xiniu.datarecycle.R;

import static android.graphics.Paint.Style.FILL_AND_STROKE;

/**
 * 创建者：wyz
 * 创建时间：2020-08-03
 * 功能描述：
 * 更新者：
 * 更新时间：
 * 更新描述：
 */
public class CarView extends SurfaceView implements SurfaceHolder.Callback2 {
    private HandlerThread handlerThread;
    private Handler worker;
    private Canvas canvas;
    private SurfaceHolder holder;
    private Bitmap carView;
    private Bitmap[] buildings;
    private Paint paint;
    private int width, height;
    private int LeftPosition, RightPosition;
    private int BuindingWidth;
    private int transDimission=0;

    public CarView(Context context) {
        this(context, null);
    }

    public CarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public CarView(Context context, AttributeSet attrs, int defStyleAttr) {
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

                    draw();
                    sendDrawMessage();
                }
            }
        };
    }

    public void sendDrawMessage() {
        transDimission = transDimission+3;
        if (transDimission> Math.abs(RightPosition - LeftPosition)){
            transDimission=0;
        }
        Message msg1 = Message.obtain();
        msg1.what = 1;
        worker.sendMessageDelayed(msg1, 16);
    }

    public void draw() {
        canvas = getHolder().lockCanvas();
        if(canvas!=null){
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        canvas.translate(width / 2, height * 0.8f);
        canvas.save();
        canvas.translate(-transDimission,0);
        for (int i = 0; i < buildings.length; i++) {
            RectF rectF = new RectF(LeftPosition + i * BuindingWidth, -carView.getHeight(), LeftPosition + (i + 1) * BuindingWidth, 0);
            canvas.drawBitmap(buildings[i], null, rectF, null);
        }
        canvas.restore();
        RectF rect = new RectF(-carView.getWidth() / 2, -carView.getHeight(), carView.getWidth() / 2, carView.getHeight() / 4);
        canvas.drawBitmap(carView, null, rect, paint);
        getHolder().unlockCanvasAndPost(canvas);}
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        LeftPosition = -200 - carView.getWidth() / 2;
        RightPosition = 200 + carView.getWidth() / 2;
        BuindingWidth = Math.abs(RightPosition - LeftPosition) / buildings.length;
        setMeasuredDimension(width, height);
    }


    public Bitmap getBitmap(int ResId) {
        return BitmapFactory.decodeResource(getResources(), ResId);
    }




}
