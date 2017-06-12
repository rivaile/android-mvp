package com.oldnum7.business;

import com.oldnum7.data.entity.HttpResponse;
import com.oldnum7.data.entity.UserEntity;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
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
    Observable<List<UserEntity>> getUsers(@Query("since") int since, @Query("per_page") int per_page);

    @Headers("Cache-Control:public,max-age=30")
    @GET("servlet")
    Observable<HttpResponse<List<UserEntity>>> getUsers();

    @Headers("Cache-Control:public,max-age=30")
    @GET("servlet")
    Call<HttpResponse<List<UserEntity>>> getUser();
}