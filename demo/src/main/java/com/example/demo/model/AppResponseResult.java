package com.example.demo.model;

/**
 * 接口响应结果封装
 */
public class AppResponseResult<T> {

    private int code;

    private String message;

    private T result;

    public AppResponseResult() {
        this.code = 200;
        this.message = "SUCCESS";
        this.result = null;
    }

    public AppResponseResult(T result) {
        this();
        this.result = result;
    }

    public AppResponseResult(int code, String message) {
        this();
        this.code = code;
        this.message = message;
    }

    public AppResponseResult(int code, String message, T result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
