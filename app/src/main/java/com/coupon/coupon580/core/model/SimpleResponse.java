package com.coupon.coupon580.core.model;

/**
 * @description:
 * @author: NoOne
 * @date: 2019-05-10 10:58
 */
public class SimpleResponse {

    public static final int SUCCESS_CODE = 200;

    public int code;

    public String msg;

    public boolean isSuccess() {
        return code == SUCCESS_CODE;
    }


}
