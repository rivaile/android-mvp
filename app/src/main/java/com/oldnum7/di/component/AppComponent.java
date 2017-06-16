package com.oldnum7.di.component;

import android.content.Context;

import com.google.gson.Gson;
import com.oldnum7.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * <pre>
 *       author : denglin
 *       time   : 2017/06/08/17:14
 *       desc   :
 *       version: 1.0
 * </pre>
 */

@Singleton
@Component(modules = {ApplicationModule.class})
public interface AppComponent {

    Context getContext();

    //全局变量...
//    Map<String, Object> extras();

    //    TasksRepository getRepository();

//    ActivityManager getActivityManager();

//    TasksRepository getTasksRepository();

    Gson gson();

//    void inject(Activity activity);
}
