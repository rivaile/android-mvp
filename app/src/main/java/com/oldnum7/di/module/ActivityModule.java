package com.oldnum7.di.module;

import android.support.v7.app.AppCompatActivity;

import com.oldnum7.di.annotation.ActivityScoped;
import com.oldnum7.ui.IMainContract;
import com.oldnum7.ui.user.ILoginContract;

import dagger.Module;
import dagger.Provides;

/**
 * <pre>
 *       author : denglin
 *       time   : 2017/06/16/14:38
 *       desc   :
 *       version: 1.0
 * </pre>
 */
@Module
public class ActivityModule {

    private IMainContract.View mView1;
    private ILoginContract.View mView;
    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @ActivityScoped
    @Provides
    IMainContract.View provideIMainContractView() {
        return mView1;
    }


    @ActivityScoped
    @Provides
    ILoginContract.View provideILoginContractView() {
        return mView;
    }

}
