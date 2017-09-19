package com.oldnum7.http;

import com.oldnum7.mvp.base.BaseApplication;
import com.oldnum7.BuildConfig;
import com.oldnum7.Constants;
import com.oldnum7.http.cookie.CookieJarImpl;
import com.oldnum7.http.cookie.store.MemoryCookieStore;
import com.oldnum7.http.https.HttpsUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;

import okhttp3.Cache;
import okhttp3.CookieJar;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * <pre>
 *       author : denglin
 *       time   : 2017/06/02/17:28
 *       desc   : Http 请求封装
 *       version: 1.0
 * </pre>
 */

public class HttpFactory {
    public static final long DEFAULT_MILLISECONDS = 30000;      //默认的超时时间

    private static final long CACHE_SIZE = 1024 * 1024 * 10;

    // base url for Http request
    private String mBaseUrl;

    private long mReadTimeout = DEFAULT_MILLISECONDS;

    private long mWriteTimeout = DEFAULT_MILLISECONDS;

    private long mConnectTimeout = DEFAULT_MILLISECONDS;

    // 是否失败重连
    private boolean mRetryOnConnectionFailure = true;

    private CookieJar mCookieJar;

    // ssl 管理
    private HttpsUtils.SSLParams mSSLParams;
    private HostnameVerifier mHostnameVerifier;


    private List<Interceptor> interceptors = new ArrayList<>();

    private List<Interceptor> networkInterceptors = new ArrayList<>();

    private OkHttpClient mOkHttpClient;

    private Retrofit mRetrofit;

    private HttpFactory(Builder builder) {

        if (null == builder.baseUrl) {
            setBaseUrl();
        } else {
            this.mBaseUrl = builder.baseUrl;
        }

        if (null == builder.cookieJar) {
            mCookieJar = new CookieJarImpl(new MemoryCookieStore());
        } else {
            this.mCookieJar = builder.cookieJar;
        }

        if (null == builder.sslParams) {
            //默认信任所有证书,不安全有风险
            mSSLParams = HttpsUtils.getSslSocketFactory();
        } else {
            this.mSSLParams = builder.sslParams;
        }

        if (null == builder.hostnameVerifier) {
            //默认信任所有证书,不安全有风险
            mHostnameVerifier = HttpsUtils.UnSafeHostnameVerifier;
        } else {
            this.mHostnameVerifier = builder.hostnameVerifier;
        }


        if (0 < builder.readTimeout) {
            this.mReadTimeout = builder.readTimeout;
        }

        if (0 < builder.writeTimeout) {
            this.mWriteTimeout = builder.writeTimeout;
        }

        if (0 < builder.connectTimeout) {
            this.mConnectTimeout = builder.connectTimeout;
        }

        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            logInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        }

        interceptors.add(logInterceptor);
        interceptors.addAll(builder.interceptors);

        // add Network interceptors
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

        File cacheFile = new File(BaseApplication.getContext().getCacheDir(), "app_cache");
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

        //自动管理cookie（或者叫session的保持），以下几种任选其一就行
//        builder.cookieJar(new CookieJarImpl(new SPCookieStore(BaseApplication.getContext())));            //使用sp保持cookie，如果cookie不过期，则一直有效
//        builder.cookieJar(new CookieJarImpl(new DBCookieStore(this)));                                     //使用数据库保持cookie，如果cookie不过期，则一直有效
        //builder.cookieJar(new CookieJarImpl(new MemoryCookieStore()));                                     //使用内存保持cookie，app退出后，cookie消失
        builder.cookieJar(mCookieJar);

//        //方法一：信任所有证书,不安全有风险
//        HttpsUtils.SSLParams sslParams1 = HttpsUtils.getSslSocketFactory();
//        //方法二：自定义信任规则，校验服务端证书
//        HttpsUtils.SSLParams sslParams2 = HttpsUtils.getSslSocketFactory(new SafeTrustManager());
//        //方法三：使用预埋证书，校验服务端证书（自签名证书）
//        HttpsUtils.SSLParams sslParams3 = HttpsUtils.getSslSocketFactory(getAssets().open("srca.cer"));
//        //方法四：使用bks证书和密码管理客户端证书（双向认证），使用预埋证书，校验服务端证书（自签名证书）
//        HttpsUtils.SSLParams sslParams4 = HttpsUtils.getSslSocketFactory(getAssets().open("xxx.bks"), "123456", getAssets().open("yyy.cer"));
//        builder.sslSocketFactory(sslParams1.sSLSocketFactory, sslParams1.trustManager);
//        //配置https的域名匹配规则，详细看demo的初始化介绍，不需要就不要加入，使用不当会导致https握手失败
//        builder.mHostnameVerifier(new SafeHostnameVerifier());

        builder.sslSocketFactory(mSSLParams.sSLSocketFactory, mSSLParams.trustManager);
        //默认不做校验...
        builder.hostnameVerifier(mHostnameVerifier);
        //TODO...注:此处证书校验跟系统版本有关系...还需完善...
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

    public Retrofit getRetrofit() {
        return mRetrofit;
    }

    public OkHttpClient getOkhttpClient() {
        return mOkHttpClient;
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
        private OkHttpClient okHttpClient;
        private Retrofit retrofit;

        private boolean retryOnConnectionFailure;
        private HttpsUtils.SSLParams sslParams;
        private HostnameVerifier hostnameVerifier;
        private CookieJar cookieJar;

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

        public Builder setRetryOnConnectionFailure(boolean retryOnConnectionFailure) {
            this.retryOnConnectionFailure = retryOnConnectionFailure;
            return this;
        }

        public Builder setSSLParams(HttpsUtils.SSLParams sslParams) {
            this.sslParams = sslParams;
            return this;
        }


        public Builder setHostnameVerifier(HostnameVerifier hostnameVerifier) {
            this.hostnameVerifier = hostnameVerifier;
            return this;
        }

        public Builder setCookieJar(CookieJar cookieJar) {
            this.cookieJar = cookieJar;
            return this;
        }


        public HttpFactory build() {
            HttpFactory httpFactory = new HttpFactory(this);
            return httpFactory;
        }
    }

}
