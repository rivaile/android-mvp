package com.oldnum7;

import android.util.Log;

import com.oldnum7.androidlib.base.BaseApplication;
import com.oldnum7.data.DataRepository;
import com.oldnum7.data.TasksDataSource;
import com.oldnum7.di.component.ApplicationComponent;
import com.oldnum7.di.component.DaggerApplicationComponent;
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

    private static ApplicationComponent mAppComponent;

//    @Inject
    DataRepository mDataRepository;

//    @Inject
//    @Named("Remote")
    TasksDataSource mTasksLocalDataSource;

//    @Inject
//    @Named("Local")
    TasksDataSource mTasksRemoteDataSource;

    @Override
    public void onCreate() {
        super.onCreate();

        this.mAppComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();

        mAppComponent.inject(this);


        Log.e("APP", "DataRepository: "+mDataRepository);
        Log.e("APP", "tasksRemoteDataSource: "+mTasksRemoteDataSource);
        Log.e("APP", "tasksLocalDataSource: "+mTasksLocalDataSource);

    }

    public static ApplicationComponent getComponent() {
        return mAppComponent;
    }

    public DataRepository getDataRepository() {
        return mDataRepository;
    }
}
