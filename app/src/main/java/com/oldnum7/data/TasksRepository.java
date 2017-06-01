package com.oldnum7.data;

import java.util.List;

import io.reactivex.Observable;

/**
 * <pre>
 *       author : denglin
 *       time   : 2017/06/01/14:52
 *       desc   : Concrete implementation to load tasks from the data sources into a cache.
 *       version: 1.0
 * </pre>
 */
public class TasksRepository implements TasksDataSource {

    @Override
    public Observable<List<UserEntity>> users(int since, int page) {
        return null;
    }
}
