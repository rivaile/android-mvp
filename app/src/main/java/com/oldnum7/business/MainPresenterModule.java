package com.oldnum7.business;

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
    private final IMainContract.View mView;

    public MainPresenterModule(IMainContract.View view) {
        this.mView = view;
    }

    @Provides
    IMainContract.View provideIMainContractView() {
        return mView;
    }
}
