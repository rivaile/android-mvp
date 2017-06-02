package com.oldnum7.base;

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

    @Override
    public void onNext(@NonNull T t) {
        /* no-op */
    }

    @Override
    public void onError(@NonNull Throwable e) {
        /*no-op*/

    }

    @Override
    public void onComplete() {
        /* no-op */

    }
}
