package com.oldnum7.ui;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * <pre>
 *       author : denglin
 *       time   : 2017/08/31/14:12
 *       desc   :
 *       version: 1.0
 * </pre>
 */
public interface GitHubService {

    @GET("users/{user}/repos")
    Call<List<Repo>> listRepos(@Path("user") String user);

}
