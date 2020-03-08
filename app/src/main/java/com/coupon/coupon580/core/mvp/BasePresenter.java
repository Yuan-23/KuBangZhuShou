package com.coupon.coupon580.core.mvp;

import java.lang.ref.WeakReference;

/**
 * @author NoOne 2018.08.14
 */
public abstract class BasePresenter<MvpView, MvpModel> {
    private WeakReference<MvpView> mMvpView;

    public BasePresenter(MvpView view) {
        mMvpView = new WeakReference<>(view);
    }

    public MvpView getView() {
        return mMvpView.get();
    }

}
