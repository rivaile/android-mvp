package com.oldnum7.androidlib.http.interceptor;

import com.blankj.utilcode.util.NetworkUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


/**
 * <pre>
 *       author : denglin
 *       time   : 2017/06/07/16:35
 *       desc   : 只针Get请求有效...Post请求无效... 在线网络缓存...只有有网的时候再会有效...无网的时候不会拦截..
 *       version: 1.0
 * </pre>
 */
public class OnLineCacheInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        if (NetworkUtils.isConnected()) {
            Response response = chain.proceed(request);
            //有网络情况下，根据请求接口的设置，配置缓存。
            //这样在下次请求时，根据缓存决定是否真正发出请求。
            String cacheControl = request.cacheControl().toString();
            //当然如果你想在有网络的情况下都直接走网络，那么只需要
            //将其超时时间这是为0即可:
            // String cacheControl="Cache-Control:public,max-age=0"
            return response.newBuilder().header("Cache-Control", cacheControl)
                    .removeHeader("Pragma")
                    .build();
        } else {

            return chain.proceed(request);
        }
    }
}


