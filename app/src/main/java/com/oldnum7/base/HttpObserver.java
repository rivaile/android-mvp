package com.oldnum7.base;

import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.oldnum7.exception.HttpException;
import com.oldnum7.status.StatusLayoutManager;

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

public class HttpObserver<T> extends DisposableObserver<T> {

    private final String TAG = getClass().getSimpleName();
    private StatusLayoutManager statusLayoutManager;

    public HttpObserver(StatusLayoutManager statusLayoutManager) {
        this.statusLayoutManager = statusLayoutManager;
    }

    @Override
    protected void onStart() {

    }

    @Override
    public void onNext(@NonNull T t) {

    }

    @Override
    public void onError(@NonNull Throwable e) {

        if (!NetworkUtils.isConnected()) {
            ToastUtils.showLong("网络连接失败...");
            onRequestError();
        } else if (e instanceof HttpException) {
            ToastUtils.showLong(e.getMessage());
            onRequestFailure();
        } else {
            ToastUtils.showLong("服务器小情绪...");
            onRequestFailure();
        }
        onFinally();
    }

    @Override
    public void onComplete() {
        statusLayoutManager.showContent();
        onFinally();
    }

    public void onFinally() {

    }

    public void onRequestFailure() {

    }

    public void onRequestError() {

    }
}
