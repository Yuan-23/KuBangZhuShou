package com.coupon.coupon580.core.mvp;

/**
 * @author NoOne 2019.05.19
 */
public abstract class BaseView {

    protected BasePresenter mPresenter;

    public abstract void initPresenter();

    public BasePresenter getPresenter() {
        return mPresenter;
    }
}
