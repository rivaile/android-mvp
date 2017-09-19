package com.oldnum7.androidlib.mvp.delegate;

import android.os.Bundle;

import com.oldnum7.androidlib.mvp.persenter.BasePresenter;
import com.oldnum7.androidlib.mvp.view.BaseView;


/**
 * The concrete implementation of {@link IActivityDelegate}
 *
 * @author zuoqx
 *         Created at 2016/8/2 18:00
 */

public class ActivityDelegateImpl<V extends BaseView, P extends BasePresenter<V>>
        implements IActivityDelegate {

    //    protected InternalDelegate<V, P> internalDelegate;
    protected DelegateCallback<V, P> mDelegateCallback;
    private P mPresenter;

    public ActivityDelegateImpl(DelegateCallback delegateCallback) {
        if (delegateCallback == null) {
            throw new NullPointerException("Mvp delegate callback is null!");
        }

        this.mDelegateCallback = delegateCallback;
    }

    @Override
    public void onCreate(Bundle bundle) {
        if (mPresenter == null) {
            mDelegateCallback.createPresenter();
        }
        mPresenter = mDelegateCallback.getPresenter();

        if (mPresenter != null) {
            mPresenter.attachView(mDelegateCallback.getMvpView());
        }
    }

    @Override
    public void onDestroy() {
        if (mPresenter != null) {
            mPresenter.unsubscribe();
            mPresenter.detachView();
            mPresenter = null;
        }
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {
        if (mPresenter != null) {
            mPresenter.subscribe();
        }
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onRestart() {

    }

    @Override
    public void onContentChanged() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }
}
