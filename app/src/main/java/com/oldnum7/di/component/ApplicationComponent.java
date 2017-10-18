package com.oldnum7.di.component;


import com.google.gson.Gson;
import com.oldnum7.App;
import com.oldnum7.data.DataRepository;
import com.oldnum7.di.module.ApplicationModule;
import com.oldnum7.di.module.TasksRepositoryModule;

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
public interface ApplicationComponent {

    void inject(App app);

    Map<String, Object> extras();

    Gson gson();

    DataRepository getRepository();

//    TasksLocalDataSource getLoacalRepository();

//    TasksRemoteDataSource getRemoteRepository();
}
