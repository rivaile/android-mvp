package com.oldnum7.androidlib.mvp.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.oldnum7.androidlib.base.BaseFragment;
import com.oldnum7.androidlib.mvp.delegate.DelegateCallback;
import com.oldnum7.androidlib.mvp.delegate.FragmentDelegateImpl;
import com.oldnum7.androidlib.mvp.delegate.IFragmentDelegate;
import com.oldnum7.androidlib.mvp.persenter.BasePresenter;
import com.oldnum7.androidlib.mvp.view.MvpView;

import javax.inject.Inject;

/**
 * <pre>
 *       author : denglin
 *       time   : 2017/09/05/16:46
 *       desc   : mvp的相关封装...注意内存泄漏的问题...可使用代理实现...
 *       version: 1.0
 * </pre>
 */
public abstract class BaseMvpFragment<V extends MvpView, P extends BasePresenter<V>> extends BaseFragment implements MvpView, DelegateCallback<V, P> {

    protected IFragmentDelegate mMvpDelegate;

    @Inject
    protected P mPresenter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getMvpDelegate().onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getMvpDelegate().onDestroyView();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getMvpDelegate().onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getMvpDelegate().onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
        getMvpDelegate().onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        getMvpDelegate().onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        getMvpDelegate().onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        getMvpDelegate().onStop();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getMvpDelegate().onActivityCreated(savedInstanceState);
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        getMvpDelegate().onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        getMvpDelegate().onDetach();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getMvpDelegate().onSaveInstanceState(outState);
    }

    @NonNull
    @Override
    public void createPresenter() {
    }

    public P getPresenter() {
        return mPresenter;
    }

    @Override
    public V getMvpView() {
        return (V) this;
    }

    @Override
    public void setPresenter(P presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public boolean isRetainInstance() {
        return false;
    }

    /**
     * Get the mvp delegate. This is internally used for creating presenter, attaching and detaching
     * view from presenter.
     * Please note that only one instance of mvp delegate should be used per Activity instance
     * Only override this method if you really know what you are doing.
     *
     * @return
     */
    @NonNull
    protected IFragmentDelegate getMvpDelegate() {
        if (mMvpDelegate == null) {
            mMvpDelegate = new FragmentDelegateImpl(this);
        }
        return mMvpDelegate;
    }


}
