package com.oldnum7;

import android.util.Log;

import com.oldnum7.mvp.BaseMvpPresenter;
import com.oldnum7.mvp.IMainPresenter;
import com.oldnum7.mvp.IMainView;
import com.oldnum7.net.HttpFactory;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * author : denglin
 * time   : 2017/05/31/13:54
 * desc   :
 * version: 1.0
 */
public class MainPresenter extends BaseMvpPresenter<IMainView> implements IMainPresenter {

    private final String TAG = getClass().getSimpleName();
    private final HttpFactory mHttpFactory;
    private final UserService mUserService;

    public MainPresenter() {
        mHttpFactory = new HttpFactory.Builder()
                .build();
        mUserService = mHttpFactory.createService(UserService.class);

    }

    @Override
    public void getUsers(int since, int per_page) {

        Call<List<UserEntity>> call = mUserService.getUsers(since, per_page);
        call.enqueue(new Callback<List<UserEntity>>() {
            @Override
            public void onResponse(Call<List<UserEntity>> call, Response<List<UserEntity>> response) {
                Log.e(TAG, "onResponse: " + response.body().toString());
            }

            @Override
            public void onFailure(Call<List<UserEntity>> call, Throwable t) {
                Log.e(TAG, "onResponse: " + t.toString());
            }
        });

    }
}
