package com.oldnum7.data.remote;

import android.support.annotation.NonNull;

import com.oldnum7.business.ApiService;
import com.oldnum7.data.TasksDataSource;
import com.oldnum7.data.UserEntity;
import com.oldnum7.data.net.HttpFactory;

import java.util.List;

import io.reactivex.Observable;

/**
 * <pre>
 *       author : denglin
 *       time   : 2017/06/01/14:55
 *       desc   : Implementation of the data source that adds a latency simulating network.
 *       version: 1.0
 * </pre>
 */
public class TasksRemoteDataSource implements TasksDataSource {

    private static TasksRemoteDataSource INSTANCE;

    private HttpFactory mHttpFactory;
    private ApiService mApiService;

    public static TasksRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TasksRemoteDataSource();
        }
        return INSTANCE;
    }

    // Prevent direct instantiation.
    private TasksRemoteDataSource() {
        mHttpFactory = new HttpFactory.Builder().build();
    }

    @Override
    public Observable<List<UserEntity>> getUsers(int since, int per_page) {
        mApiService = mHttpFactory.createService(ApiService.class);
        return mApiService.getUsers(since, per_page);
    }

    @Override
    public void saveTask(@NonNull UserEntity userEntity) {

    }
}
