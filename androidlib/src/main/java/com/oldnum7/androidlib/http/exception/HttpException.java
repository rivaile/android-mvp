package com.oldnum7.androidlib.http.exception;


import com.oldnum7.androidlib.http.model.HttpResponse;
import com.oldnum7.androidlib.http.utils.HttpUtils;

/**
 * <pre>
 *       author : denglin
 *       time   : 2017/06/05/19:29
 *       desc   :
 *       version: 1.0
 * </pre>
 */
public class HttpException extends RuntimeException {

    private int code;                               //HTTP status code
    private String message;                         //HTTP status message
    private HttpResponse<?> response;             //The full HTTP response. This may be null if the exception was serialized

    public HttpException(String message) {
        super(message);
    }

    public HttpException(HttpResponse<?> response) {
        super(getMessage(response));
        this.code = response.getStatus();
        this.message = response.getMsg();
        this.response = response;
    }

    private static String getMessage(HttpResponse<?> response) {
        HttpUtils.checkNotNull(response, "response == null");
        return "HTTP " + response.getStatus() + " " + response.getMsg();
    }

    public int code() {
        return code;
    }

    public String message() {
        return message;
    }

    public HttpResponse<?> response() {
        return response;
    }

    public static HttpException NET_ERROR() {
        return new HttpException("network error! http response code is 404 or 5xx!");
    }

    public static HttpException COMMON(String message) {
        return new HttpException(message);
    }
}
