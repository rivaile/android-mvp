package com.oldnum7.base;

import com.oldnum7.androidlib.mvp.base.BaseMvpFragment;
import com.oldnum7.androidlib.mvp.persenter.BasePresenter;
import com.oldnum7.androidlib.mvp.view.MvpView;
import com.oldnum7.di.component.ActivityComponent;

import butterknife.Unbinder;

/**
 * <pre>
 *       author : denglin
 *       time   : 2017/10/18/17:58
 *       desc   : 自定义的拓展Fragment基础类..主要是业务相关的基类...
 *       version: 1.0
 * </pre>
 */
public abstract class BaseAppFragment<V extends MvpView, P extends BasePresenter<V>> extends BaseMvpFragment<V, P> {

    private Unbinder mUnBinder;

    private BaseAppActivity mActivity;

    public ActivityComponent getActivityComponent() {
        if (mActivity != null) {
            return mActivity.getActivityComponent();
        }
        return null;
    }


    @Override
    public void onDetach() {
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        super.onDetach();
    }

    public void setUnBinder(Unbinder unBinder) {
        mUnBinder = unBinder;
    }

    public BaseAppActivity getBaseActivity() {
        return mActivity;
    }


}
