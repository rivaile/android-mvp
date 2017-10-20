
package com.oldnum7.di.module;


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

    @Provides
    @Singleton
    @Named("Local")
    TasksDataSource provideLocalDataSource(TasksLocalDataSource dataSource) {

        return dataSource;
    }
    @Provides
    @Singleton
    @Named("Remote")
    TasksDataSource provideRemoteDataSource(TasksRemoteDataSource dataSource) {
        return dataSource;
    }

}
