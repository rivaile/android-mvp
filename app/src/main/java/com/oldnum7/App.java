package com.oldnum7;

import com.oldnum7.androidlib.base.BaseApplication;
import com.oldnum7.di.component.AppComponent;
import com.oldnum7.di.component.DaggerAppComponent;
import com.oldnum7.di.module.ApplicationModule;


/**
 * <pre>
 *       author : denglin
 *       time   : 2017/09/19/15:35
 *       desc   :
 *       version: 1.0
 * </pre>
 */
public class App extends BaseApplication {

    private static AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        this.mAppComponent = DaggerAppComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public static AppComponent getAppComponent() {
        return mAppComponent;
    }
}
