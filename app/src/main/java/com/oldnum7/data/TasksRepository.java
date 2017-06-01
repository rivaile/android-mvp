package com.oldnum7.data;

import android.support.annotation.NonNull;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

/**
 * <pre>
 *       author : denglin
 *       time   : 2017/06/01/14:52
 *       desc   : Concrete implementation to load tasks from the data sources into a cache.
 *       version: 1.0
 * </pre>
 */
public class TasksRepository implements TasksDataSource {

    private static TasksRepository INSTANCE = null;

    private final TasksDataSource mTasksRemoteDataSource;

    private final TasksDataSource mTasksLocalDataSource;

    /**
     * This variable has package local visibility so it can be accessed from tests.
     */
    Map<String, UserEntity> mCachedTasks;

    /**
     * Marks the cache as invalid, to force an update the next time data is requested. This variable
     * has package local visibility so it can be accessed from tests.
     */
    boolean mCacheIsDirty = false;

    // Prevent direct instantiation.
    private TasksRepository(@NonNull TasksDataSource tasksRemoteDataSource,
                            @NonNull TasksDataSource tasksLocalDataSource) {
        if (tasksRemoteDataSource == null || tasksLocalDataSource == null) {
            throw new NullPointerException();
        }
        mTasksRemoteDataSource = tasksRemoteDataSource;
        mTasksLocalDataSource = tasksLocalDataSource;
    }

    /**
     * Returns the single instance of this class, creating it if necessary.
     *
     * @param tasksRemoteDataSource the backend data source
     * @param tasksLocalDataSource  the device storage data source
     * @return the {@link TasksRepository} instance
     */
    public static TasksRepository getInstance(TasksDataSource tasksRemoteDataSource,
                                              TasksDataSource tasksLocalDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new TasksRepository(tasksRemoteDataSource, tasksLocalDataSource);
        }
        return INSTANCE;
    }

    /**
     * Used to force {@link #getInstance(TasksDataSource, TasksDataSource)} to create a new instance
     * next time it's called.
     */
    public static void destroyInstance() {
        INSTANCE = null;
    }
    //TODO....

    /**
     * Gets tasks from cache, local data source (SQLite) or remote data source, whichever is
     * available first.
     */
    @Override
    public Observable<List<UserEntity>> getUsers(int since, int page) {
        // Respond immediately with cache if available and not dirty , 内存缓存
        if (mCachedTasks != null && !mCacheIsDirty) {
            Collection<UserEntity> values = mCachedTasks.values();

            return Observable.fromIterable(values)
                    .toList()
                    .flatMapObservable(new Function<List<UserEntity>, ObservableSource<? extends List<UserEntity>>>() {
                        @Override
                        public ObservableSource<? extends List<UserEntity>> apply(@io.reactivex.annotations.NonNull List<UserEntity> userEntities) throws Exception {
                            return Observable.just(userEntities);
                        }
                    });
        } else if (mCachedTasks == null) {
            mCachedTasks = new LinkedHashMap<>();
        }

        Observable<List<UserEntity>> remoteTasks = getAndSaveRemoteTasks();

        return null;
    }


    private Observable<List<UserEntity>> getAndSaveRemoteTasks() {
//        return mTasksRemoteDataSource
//                .getUsers(10,10)
//                .flatMap(new Function<List<UserEntity>, ObservableSource<?>>() {
//                    @Override
//                    public ObservableSource<?> apply(@io.reactivex.annotations.NonNull List<UserEntity> userEntities) throws Exception {
//                        return null;
//                    }
//                })
//                .flatMap(new Func1<List<Task>, Observable<List<Task>>>() {
//                    @Override
//                    public Observable<List<Task>> call(List<Task> tasks) {
//                        return Observable.from(tasks).doOnNext(new Action1<Task>() {
//                            @Override
//                            public void call(Task task) {
//                                mTasksLocalDataSource.saveTask(task);
//                                mCachedTasks.put(task.getId(), task);
//                            }
//                        }).toList();
//                    }
//                })
//                .doOnCompleted(new Action0() {
//                    @Override
//                    public void call() {
//                        mCacheIsDirty = false;
//                    }
//                });
        return null;
    }


}
