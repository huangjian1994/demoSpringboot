package com.example.demo.config;

import com.example.demo.entity.User;

public class DataResult extends User {

    private int code;

    private String message;

    private Object data;

    public DataResult() {

    }

    private DataResult(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static DataResult buildSuccess() {
        return new DataResult(0, "success", null);
    }

    public static DataResult buildSuccess(Object data) {
        return new DataResult(0, "success", data);
    }

    public static DataResult buildError(String message) {
        return new DataResult(1, message, "");
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
