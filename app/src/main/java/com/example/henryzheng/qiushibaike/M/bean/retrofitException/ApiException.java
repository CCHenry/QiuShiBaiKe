package com.example.henryzheng.qiushibaike.M.bean.retrofitException;

/**
 * Created by henryzheng on 2017/1/24.
 */
/**
 * Created by 12262 on 2016/5/31.
 * view层自定义异常类
 */
public class ApiException extends Exception {
    private int code;
    //用于展示的异常信息
    private String displayMessage;

    public ApiException(Throwable throwable, int code) {
        super(throwable);
        this.code = code;

    }

    public void setDisplayMessage(String displayMessage) {
        this.displayMessage = displayMessage;
    }

    public String getDisplayMessage() {
        return displayMessage;
    }

    public int getCode() {
        return code;
    }
}