package com.coupon.coupon580.search.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * @author NoOne 2019.03.24
 */
public class MessageBean implements MultiItemEntity {

    private static final String TAG = "MessageBean";

    public static final int TYPE_ROBOT = 0;
    public static final int TYPE_MINE = 1;

    private int mType;

    private String mMsg;

    public MessageBean(int type) {
        mType = type;
    }

    public MessageBean(int type, String msg) {
        mType = type;
        mMsg = msg;
    }

    @Override
    public int getItemType() {
        return mType;
    }


    public String getMsg() {
        return mMsg == null ? "" : mMsg;
    }

    public void setMsg(String msg) {
        mMsg = msg;
    }
}
