package com.oldnum7.androidlib.mvp.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.oldnum7.androidlib.R;
import com.oldnum7.androidlib.mvp.view.MvpView;
import com.oldnum7.androidlib.base.BaseActivity;
import com.oldnum7.androidlib.mvp.delegate.ActivityDelegateImpl;
import com.oldnum7.androidlib.mvp.delegate.DelegateCallback;
import com.oldnum7.androidlib.mvp.delegate.IActivityDelegate;
import com.oldnum7.androidlib.mvp.persenter.BasePresenter;

import javax.inject.Inject;

/**
 * <pre>
 *       author : denglin
 *       time   : 2017/09/05/16:46
 *       desc   : mvp的相关封装...注意内存泄漏的问题...可使用代理实现...
 *       version: 1.0
 * </pre>
 */
public class BaseMvpActivity<V extends MvpView, P extends BasePresenter<V>> extends BaseActivity implements MvpView, DelegateCallback<V, P> {

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
    protected IActivityDelegate getMvpDelegate() {
        if (mMvpDelegate == null) {
            mMvpDelegate = new ActivityDelegateImpl(this);
        }
        return mMvpDelegate;
    }

    //-------------------------------------------------对话框--------------------------------------------------------//

    private ProgressDialog dialog;

    public void showLoading() {
        if (dialog != null && dialog.isShowing()) return;
        dialog = new ProgressDialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("请求网络中...");
        dialog.show();
    }

    public void hideLoading() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    public void showToast(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (msg != null) {
                    Toast.makeText(BaseMvpActivity.this, msg, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(BaseMvpActivity.this, getString(R.string.some_error), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void showSnackBar(String msg) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                msg, Snackbar.LENGTH_SHORT);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView
                .findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(this, R.color.white));
        snackbar.show();
    }

}
