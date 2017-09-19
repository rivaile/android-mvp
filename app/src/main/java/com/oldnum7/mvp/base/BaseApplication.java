package com.oldnum7.mvp.base;

import android.app.Application;
import android.content.Context;

import com.blankj.utilcode.util.Utils;
import com.oldnum7.di.component.AppComponent;
import com.oldnum7.di.component.DaggerAppComponent;
import com.oldnum7.di.module.ApplicationModule;

/**
 * <pre>
 *       author : denglin
 *       time   : 2017/06/01/10:17
 *       desc   :
 *       version: 1.0
 * </pre>
 */
public class BaseApplication extends Application {
    private static Context mContext;

    private static AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        Utils.init(this);

        this.mAppComponent = DaggerAppComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        mContext = mAppComponent.getContext();
    }

    public static AppComponent getAppComponent() {
        return mAppComponent;
    }

    public static Context getContext() {
        return mContext;
    }

}
