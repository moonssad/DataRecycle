package com.xiniu.datarecycle.baseNoModel.Base;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 创建者：wyz
 * 创建时间：2020-07-15
 * 功能描述：
 * 更新者：
 * 更新时间：
 * 更新描述：
 */
public abstract class BasePresenter<V extends IView, M extends IModel> implements IPresenter {
    private CompositeDisposable compositeDisposable;
    public V mView;
    public M mModel;


    public BasePresenter(V view) {
        mView = view;
        mModel = getModel();
    }


    public void ondestroy() {
        onDestroy();
        if (compositeDisposable != null) {
            compositeDisposable.clear();
            compositeDisposable = null;
        }
    }

    public <T> void getDataAll(onCallBack<T> callback, onDoSubscribe subscribe, String name) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(
                Observable.create(new ObservableOnSubscribe<T>() {
                    @Override
                    public void subscribe(ObservableEmitter<T> emitter) throws Exception {
                        T result = (T) subscribe.doSubscribe(name);
                        emitter.onNext(result);
                    }
                })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(callback::onSuccess, callback::onFail)
        );
    }


    //显示log
    public void showLog(String logs) {
        Log.e(this.getClass().getSimpleName(), logs);
    }


    public interface onCallBack<T> {
        void onSuccess(T t);

        void onFail(Throwable throwable);
    }

    public interface onDoSubscribe<T> {
        T doSubscribe(String name);
    }

    public abstract M getModel();
}
