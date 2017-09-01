package com.oldnum7.http.exception;

/**
 * <pre>
 *       author : denglin
 *       time   : 2017/06/05/19:29
 *       desc   :
 *       version: 1.0
 * </pre>
 */
public class HttpException extends RuntimeException {

    private static String message;

    private String error;

    private String msg;

    private int status;

    public HttpException() {

    }
    
    public HttpException(int status, String msg, String error) {
        this.error = error;
        this.status = status;
        this.msg = msg;
    }

    public HttpException(String message) {
        super(message);
    }

    public HttpException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpException(Throwable cause) {
        super(cause);
    }


}
