package com.coupon.coupon580.core.network.okgo;

import com.blankj.utilcode.util.GsonUtils;
import com.google.gson.Gson;
import com.lzy.okgo.callback.AbsCallback;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;

import okhttp3.Response;
import okhttp3.ResponseBody;

public abstract class GsonCallback<T> extends AbsCallback<T> {

    private Type type;
    private Class<T> clazz;


    public GsonCallback(Type type) {
        this.type = type;
    }

    public GsonCallback(Class<T> clazz) {
        this.clazz = clazz;
    }


    @Override
    public T convertResponse(Response response) throws Throwable {
        ResponseBody body = response.body();
        if (body == null) return null;

        T data = null;
        Gson gson = GsonUtils.getGson();

        if (type != null) {
            JsonArrayConvert jsonArrayConvert = new JsonArrayConvert();
            JSONArray jsonArray = jsonArrayConvert.convertResponse(response);
            data = gson.fromJson(jsonArray.toString(), type);
        }
        if (clazz != null) {
            JsonConvert jsonConvert = new JsonConvert();
            JSONObject jsonObject = jsonConvert.convertResponse(response);
            data = gson.fromJson(jsonObject.toString(), clazz);
        }

        response.close();
        return data;
    }
}
