package com.coupon.coupon580.core.activity;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.RelativeLayout;
import com.coupon.coupon580.R;
import com.coupon.coupon580.core.base.BaseActivity;
import com.coupon.coupon580.core.widget.RoundTextView;

import butterknife.BindView;

public class SplashActivity extends BaseActivity {


   @BindView(R.id.count_down_tv)
    RoundTextView mCountDownTv;

    private CountDownTimer mTimer;

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView() {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) mCountDownTv.getLayoutParams();
        layoutParams.topMargin += getStatusBarHeight();
        mCountDownTv.setLayoutParams(layoutParams);
    }

    @Override
    public void initData() {
        mTimer = new CountDownTimer(3 * 1000L, 1000L) {
            @Override
            public void onTick(long millisUntilFinished) {
                int second = (int) (millisUntilFinished / 1000);
                if (second > 2) {
                    return;
                }
                mCountDownTv.setVisibility(View.VISIBLE);
                mCountDownTv.setText("点击跳过 " + second);
            }

            @Override
            public void onFinish() {
                finish();
                startActivity(MainActivity.class);
            }
        }.start();
    }

    @Override
    public void initListener() {
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTimer.cancel();
                finish();
                startActivity(MainActivity.class);
            }
        };
        mCountDownTv.setOnClickListener(onClickListener);
    }

}
