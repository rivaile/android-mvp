package com.oldnum7.data.local;

import android.support.annotation.NonNull;

import com.oldnum7.data.TasksDataSource;
import com.oldnum7.data.entity.UserEntity;
import com.oldnum7.http.cache.rxcache.CacheService;
import com.oldnum7.http.cache.rxcache.RxCacheFactory;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.rx_cache2.EvictProvider;
import io.rx_cache2.Reply;


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
    private CacheService mCacheService = RxCacheFactory.getInstance().createCacheService(CacheService.class);
    private Observable<Reply<List<UserEntity>>> mUsersCache;

    // Prevent direct instantiation.
    @Inject
    public TasksLocalDataSource() {
//        if (INSTANCE == null) {
//            INSTANCE = new TasksLocalDataSource(context);
//        }
    }

    @Override
    public Observable<List<UserEntity>> getUsers(int since, int page) {
        return null;
    }

    @Override
    public Observable<List<UserEntity>> getUsers() {
        return mUsersCache.map(new Function<Reply<List<UserEntity>>, List<UserEntity>>() {
            @Override
            public List<UserEntity> apply(Reply<List<UserEntity>> listReply) throws Exception {

                return listReply.getData();
            }
        });
    }

    @Override
    public void saveTask(@NonNull UserEntity userEntity) {
        //缓存...1.保存数据库 2.file文件。。sp文件...。3. RxCache缓存...
//        HttpResponse<List<UserEntity>> response = new HttpResponse<>();
//        response.setMsg("操作成功");
//        response.setStatus("200");
//        List<UserEntity> users = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//            UserEntity user = new UserEntity();
//            user.setHeadImg("https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png");
//            user.setToken("DSHDHsdsdKKHKDSdsd" + i);
//            user.setUserName("第" + i + "个用户");
//        }
//        response.getResult().addAll(users);

    }
    
    public void saveTask(Observable<List<UserEntity>> users, Boolean flag) {
        mUsersCache = mCacheService.getUsersCache(users, new EvictProvider(flag));
    }

}
