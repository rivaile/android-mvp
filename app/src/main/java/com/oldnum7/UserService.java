package com.oldnum7;

import java.util.List;

import retrofit2.Call;
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
public interface UserService {
    //https://api.github.com/users?since=1&per_page=10
    @GET("users")
    Call<List<UserEntity>> getUsers(@Query("since") int since, @Query("per_page") int per_page);
}
