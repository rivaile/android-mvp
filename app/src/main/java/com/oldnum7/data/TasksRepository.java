package com.oldnum7.data;

import android.support.annotation.NonNull;

import com.oldnum7.ActivityScoped;
import com.oldnum7.data.entity.UserEntity;
import com.oldnum7.data.local.TasksLocalDataSource;
import com.oldnum7.data.remote.TasksRemoteDataSource;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

/**
 * <pre>
 *       author : denglin
 *       time   : 2017/06/01/14:52
 *       desc   : Concrete implementation to load tasks from the data sources into a cache.
 *       version: 1.0
 * </pre>
 */

public class TasksRepository implements TasksDataSource {


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
    @Inject
    public TasksRepository(TasksRemoteDataSource tasksRemoteDataSource,
                           TasksLocalDataSource tasksLocalDataSource) {
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
//    public static TasksRepository getInstance(TasksDataSource tasksRemoteDataSource,
//                                              TasksDataSource tasksLocalDataSource) {
//        if (INSTANCE == null) {
//            INSTANCE = new TasksRepository(tasksRemoteDataSource, tasksLocalDataSource);
//        }
//        return INSTANCE;
//    }

    /**
     * Used to force {@link #getInstance(TasksDataSource, TasksDataSource)} to create a new instance
     * next time it's called.
     */
//    public static void destroyInstance() {
//        INSTANCE = null;
//    }

    public void refreshTasks() {
        mCacheIsDirty = true;
    }

    //TODO....

    /**
     * Gets tasks from cache, local data source (SQLite) or remote data source, whichever is
     * available first.
     * 缓存情况:
     * 1. 死的列表:
     * 2. 登录与否:
     * 3. 分页加载:
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
                        public ObservableSource<? extends List<UserEntity>> apply(List<UserEntity> userEntities) throws Exception {
                            return Observable.just(userEntities);
                        }
                    });
        } else if (mCachedTasks == null) {
            mCachedTasks = new LinkedHashMap<>();
        }

        Observable<List<UserEntity>> remoteTasks = getAndSaveRemoteTasks();

        if (mCacheIsDirty) {
            return remoteTasks;
        } else {
            // Query the local storage if available. If not, query the network.
            Observable<List<UserEntity>> localTasks = getAndCacheLocalTasks();
            return Observable.concat(localTasks, remoteTasks)
                    .filter(new Predicate<List<UserEntity>>() {
                        @Override
                        public boolean test(List<UserEntity> userEntities) throws Exception {
                            return !userEntities.isEmpty();
                        }
                    })
                    .first(localTasks.blockingFirst())
                    .flatMapObservable(new Function<List<UserEntity>, ObservableSource<List<UserEntity>>>() {
                        @Override
                        public ObservableSource<List<UserEntity>> apply(List<UserEntity> userEntities) throws Exception {
                            return Observable.just(userEntities);
                        }
                    });
        }
    }

    @Override
    public Observable<List<UserEntity>> getUsers() {
        //内存缓存------->本地缓存------->网络缓存
        // Respond immediately with cache if available and not dirty , 内存缓存
        if (mCachedTasks != null && !mCacheIsDirty) {
            Collection<UserEntity> values = mCachedTasks.values();

            return Observable.fromIterable(values)
                    .toList()
                    .flatMapObservable(new Function<List<UserEntity>, ObservableSource<? extends List<UserEntity>>>() {
                        @Override
                        public ObservableSource<? extends List<UserEntity>> apply(List<UserEntity> userEntities) throws Exception {
                            return Observable.just(userEntities);
                        }
                    });
        } else if (mCachedTasks == null) {
            mCachedTasks = new LinkedHashMap<>();
        }

        Observable<List<UserEntity>> remoteTasks = getAndSaveRemoteTasks();

        if (mCacheIsDirty) {
            return remoteTasks;
        } else {
            // Query the local storage if available. If not, query the network.
            Observable<List<UserEntity>> localTasks = getAndCacheLocalTasks();
            return Observable.concat(localTasks, remoteTasks)
                    .filter(new Predicate<List<UserEntity>>() {
                        @Override
                        public boolean test(List<UserEntity> userEntities) throws Exception {
                            return !userEntities.isEmpty();
                        }
                    })
                    .first(localTasks.blockingFirst())
                    .flatMapObservable(new Function<List<UserEntity>, ObservableSource<List<UserEntity>>>() {
                        @Override
                        public ObservableSource<List<UserEntity>> apply(List<UserEntity> userEntities) throws Exception {
                            return Observable.just(userEntities);
                        }
                    });
        }
    }

    private Observable<List<UserEntity>> getAndSaveRemoteTasks() {

        ((TasksLocalDataSource) mTasksLocalDataSource).saveTask(mTasksRemoteDataSource.getUsers(), false);

        return mTasksRemoteDataSource
                .getUsers()
                .flatMap(new Function<List<UserEntity>, ObservableSource<List<UserEntity>>>() {
                    @Override
                    public ObservableSource<List<UserEntity>> apply(List<UserEntity> userEntities) throws Exception {

                        return Observable.fromIterable(userEntities)
                                .doOnNext(new Consumer<UserEntity>() {
                                    @Override
                                    public void accept(UserEntity userEntity) throws Exception {
                                        mTasksLocalDataSource.saveTask(userEntity);
                                        mCachedTasks.put(userEntity.getUserName() + "", userEntity);
                                    }
                                }).toList()
                                .flatMapObservable(new Function<List<UserEntity>, ObservableSource<? extends List<UserEntity>>>() {
                                    @Override
                                    public ObservableSource<? extends List<UserEntity>> apply(List<UserEntity> userEntities) throws Exception {
                                        return Observable.just(userEntities);
                                    }
                                });
                    }
                }).doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        mCacheIsDirty = false;
                    }
                });

    }

    @Override
    public void saveTask(@NonNull UserEntity userEntity) {

    }

    private Observable<List<UserEntity>> getAndCacheLocalTasks() {
        return mTasksLocalDataSource.getUsers()
                .flatMap(new Function<List<UserEntity>, Observable<List<UserEntity>>>() {
                    @Override
                    public Observable<List<UserEntity>> apply(List<UserEntity> userEntities) throws Exception {
                        return Observable.fromIterable(userEntities)
                                .doOnNext(new Consumer<UserEntity>() {
                                    @Override
                                    public void accept(UserEntity userEntity) throws Exception {
                                        mCachedTasks.put(userEntity.getUserName() + "", userEntity);
                                    }
                                }).toList()
                                .flatMapObservable(new Function<List<UserEntity>, ObservableSource<? extends List<UserEntity>>>() {
                                    @Override
                                    public ObservableSource<List<UserEntity>> apply(List<UserEntity> userEntities) throws Exception {
                                        return Observable.just(userEntities);
                                    }
                                });
                    }

                });
    }

}
