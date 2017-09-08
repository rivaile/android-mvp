package com.oldnum7.mvp.base;

import android.app.Application;
import android.content.Context;

/**
 * <pre>
 *       author : denglin
 *       time   : 2017/09/06/11:45
 *       desc   :
 *       version: 1.0
 * </pre>
 */
public class BaseApplication extends Application {
    private static Context sContext;

    public static Context getContext() {
        return sContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.sContext = getApplicationContext();
    }
}
