package com.oldnum7.base;

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
public class App extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        this.mContext = this;
        Utils.init(this);
    }

    public static Context getmContext() {
        return mContext;
    }
}
