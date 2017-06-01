package com.oldnum7.data.local;

import com.oldnum7.data.TasksDataSource;
import com.oldnum7.data.UserEntity;

import java.util.List;

import io.reactivex.Observable;

/**
 * <pre>
 *       author : denglin
 *       time   : 2017/06/01/14:55
 *       desc   : Concrete implementation of a data source as a db.
 *       version: 1.0
 * </pre>
 */
public class TasksLocalDataSource implements TasksDataSource {
    @Override
    public Observable<List<UserEntity>> users(int since, int page) {
        return null;
    }
}
