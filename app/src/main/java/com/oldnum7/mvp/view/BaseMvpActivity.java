package com.oldnum7.mvp.view;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.oldnum7.mvp.base.BaseActivity;
import com.oldnum7.mvp.delegate.ActivityDelegateImpl;
import com.oldnum7.mvp.delegate.DelegateCallback;
import com.oldnum7.mvp.delegate.IActivityDelegate;
import com.oldnum7.mvp.persenter.BasePresenter;

import javax.inject.Inject;

/**
 * <pre>
 *       author : denglin
 *       time   : 2017/09/05/16:46
 *       desc   : mvp的相关封装...注意内存泄漏的问题...可使用代理实现...
 *       version: 1.0
 * </pre>
 */
public class BaseMvpActivity<V extends BaseView, P extends BasePresenter<V>> extends BaseActivity implements BaseView, DelegateCallback<V, P> {

    protected IActivityDelegate mMvpDelegate;

    @Inject
    protected P mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getMvpDelegate().onCreate(savedInstanceState);
    }


    @Override
    protected void onStart() {
        super.onStart();
        getMvpDelegate().onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getMvpDelegate().onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getMvpDelegate().onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        getMvpDelegate().onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        getMvpDelegate().onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getMvpDelegate().onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getMvpDelegate().onSaveInstanceState(outState);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        getMvpDelegate().onContentChanged();
    }

    @NonNull
    @Override
    public P createPresenter() {
        return null;
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
    protected IActivityDelegate getMvpDelegate() {
        if (mMvpDelegate == null) {
            mMvpDelegate = new ActivityDelegateImpl(this);
        }
        return mMvpDelegate;
    }

}
