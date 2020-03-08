package com.coupon.coupon580.mine.model;

/**
 * 账单收支
 *
 * @author NoOne 2019.03.10
 */
public class BillBean {

    private long time;
    // 0：提现 1：利润
    private int type;
    private double moneyNum;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getMoneyNum() {
        return moneyNum;
    }

    public void setMoneyNum(double moneyNum) {
        this.moneyNum = moneyNum;
    }
}
