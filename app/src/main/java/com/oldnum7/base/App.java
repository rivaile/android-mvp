package com.oldnum7.base;

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
public class App extends Application {
    private static Context mContext;

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.mContext = this;
        Utils.init(this);

        this.mAppComponent = DaggerAppComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }
    
    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    public static Context getmContext() {
        return mContext;
    }
}
