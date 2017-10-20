package com.oldnum7.di.module;

import android.support.v7.app.AppCompatActivity;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

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


    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

}
