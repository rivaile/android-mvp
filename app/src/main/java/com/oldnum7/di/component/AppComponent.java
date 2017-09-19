package com.oldnum7.di.component;


import android.content.Context;

import com.google.gson.Gson;
import com.oldnum7.di.module.TasksRepositoryModule;
import com.oldnum7.data.TasksRepository;
import com.oldnum7.di.module.ApplicationModule;

import java.util.Map;

import javax.inject.Singleton;

import dagger.Component;

/**
 * <pre>
 *       author : denglin
 *       time   : 2017/06/08/17:14
 *       desc   : init some unique variate
 *       version: 1.0
 * </pre>
 */

@Singleton
@Component(modules = {TasksRepositoryModule.class, ApplicationModule.class})
public interface AppComponent {

    Context getContext();

    //全局变量...
    Map<String, Object> extras();

    TasksRepository getRepository();

    Gson gson();
}
