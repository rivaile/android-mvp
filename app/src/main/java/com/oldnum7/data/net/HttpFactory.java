package com.oldnum7.data.net;

import com.oldnum7.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Http 请求封装
 */
public class HttpFactory {

    private static final String TAG = "HttpFactory";

    private static final long CACHE_SIZE = 1024 * 1024 * 10;

    // base url for Http request
    private String mBaseUrl;

    private long mReadTimeout = 10000;

    private long mWriteTimeout = 1000;

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

    private HttpFactory(Builder builder) {

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
//        CustomHttpLogInterceptor logInterceptor = new CustomHttpLogInterceptor();
//        if (BuildConfig.DEBUG) {
//            logInterceptor.setLevel(CustomHttpLogInterceptor.Level.BODY);
//        } else {
//            logInterceptor.setLevel(CustomHttpLogInterceptor.Level.BASIC);
//        }
        // for test
//        logInterceptor.setLevel(CustomHttpLogInterceptor.Level.BODY);
//        interceptors.add(logInterceptor);
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

    /**
     * 设置HTTPS相关
     */
//    private void setSslSocketFactory() {
//
//        try {
//            // 这里直接创建一个不做证书串验证的TrustManager
//            mX509TrustManager = new X509TrustManager() {
//                @Override
//                public void checkClientTrusted(X509Certificate[] chain, String authType)
//                        throws CertificateException {
//                }
//
//                @Override
//                public void checkServerTrusted(X509Certificate[] chain, String authType)
//                        throws CertificateException {
//
//                    if (chain == null) {
//                        throw new IllegalArgumentException("check server X509Certificate is null");
//                    }
//
//                    if (chain.length < 0) {
//                        throw new IllegalArgumentException("check server X509Certificate is empty");
//                    }
//
//                    for (X509Certificate cert : chain) {
//
//                        // 检查服务端证书是否有问题
//                        cert.checkValidity();
//
//                        String certName = "51zhanke.com_release.bks";
//                        try {
//                            InputStream in = new BufferedInputStream(BaseApplication.getContext().getAssets().open(certName));
//                            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
//                            List<X509Certificate> serverCert = (List<X509Certificate>) certificateFactory.generateCertificates(in);
//
//                            // 和App内证书做比对
//                            for (X509Certificate x509Certificate : serverCert) {
//                                cert.verify(x509Certificate.getPublicKey());
//                            }
//
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        } catch (NoSuchAlgorithmException e) {
//                            e.printStackTrace();
//                        } catch (SignatureException e) {
//                            e.printStackTrace();
//                        } catch (NoSuchProviderException e) {
//                            e.printStackTrace();
//                        } catch (InvalidKeyException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//
//                @Override
//                public X509Certificate[] getAcceptedIssuers() {
//                    return new X509Certificate[]{};
//                }
//            };
//
//            hostnameVerifier = new HostnameVerifier() {
//                @Override
//                public boolean verify(String hostname, SSLSession session) {
//                    // 对Hostname 进行强校验
//                    if (hostname.equals(session.getPeerHost())) {
//                        return true;
//                    } else {
//                        return false;
//                    }
//                }
//            };
//
//            // Install the all-trusting trust manager
//            final SSLContext sslContext = SSLContext.getInstance("TLS");
//            sslContext.init(null, new TrustManager[]{mX509TrustManager}, new java.security.SecureRandom());
//            // Create an ssl socket factory with our all-trusting manager
//            mSslSocketFactory = sslContext.getSocketFactory();
//        } catch (Exception e) {
//            Log.e(TAG, e.getMessage());
//        }
//    }
    private void createHttpClient() {
//        File cacheFile = new File(BaseApplication.getContext().getCacheDir(), "app_cache");
//        Cache cache = new Cache(cacheFile, CACHE_SIZE);

        // create OkHttpClient instance
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        // network interceptor
//        builder.addNetworkInterceptor(new StethoInterceptor());
        //设置读、写、连接超时
        builder.readTimeout(mReadTimeout, TimeUnit.MILLISECONDS);
        builder.connectTimeout(mConnectTimeout, TimeUnit.MILLISECONDS);
        builder.writeTimeout(mWriteTimeout, TimeUnit.MILLISECONDS);
        builder.retryOnConnectionFailure(mRetryOnConnectionFailure);

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

        public HttpFactory build() {

            HttpFactory httpFactory = new HttpFactory(this);

            return httpFactory;
        }

    }

}
