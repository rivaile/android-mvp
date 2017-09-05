package com.oldnum7;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.blankj.utilcode.util.NetworkUtils;
import com.oldnum7.business.ApiService;
import com.oldnum7.data.entity.HttpResponse;

import java.io.IOException;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //创建Cache
        Cache cache = new Cache(this.getCacheDir(), 10 * 1024 * 1024);

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        }

//        interceptors.add(logging);
        //设置拦截器和Cache
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(getCacheInterceptor())
                .cache(cache)
                .addInterceptor(getCacheInterceptor())
                .addInterceptor(logging)
                .build();

        //设置OkHttpClient
        Retrofit retofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.176:8080/")
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retofit.create(ApiService.class)
                        .getUser().enqueue(new Callback<HttpResponse<List<T>>>() {
                    @Override
                    public void onResponse(Call<HttpResponse<List<T>>> call, retrofit2.Response<HttpResponse<List<T>>> response) {
                        Log.e("TAG", "onResponse: " + response);
                    }

                    @Override
                    public void onFailure(Call<HttpResponse<List<T>>> call, Throwable t) {
                        Log.e("TAG", "onResponse: " + t.toString());
                    }
                });
            }
        });
    }


    public static Interceptor getCacheInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!NetworkUtils.isConnected()) {
                    //无网络下强制使用缓存，无论缓存是否过期,此时该请求实际上不会被发送出去。
                    request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }

                Response response = chain.proceed(request);
                if (NetworkUtils.isConnected()) {//有网络情况下，根据请求接口的设置，配置缓存。
                    //这样在下次请求时，根据缓存决定是否真正发出请求。
                    String cacheControl = request.cacheControl().toString();
                    //当然如果你想在有网络的情况下都直接走网络，那么只需要
                    //将其超时时间这是为0即可:String cacheControl="Cache-Control:public,max-age=0"
                    return response.newBuilder().header("Cache-Control", cacheControl)
                            .removeHeader("Pragma")
                            .build();
                } else {//无网络
                    return response.newBuilder().header("Cache-Control", "public,only-if-cached,max-stale=360000")
                            .removeHeader("Pragma")
                            .build();
                }

            }
        };
    }

    private void test() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {

            }
        });
    }
}
