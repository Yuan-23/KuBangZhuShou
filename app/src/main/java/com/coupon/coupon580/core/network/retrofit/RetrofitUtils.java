package com.coupon.coupon580.core.network.retrofit;

import com.coupon.coupon580.core.data.UrlConstants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @description: Retrofit工具类
 * @author: NoOne
 * @date: 2019-04-25 18:00
 */
public class RetrofitUtils {

    public static final String BASE_URL = UrlConstants.BASE_URL;

    private static volatile Retrofit sRetrofit;

    public static Retrofit getRetrofit() {
        if (sRetrofit == null) {
            synchronized (RetrofitUtils.class) {
                if (sRetrofit == null) {
                    sRetrofit = new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                }
            }
        }
        return sRetrofit;
    }

}
