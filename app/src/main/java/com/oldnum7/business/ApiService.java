package com.oldnum7.business;

import com.oldnum7.data.UserEntity;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * <pre>
 *       author : denglin
 *       time   : 2017/05/31/17:35
 *       desc   :
 *       version: 1.0
 * </pre>
 */
public interface ApiService {
    //https://api.github.com/users?since=1&per_page=10
    @GET("getUsers")
    Single<List<UserEntity>> getUsers(@Query("since") int since, @Query("per_page") int per_page);
}
