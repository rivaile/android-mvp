package com.oldnum7.data;


import android.support.annotation.NonNull;

import com.oldnum7.data.entity.LoginEntity;
import com.oldnum7.data.entity.T;
import com.oldnum7.data.entity.VersionEntity;

import java.util.List;

import io.reactivex.Observable;

/**
 * <pre>
 *       author : denglin
 *       time   : 2017/06/01/14:44
 *       desc   :Interface that represents a Repository for getting {@link T} related data.
 *       version: 1.0
 * </pre>
 */
public interface TasksDataSource {
    /**
     * Get an {@link Observable} which will emit a List of {@link T}.
     */
    Observable<List<T>> getUsers(int since, int page);

    Observable<List<T>> getUsers();

    void saveTask(@NonNull T userEntity);

    Observable<LoginEntity> login(String name, String pwd);

    Observable<VersionEntity> updateVersion(String clientVersion);
}
