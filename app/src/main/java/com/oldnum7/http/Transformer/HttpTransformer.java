package com.oldnum7.http.Transformer;

import com.oldnum7.Constants;
import com.oldnum7.data.entity.HttpResponse;
import com.oldnum7.http.exception.HttpException;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;

/**
 * <pre>
 *       author : denglin
 *       time   : 2017/09/04/17:15
 *       desc   : 常见转换器定义...
 *       version: 1.0
 * </pre>
 */
public class HttpTransformer {


    public static <T> ObservableTransformer<HttpResponse<T>, T> transform() {
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


}
