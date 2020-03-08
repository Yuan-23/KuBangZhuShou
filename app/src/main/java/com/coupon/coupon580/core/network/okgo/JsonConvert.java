package com.coupon.coupon580.core.network.okgo;

import android.text.TextUtils;
import android.util.Log;

import com.lzy.okgo.convert.Converter;
import com.coupon.coupon580.core.network.EmptyDataException;

import org.json.JSONObject;

import okhttp3.Response;

public class JsonConvert implements Converter<JSONObject> {
    @Override
    public JSONObject convertResponse(Response response) throws Throwable {
        if (response.body() == null) {
            throw new EmptyDataException("response.body() == null");
        }


        String json = response.body().string();

        Log.d("OkGo", "Response: " + json);

        if (TextUtils.isEmpty(json)) {
            throw new EmptyDataException("dataStr is empty");
        } else {
            return new JSONObject(json);
        }
    }
}
