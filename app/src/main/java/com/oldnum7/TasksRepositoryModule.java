
package com.oldnum7;


import com.oldnum7.data.TasksDataSource;
import com.oldnum7.data.local.TasksLocalDataSource;
import com.oldnum7.data.remote.TasksRemoteDataSource;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * This is used by Dagger to inject the required arguments into the {@link}.
 */

@Module
public class TasksRepositoryModule {
    @Singleton
    @Provides
    @Named("Local")
    TasksDataSource provideTasksLocalDataSource(TasksLocalDataSource dataSource) {
        return dataSource;
    }

    @Singleton
    @Provides
    @Named("Remote")
    TasksDataSource provideTasksRemoteDataSource(TasksRemoteDataSource dataSource) {
        return dataSource;
    }

}
