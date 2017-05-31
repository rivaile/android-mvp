package com.oldnum7.mvp;

import android.support.annotation.UiThread;

/**
 * author : denglin
 * time   : 2017/05/31/13:55
 * desc   :
 * version: 1.0
 */
public interface MvpPresenter<V extends MvpView> {

    @UiThread
    void attachView(V view);

    /**
     * detach this view attached to the presenter.
     */
    @UiThread
    void detachView();

    /**
     * Get attached view if the view is already attached to the presenter. You should always call
     * isViewAttached() method to check if the view is attached before calling this method.
     *
     * @return null, if view is not attached, otherwise the concrete view instance.
     */
    @UiThread
    V getView();

    @UiThread
    boolean isViewAttached();
}
