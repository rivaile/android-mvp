package com.oldnum7.data.local;

import android.content.Context;
import android.support.annotation.NonNull;

import com.oldnum7.data.TasksDataSource;
import com.oldnum7.data.entity.UserEntity;

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
    public Observable<List<UserEntity>> getUsers() {
        return null;
    }

    @Override
    public void saveTask(@NonNull UserEntity userEntity) {
        //缓存...1.保存数据库 2.file文件。。。3. RxCache缓存...
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


}
