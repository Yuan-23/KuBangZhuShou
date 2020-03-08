package com.coupon.coupon580.core.network;

/**
 * 网络请求解析空数据异常
 * Created by NoOne on 2017/12/29 0029.
 */

public class EmptyDataException extends Exception {
    public EmptyDataException() {
        super();
    }

    public EmptyDataException(String message) {
        super(message);
    }

    public EmptyDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyDataException(Throwable cause) {
        super(cause);
    }
}
