
package com.oldnum7;


import com.oldnum7.data.Local;
import com.oldnum7.data.Remote;
import com.oldnum7.data.TasksDataSource;
import com.oldnum7.data.local.TasksLocalDataSource;
import com.oldnum7.data.remote.TasksRemoteDataSource;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

/**
 * This is used by Dagger to inject the required arguments into the {@link TasksRepository}.
 */
@Module
public class TasksRepositoryModule {

    @Singleton
    @Local
    TasksDataSource provideTasksLocalDataSource(TasksLocalDataSource dataSource) {
        return dataSource;
    }


    @Singleton
    @Remote
    TasksDataSource provideTasksRemoteDataSource(TasksRemoteDataSource dataSource) {
        return dataSource;
    }

}
