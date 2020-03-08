package com.coupon.coupon580.core.network.okgo;

import android.text.TextUtils;
import android.util.Log;

import com.lzy.okgo.convert.Converter;
import com.coupon.coupon580.core.network.EmptyDataException;

import org.json.JSONArray;

import okhttp3.Response;

public class JsonArrayConvert implements Converter<JSONArray> {
    @Override
    public JSONArray convertResponse(Response response) throws Throwable {
        if (response.body() == null) {
            throw new EmptyDataException("response.body() == null");
        }
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAA");
        String json = response.body().string();

        Log.d("OkGo+++++++++++", "Response1111111111: " + json);

        if (TextUtils.isEmpty(json)) {
            throw new EmptyDataException("dataStr is empty");
        } else {
            return new JSONArray(json);
        }
    }
}
