package com.coupon.coupon580.core.network.retrofit;

import java.io.Serializable;

/**
 * @description: 网络请求返回数据封装类
 * @author: NoOne
 * @date: 2019-04-28 10:55
 */
public class BaseResponse<T> implements Serializable {

    public static final int SUCCESS_CODE = 200;

    private int code;

    private String message;

    private T data;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    /**
     * 请求是否成功
     *
     * @return success
     */
    public boolean isSuccess() {
        return SUCCESS_CODE == code;
    }
}
