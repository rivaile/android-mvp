package com.oldnum7.http;

import android.app.Application;
import android.content.Context;

import com.oldnum7.BuildConfig;
import com.oldnum7.Constants;
import com.oldnum7.base.App;
import com.oldnum7.http.utils.HttpUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Http 请求封装
 */
public class HttpManager {

    private static final String TAG = "HttpManager";

    private static HttpManager sInstance;

    private Application mContext;            //全局上下文


    //------------------------------------------------
    private static final long CACHE_SIZE = 1024 * 1024 * 10;

    // base url for Http request
    private String mBaseUrl;

    private long mReadTimeout = 10000;

    private long mWriteTimeout = 10000;

    private long mConnectTimeout = 10000;

    // 是否失败重连
    private boolean mRetryOnConnectionFailure = true;

    // ssl 管理
    private SSLSocketFactory mSslSocketFactory;

    private TrustManager[] trustAllCerts;

    private X509TrustManager mX509TrustManager;

    private HostnameVerifier hostnameVerifier;

    private List<Interceptor> interceptors = new ArrayList<>();

    private List<Interceptor> networkInterceptors = new ArrayList<>();

    private OkHttpClient mOkHttpClient;

    private Retrofit mRetrofit;

    //----------------------------------------------------------------------------------------------------

    public static HttpManager getInstance() {
        if (sInstance == null) {
            synchronized (HttpManager.class) {
                if (sInstance == null) {
                    sInstance = new HttpManager();
                }
            }
        }
        return sInstance;
    }

    HttpManager() {

    }

    public HttpManager init(Application app) {
        this.mContext = app;
        return this;
    }

    /** 获取全局上下文 */
    public Context getContext() {
        HttpUtils.checkNotNull(mContext, "please call HttpManager.getInstance().init() first in application!");
        return mContext;
    }


    private HttpManager(Builder builder) {

        if (null == builder.baseUrl) {
            setBaseUrl();
        } else {
            this.mBaseUrl = builder.baseUrl;
        }

        if (null == builder.sslSocketFactory) {
//            setSslSocketFactory();
        } else {
            this.mSslSocketFactory = builder.sslSocketFactory;
        }

        if (0 != builder.readTimeout) {
            this.mReadTimeout = builder.readTimeout;
        }

        if (0 != builder.writeTimeout) {
            this.mWriteTimeout = builder.writeTimeout;
        }

        if (0 != builder.connectTimeout) {
            this.mConnectTimeout = builder.connectTimeout;
        }

        if (!builder.retryOnConnectionFailure) {
            this.mRetryOnConnectionFailure = builder.retryOnConnectionFailure;
        }

        // add Application interceptors
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        }

        interceptors.add(logging);

        //有网络的时候直接访问网络...无网的时候走离线缓存
//        interceptors.add(new OffLineCacheInterceptor());
//        interceptors.add(new CacheInterceptor());
        interceptors.addAll(builder.interceptors);

        // add Network interceptors

        //无络的时候不会执行调用这个拦截器...有网的时候会执行网络缓存...
//        networkInterceptors.add(new OnLineCacheInterceptor());
//        networkInterceptors.add(new CacheInterceptor());
        networkInterceptors.addAll(builder.networkInterceptors);

        if (null == builder.okHttpClient) {
            createHttpClient();
        } else {
            this.mOkHttpClient = builder.okHttpClient;
        }

        if (null == builder.retrofit) {
            createRetrofit();
        } else {
            this.mRetrofit = builder.retrofit;
        }

    }

    // set base url
    private void setBaseUrl() {
        mBaseUrl = Constants.HTTP_BASE_URL;
    }


    private void createHttpClient() {
        File cacheFile = new File(App.getmContext().getCacheDir(), "app_cache");
        Cache cache = new Cache(cacheFile, CACHE_SIZE);

        // create OkHttpClient instance
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        // network interceptor
//        builder.addNetworkInterceptor(new StethoInterceptor());
        //设置读、写、连接超时
        builder.readTimeout(mReadTimeout, TimeUnit.MILLISECONDS);
        builder.connectTimeout(mConnectTimeout, TimeUnit.MILLISECONDS);
        builder.writeTimeout(mWriteTimeout, TimeUnit.MILLISECONDS);
        builder.retryOnConnectionFailure(mRetryOnConnectionFailure);
        builder.cache(cache);

        // add application interceptor
        if (interceptors.size() > 0) {
            for (Interceptor interceptor : interceptors) {
                builder.addInterceptor(interceptor);
            }
        }

        // add Network interceptor
        if (networkInterceptors.size() > 0) {
            for (Interceptor interceptor : networkInterceptors) {
                builder.addNetworkInterceptor(interceptor);
            }
        }

        // confirge ssl
//        builder.hostnameVerifier(hostnameVerifier);
//        builder.sslSocketFactory(mSslSocketFactory, mX509TrustManager);

        mOkHttpClient = builder.build();
    }

    private void createRetrofit() {
        // create Retrofit.Builder instance
        mRetrofit = new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(mOkHttpClient)
                .build();
    }

    public <T> T createService(Class<T> clazz) {

        return mRetrofit.create(clazz);
    }

    /**
     * Http inner builder class for confirging.
     */
    public static class Builder {

        final List<Interceptor> interceptors = new ArrayList<>();
        final List<Interceptor> networkInterceptors = new ArrayList<>();
        private String baseUrl;
        private long readTimeout;
        private long writeTimeout;
        private long connectTimeout;
        private boolean retryOnConnectionFailure = true;
        //        private CustomHttpLogInterceptor loggingInterceptor;
        private SSLSocketFactory sslSocketFactory;
        private OkHttpClient okHttpClient;
        private Retrofit retrofit;

        public Builder() {
        }

        public Builder setBaseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public Builder setReadTimeout(long readTimeout) {
            this.readTimeout = readTimeout;
            return this;
        }


        public Builder setWriteTimeout(long writeTimeout) {
            this.writeTimeout = writeTimeout;
            return this;
        }

        public Builder setConnectTimeout(long connectTimeout) {
            this.connectTimeout = connectTimeout;
            return this;
        }

        public Builder setRetryOnConnectionFailure(boolean retryOnConnectionFailure) {
            this.retryOnConnectionFailure = retryOnConnectionFailure;
            return this;
        }

//        public Builder setLoggingInterceptor(CustomHttpLogInterceptor loggingInterceptor) {
//            this.loggingInterceptor = loggingInterceptor;
//            return this;
//        }

        public Builder setSslSocketFactory(SSLSocketFactory sslSocketFactory) {
            this.sslSocketFactory = sslSocketFactory;
            return this;
        }

        public Builder setInterceptor(Interceptor interceptor) {
            if (interceptor != null) {
                interceptors.add(interceptor);
            }
            return this;
        }

        public Builder setNetworkInterceptor(Interceptor interceptor) {
            if (interceptor != null) {
                networkInterceptors.add(interceptor);
            }
            return this;
        }

        public Builder setOkHttpClient(OkHttpClient okHttpClient) {
            this.okHttpClient = okHttpClient;
            return this;
        }

        public Builder setRetrofit(Retrofit retrofit) {
            this.retrofit = retrofit;
            return this;
        }

        public HttpManager build() {

            HttpManager httpManager = new HttpManager(this);

            return httpManager;
        }
    }

}
