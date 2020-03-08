package com.coupon.coupon580.core.utils;

import android.os.CountDownTimer;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 倒计时View帮助类
 * @author: NoOne
 * @date: 2019-04-11 15:11
 */
public class CountDownTimerHelper {

    private static Map<String, CountDownTimer> sTimerMap = new HashMap<>();

    /**
     * @param tag
     * @param second
     * @param hourView
     * @param minuteView
     * @param secondView
     */
    public static void addCountDownTimer(String tag, long second, final TextView hourView, final TextView minuteView, final TextView secondView) {

        CountDownTimer countDownTimer = new CountDownTimer(second * 1000L, 1000L) {
            @Override
            public void onTick(long millisUntilFinished) {
                millisUntilFinished /= 1000;
                long hour = millisUntilFinished / 3600;
                millisUntilFinished %= 3600;
                long min = millisUntilFinished / 60;
                millisUntilFinished %= 60;
                long second = millisUntilFinished;
                if (hour >= 24) {
                    hourView.setText("24");
                    minuteView.setText("00");
                    secondView.setText("00");
                    return;
                }

                hour %= 24;
                hourView.setText((hour < 10 ? "0" : "") + hour);
                minuteView.setText((min < 10 ? "0" : "") + min);
                secondView.setText((second < 10 ? "0" : "") + second);
            }

            @Override
            public void onFinish() {
                hourView.setText("00");
                minuteView.setText("00");
                secondView.setText("00");
            }
        }.start();

        sTimerMap.put(tag, countDownTimer);
    }


    public static void removeCountDownTimer(String tag) {
        CountDownTimer countDownTimer = sTimerMap.remove(tag);
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}
