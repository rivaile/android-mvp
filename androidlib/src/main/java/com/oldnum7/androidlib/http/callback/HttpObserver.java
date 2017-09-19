package com.oldnum7.androidlib.http.callback;

import android.util.MalformedJsonException;

import com.google.gson.JsonSyntaxException;
import com.oldnum7.androidlib.http.exception.HttpException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

/**
 * <pre>
 *       author : denglin
 *       time   : 2017/06/02/17:28
 *       desc   :封装异常信息
 *       version: 1.0
 * </pre>
 */

public abstract class HttpObserver<T> extends DisposableObserver<T> {

    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onStart() {
    }

    @Override
    public abstract void onNext(@NonNull T t);

    @Override
    public void onError(@NonNull Throwable e) {

        if (e instanceof SocketTimeoutException || e instanceof ConnectException || e instanceof UnknownHostException) {
            e.printStackTrace();
        } else if (e instanceof MalformedJsonException || e instanceof JsonSyntaxException) {
            e.printStackTrace();
        } else if (e instanceof HttpException) {//此处可以对统一的错误进行处理...
            e.printStackTrace();
        } else {
            e.printStackTrace();
        }
//        if (!NetworkUtils.isConnected()) {
//            ToastUtils.showLong("网络连接失败...");
//            onRequestError();
//        } else if (e instanceof HttpException) {
//            ToastUtils.showLong(e.getMessage());
//            onRequestFailure();
//        } else {
//            ToastUtils.showLong("服务器小情绪...");
//            onRequestFailure();
//        }
        onFinish();
    }

    @Override
    public void onComplete() {
        onFinish();
    }

    public void onFinish() {
    }

//    public void onRequestFailure() {
//
//    }
//
//    public void onRequestError() {
//
//    }
}
