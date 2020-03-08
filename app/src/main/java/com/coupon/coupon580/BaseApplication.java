package com.coupon.coupon580;

import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.SPCookieStore;

import okhttp3.OkHttpClient;

/**
 * @author NoOne 2018.12.22
 */
public class BaseApplication extends Application {

    private static Application sInstance;

    public static Application getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        // BlankJUtilCode
        Utils.init(this);

        // OkGo
        OkGo.getInstance().init(this);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cookieJar(new CookieJarImpl(new SPCookieStore(this)))
                .build();
        OkGo.getInstance().setOkHttpClient(okHttpClient);


    }
}
