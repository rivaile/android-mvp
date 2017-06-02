package com.oldnum7.data.local;

import android.content.Context;
import android.support.annotation.NonNull;

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

    private static TasksLocalDataSource INSTANCE;

    // Prevent direct instantiation.
    private TasksLocalDataSource(@NonNull Context context) {

    }

    public static TasksLocalDataSource getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            INSTANCE = new TasksLocalDataSource(context);
        }
        return INSTANCE;
    }

    @Override
    public Observable<List<UserEntity>> getUsers(int since, int page) {
        return null;
    }

    @Override
    public void saveTask(@NonNull UserEntity userEntity) {
        //缓存...1.保存数据库 2.file文件。。。3. RxCache缓存...
    }
}
