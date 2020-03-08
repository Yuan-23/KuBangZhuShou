package com.coupon.coupon580.core.utils;

import android.content.Context;
import android.content.SharedPreferences;


public class SharedPreUtil {
    private static final String SharedPreferenceName = "CoolMall";
    private static SharedPreferences sp;
    private static SharedPreferences.Editor editor;

    public SharedPreUtil() {
    }

    private static SharedPreferences getSharedPre(Context context) {
        if (sp == null) {
            sp = context.getSharedPreferences(SharedPreferenceName, Context.MODE_PRIVATE);
            editor = sp.edit();
        }

        return sp;
    }

    public static void putIntValue(Context context, String name, int value) {
        getSharedPre(context);
        editor.putInt(name, value);
        editor.commit();
    }

    public static int getIntValue(Context context, String name, int defaultValue) {
        getSharedPre(context);
        return sp.getInt(name, defaultValue);
    }

    public static void putStringValue(Context context, String name, String value) {
        getSharedPre(context);
        editor.putString(name, value);
        editor.commit();
    }

    public static String getStringValue(Context context, String name, String defaultValue) {
        getSharedPre(context);
        return sp.getString(name, defaultValue);
    }

    public static void putBooleanValue(Context context, String name, boolean value) {
        getSharedPre(context);
        editor.putBoolean(name, value);
        editor.commit();
    }

    public static boolean getBooleanValue(Context context, String name, boolean defaultValue) {
        getSharedPre(context);
        return sp.getBoolean(name, defaultValue);
    }

    public static void putLongValue(Context context, String name, long value) {
        getSharedPre(context);
        editor.putLong(name, value);
        editor.commit();
    }

    public static Long getLongValue(Context context, String name, long defaultValue) {
        getSharedPre(context);
        return sp.getLong(name, defaultValue);
    }

    public static void removeValue(Context context, String... name) {
        getSharedPre(context);

        for (String aName : name) {
            editor.remove(aName);
        }

        editor.commit();
    }

}
