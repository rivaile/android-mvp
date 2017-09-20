package com.oldnum7.data.local;

import android.support.annotation.NonNull;

import com.oldnum7.data.TasksDataSource;
import com.oldnum7.data.entity.LoginEntity;
import com.oldnum7.data.entity.T;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;


/**
 * <pre>
 *       author : denglin
 *       time   : 2017/06/01/14:55
 *       desc   : Concrete implementation of a data source as a db.
 *       version: 1.0
 * </pre>
 */
@Singleton
public class TasksLocalDataSource implements TasksDataSource {

    private static TasksLocalDataSource INSTANCE;
//    private CacheService mCacheService = RxCacheFactory.getInstance().createCacheService(CacheService.class);
//    private Observable<Reply<List<T>>> mUsersCache;

    // Prevent direct instantiation.
    @Inject
    public TasksLocalDataSource() {
//        if (INSTANCE == null) {
//            INSTANCE = new TasksLocalDataSource(context);
//        }
    }

    @Override
    public Observable<List<T>> getUsers(int since, int page) {
        return null;
    }

    @Override
    public Observable<List<T>> getUsers() {
//        return mUsersCache.map(new Function<Reply<List<T>>, List<T>>() {
//            @Override
//            public List<T> apply(Reply<List<T>> listReply) throws Exception {
//
//                return listReply.getData();
//            }
//        });
        return null;
    }

    @Override
    public void saveTask(@NonNull T userEntity) {
        //缓存...1.保存数据库 2.file文件。。sp文件...。3. RxCache缓存...
//        HttpResponse<List<T>> response = new HttpResponse<>();
//        response.setMsg("操作成功");
//        response.setStatus("200");
//        List<T> users = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//            T user = new T();
//            user.setHeadImg("https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png");
//            user.setToken("DSHDHsdsdKKHKDSdsd" + i);
//            user.setUserName("第" + i + "个用户");
//        }
//        response.getResult().addAll(users);

    }

    public void saveTask(Observable<List<T>> users, Boolean flag) {
//        mUsersCache = mCacheService.getUsersCache(users, new EvictProvider(flag));
    }
    
    @Override
    public Observable<LoginEntity> login(String name, String pwd) {
        return null;
    }

}
