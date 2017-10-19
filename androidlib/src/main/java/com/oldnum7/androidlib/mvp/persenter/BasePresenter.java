package com.oldnum7.androidlib.mvp.persenter;

import android.support.annotation.Nullable;
import android.support.annotation.UiThread;

import com.oldnum7.androidlib.mvp.view.MvpView;

import java.lang.ref.WeakReference;

/**
 * <pre>
 *      author : denglin
 *      time   : 2017/05/31/13:55
 *      desc   :  A base presenter that uses a WeakReference for referring to the attached view.
 *                Should always check isViewAttached() to check if the view is attached to this
 *                presenter before calling getView() to access the view.
 *      version: 1.0
 * </pre>
 */
public abstract class BasePresenter<V extends MvpView> implements MvpPresenter<V>,IPresenter {

    private WeakReference<V> mViewReference;

    @UiThread
    @Override
    public void attachView(V view) {
        this.mViewReference = new WeakReference<V>(view);
    }

    /**
     * detach this view attached to the presenter.
     */
    @UiThread
    @Override
    public void detachView() {
        if (mViewReference != null) {
            mViewReference.clear();
            mViewReference = null;
        }
    }

    /**
     * Get attached view if the view is already attached to the presenter. You should always call
     * isViewAttached() method to check if the view is attached before calling this method.
     *
     * @return null, if view is not attached, otherwise the concrete view instance.
     */
    @UiThread
    @Nullable
    @Override
    public V getView() {
        return mViewReference == null ? null : mViewReference.get();
    }

    /**
     * Checks if a view is attached to this presenter.
     *
     * @return false, if view is not attached, otherwise returns true.
     */
    @UiThread
    @Override
    public boolean isViewAttached() {
        return mViewReference != null && mViewReference.get() != null;
    }

}
