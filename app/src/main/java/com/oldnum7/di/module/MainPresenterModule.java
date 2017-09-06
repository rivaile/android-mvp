package com.oldnum7.di.module;

import com.oldnum7.ActivityScoped;
import com.oldnum7.business.IMainContract;
import com.oldnum7.business.user.ILoginContract;

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
public class MainPresenterModule {
    private IMainContract.View mView1;
    private ILoginContract.View mView;


    public MainPresenterModule(IMainContract.View view) {
        this.mView1 = view;
    }

    public MainPresenterModule(ILoginContract.View view) {
        this.mView = view;
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
