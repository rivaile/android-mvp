package com.oldnum7.di.component;

import android.content.Context;

import com.google.gson.Gson;
import com.oldnum7.ActivityManager;
import com.oldnum7.data.TasksRepository;
import com.oldnum7.di.module.AppModule;

import java.util.Map;

import dagger.Component;

/**
 * <pre>
 *       author : denglin
 *       time   : 2017/06/08/17:14
 *       desc   :
 *       version: 1.0
 * </pre>
 */
@Component(modules = {AppModule.class})
public interface AppComponent {

    Context getContext();

    //全局变量...
    Map<String, Object> extras();

    TasksRepository getRepository();

    ActivityManager getActivityManager();

    Gson gson();
}
