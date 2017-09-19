package com.oldnum7.androidlib.http.model;

/**
 * <pre>
 *       author : denglin
 *       time   : 2017/06/05/14:07
 *       desc   : 封装的返回实体类...
 *       version: 1.0
 * </pre>
 */
public class HttpResponse<T> {

    private String error;

    private String msg;

    private int status;

    private T result;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "HttpResponse{" +
                "error='" + error + '\'' +
                ", msg='" + msg + '\'' +
                ", status='" + status + '\'' +
                ", result=" + result +
                '}';
    }
}
