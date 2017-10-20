package com.oldnum7.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.oldnum7.App;
import com.oldnum7.R;
import com.oldnum7.androidlib.mvp.base.BaseLceActivity;
import com.oldnum7.androidlib.mvp.persenter.BasePresenter;
import com.oldnum7.androidlib.mvp.view.LceView;
import com.oldnum7.di.component.ActivityComponent;
import com.oldnum7.di.component.DaggerActivityComponent;
import com.oldnum7.di.module.ActivityModule;

import butterknife.Unbinder;

/**
 * <pre>
 *       author : denglin
 *       time   : 2017/10/18/17:58
 *       desc   : 自定义的拓展基础类..主要是业务相关的基类...
 *       version: 1.0
 * </pre>
 */
public class BaseAppActivity<V extends LceView, P extends BasePresenter<V>> extends BaseLceActivity<V, P> {

    private ActivityComponent mActivityComponent;
    private Unbinder mUnBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //need frist init ..
        mActivityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(((App) getApplication()).getComponent())
                .build();

        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {

        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        super.onDestroy();
    }

    public ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }

    public void setUnBinder(Unbinder unBinder) {
        mUnBinder = unBinder;
    }


    public void showFragment(int resId, BaseAppFragment fragment, String tag) {
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                .add(resId, fragment, tag)
                .commit();
    }

    @Override
    public void onFragmentDetached(String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if (fragment != null) {
            fragmentManager
                    .beginTransaction()
                    .disallowAddToBackStack()
                    .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                    .remove(fragment)
                    .commitNow();
        }
    }
}
