package com.oldnum7.data.remote;

import android.support.annotation.NonNull;

import com.oldnum7.Constants;
import com.oldnum7.business.ApiService;
import com.oldnum7.data.TasksDataSource;
import com.oldnum7.data.entity.HttpResponse;
import com.oldnum7.data.entity.UserEntity;
import com.oldnum7.http.HttpFactory;
import com.oldnum7.http.exception.HttpException;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * <pre>
 *       author : denglin
 *       time   : 2017/06/01/14:55
 *       desc   : Implementation of the data source that adds a latency simulating network.
 *       version: 1.0
 * </pre>
 */
@Singleton
public class TasksRemoteDataSource implements TasksDataSource {
    private HttpFactory mHttpManager;
    private ApiService mApiService;

    // Prevent direct instantiation.
    @Inject
    TasksRemoteDataSource() {
//        mHttpManager = new HttpFactory.Builder().build();
    }

    @Override
    public Observable<List<UserEntity>> getUsers(int since, int per_page) {
        mApiService = mHttpManager.createService(ApiService.class);
        return mApiService.getUsers(since, per_page);
    }

    @Override
    public Observable<List<UserEntity>> getUsers() {
        //TODO...此处需要做操作,自定义异常...
        mApiService = mHttpManager.createService(ApiService.class);

        return mApiService
                .getUsers()
                .compose(compose());

    }

    @Override
    public void saveTask(@NonNull UserEntity userEntity) {

    }

    public <T> ObservableTransformer<HttpResponse<T>, T> compose() {
        return new ObservableTransformer<HttpResponse<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<HttpResponse<T>> upstream) {
                return upstream.map(new Function<HttpResponse<T>, T>() {
                    @Override
                    public T apply(HttpResponse<T> response) throws Exception {
                        if (Constants.HTTP_SUCCESS != response.getStatus()) {
                            throw new HttpException(response);
                        }
                        return response.getResult();
                    }
                });
            }
        };
    }

//    private ObservableTransformer<HttpResponse<List<UserEntity>>, List<UserEntity>> composer = new ObservableTransformer<HttpResponse<List<UserEntity>>, List<UserEntity>>() {
//        @Override
//        public ObservableSource<List<UserEntity>> apply(Observable<HttpResponse<List<UserEntity>>> upstream) {
//            return upstream.map(new Function<HttpResponse<List<UserEntity>>, List<UserEntity>>() {
//                @Override
//                public List<UserEntity> apply(HttpResponse<List<UserEntity>> response) throws Exception {
//                    if (Constants.HTTP_SUCCESS != response.getStatus()) {
//                        throw new HttpException(response.getStatus(), response.getMsg(), response.getError());
//                    }
//                    return response.getResult();
//                }
//            });
//        }
//    };


    public void getTask() {
        ApiService service = HttpFactory.getInstance().createService(ApiService.class);

        service.getUser().enqueue(new Callback<HttpResponse<List<UserEntity>>>() {
            @Override
            public void onResponse(Call<HttpResponse<List<UserEntity>>> call, Response<HttpResponse<List<UserEntity>>> response) {

            }

            @Override
            public void onFailure(Call<HttpResponse<List<UserEntity>>> call, Throwable t) {

            }
        });
    }
}
