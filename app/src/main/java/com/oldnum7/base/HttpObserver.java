package com.oldnum7.base;

import android.util.Log;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

/**
 * <pre>
 *       author : denglin
 *       time   : 2017/06/02/17:28
 *       desc   :
 *       version: 1.0
 * </pre>
 */
// TODO: 2017/6/2 封装异常信息
public class HttpObserver<T> extends DisposableObserver<T> {

    private static final String TAG = "";

    @Override
    public void onNext(@NonNull T t) {
        /* no-op */
    }

    @Override
    public void onError(@NonNull Throwable e) {
        /*no-op*/
        Log.e(TAG, "onError: " + e.toString());
    }

    @Override
    public void onComplete() {
        /* no-op */
        Log.e(TAG, "onComplete: ");
    }
}
