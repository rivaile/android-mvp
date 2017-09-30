package com.oldnum7.androidlib.base;

import android.app.Application;
import android.content.Context;

import com.blankj.utilcode.util.Utils;

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

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        Utils.init(this);
    }

    public static Context getContext() {
        return mContext;
    }
}
