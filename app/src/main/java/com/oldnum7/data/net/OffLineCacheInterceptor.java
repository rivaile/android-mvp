package com.oldnum7.data.net;

import com.blankj.utilcode.util.NetworkUtils;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


/**
 * <pre>
 *       author : denglin
 *       time   : 2017/06/07/16:35
 *       desc   : 只针Get请求有效...Post请求无效...设置给应用拦截器...应用拦截器在无网的时候有效...有网的时候直接请求网络...
 *       version: 1.0
 * </pre>
 */
public class OffLineCacheInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        if (!NetworkUtils.isConnected()) {
            //无网络下强制使用缓存，无论缓存是否过期,此时该请求实际上不会被发送出去。
            request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE)
                    .build();
            Response response = chain.proceed(request);
            return response.newBuilder().header("Cache-Control", "public,only-if-cached,max-stale=360000")
                    .removeHeader("Pragma")
                    .build();
        } else {
            //有网络的时候，这个拦截器不做处理，直接返回
            return chain.proceed(request);
        }

    }
}


