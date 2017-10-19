package com.oldnum7.androidlib.mvp.delegate;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.oldnum7.androidlib.mvp.persenter.BasePresenter;
import com.oldnum7.androidlib.mvp.view.MvpView;


/**
 * The concrete implementation of {@link IActivityDelegate}
 *
 * @author denglin
 *         Created at 2016/8/2 18:00
 */

public class FragmentDelegateImpl<V extends MvpView, P extends BasePresenter<V>>
        implements IFragmentDelegate {

    //    protected InternalDelegate<V, P> internalDelegate;
    protected DelegateCallback<V, P> mDelegateCallback;
    private P mPresenter;

    public FragmentDelegateImpl(DelegateCallback delegateCallback) {
        if (delegateCallback == null) {
            throw new NullPointerException("Mvp delegate callback is null!");
        }

        this.mDelegateCallback = delegateCallback;
    }

    @Override
    public void onCreate(Bundle bundle) {

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

    }


    @Override
    public void onAttach(Context activity) {
        if (mPresenter == null) {
            mDelegateCallback.createPresenter();
        }
        mPresenter = mDelegateCallback.getPresenter();

        if (mPresenter != null) {
            mPresenter.attachView(mDelegateCallback.getMvpView());
        }
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {
        if (mPresenter != null) {
            mPresenter.subscribe();
        }
    }

    @Override
    public void onPause() {

    }


    @Override
    public void onStop() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }


    @Override
    public void onDetach() {
        if (mPresenter != null) {
            mPresenter.unsubscribe();
            mPresenter.detachView();
            mPresenter = null;
        }
    }
    @Override
    public void onDestroyView() {

    }

    @Override
    public void onDestroy() {

    }
}
