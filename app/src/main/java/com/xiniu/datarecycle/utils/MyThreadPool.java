package com.xiniu.datarecycle.utils;

import android.util.Log;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import io.reactivex.internal.schedulers.SchedulerPoolFactory;

/**
 * Data 2020/9/14
 * author 线程池学习。
 **/
public class MyThreadPool {
    static final ArrayBlockingQueue<Runnable> timestamps = new ArrayBlockingQueue<Runnable>(100);
    static final RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();

    static ThreadPoolExecutor service = new ThreadPoolExecutor(9, 8 * 2, 5, TimeUnit.MILLISECONDS,
            timestamps, Executors.defaultThreadFactory(), handler);

    public static void doRun() {
        for (int i = 0; i < 100; i++) {
            service.execute(new MyRunable(i + ""));
        }
    }

    public

    static class MyRunable implements Runnable {

        String text;

        public MyRunable(String text) {
            this.text = text;
        }

        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            try {
                Thread.sleep(1000);
                Log.e("run: ", String.format("当前线程名%s，第%s次运行", name, text));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
