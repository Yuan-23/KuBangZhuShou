package com.coupon.coupon580.mine.activity;

import android.os.CountDownTimer;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.coupon.coupon580.R;
import com.coupon.coupon580.core.base.BaseActivity;
import com.coupon.coupon580.core.data.UrlConstants;
import com.coupon.coupon580.core.model.SimpleResponse;
import com.coupon.coupon580.core.network.okgo.GsonCallback;

import butterknife.BindView;
import butterknife.OnClick;

public class ForgetPswActivity extends BaseActivity {

    @BindView(R.id.check_btn)
    TextView fCheckBtn;
    @BindView(R.id.phone_et)
    TextInputEditText fPhoneEt;
    @BindView(R.id.password_et)
    TextInputEditText fPasswordEt;
    @BindView(R.id.password_et_check)
    TextInputEditText fPasswordCk;
    @BindView(R.id.getcode_btn)
    Button fGetcode;
    @BindView(R.id.code_et)
    TextInputEditText fSendcode;

    @Override
    public int getLayoutId() {
        return R.layout.activity_forget_psw;
    }

    @Override
    public void initView() {
        setStatusBarTextBlack(false);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @OnClick(R.id.check_btn)
    public void onClickCheck() {
        if (TextUtils.isEmpty(fPhoneEt.getText())) {
            showToast("请输入手机号");
        }
        else if(TextUtils.isEmpty(fPasswordEt.getText()))
        {
            showToast("请输入新密码");
        }
        else if(TextUtils.isEmpty(fPasswordCk.getText()))
        {
            showToast("请再次输入新密码");
        }
        else if (!fPasswordEt.getText().toString().equals(fPasswordCk.getText().toString())) {
            showToast("两次密码输入不一致，请确认");
        }
        else if (TextUtils.isEmpty(fSendcode.getText())){
            showToast("请输入验证码");
        }
        else {
            OkGo.<SimpleResponse>post(UrlConstants.forgetpsw)
                    .params("phonenum", fPhoneEt.getText().toString())
                    .params("password", fPasswordEt.getText().toString())
                    .params("send_code", fSendcode.getText().toString())
                    .execute(new GsonCallback<SimpleResponse>(SimpleResponse.class) {
                        @Override
                        public void onSuccess(Response<SimpleResponse> response) {
                            SimpleResponse body = response.body();
                            if (body.isSuccess()) {
                                showToast("修改成功");
                                finish();
                            } else {
                                showToast(body.msg);
                            }
                        }


                        @Override
                        public void onError(Response<SimpleResponse> response) {
                            super.onError(response);
                            showToast(getResources().getString(R.string.network_error));
                        }
                    });
        }
    }

    @OnClick(R.id.getcode_btn)
    public void onClickGetCode(){
        if (TextUtils.isEmpty(fPhoneEt.getText())){
            showToast("请输入手机号");
        }
        else {
            OkGo.<SimpleResponse>post(UrlConstants.sendCode)
                    .params("phonenum",fPhoneEt.getText().toString())
                    .execute(new GsonCallback<SimpleResponse>(SimpleResponse.class){
                        @Override
                        public void onSuccess(Response<SimpleResponse> response) {
                            if (response.body().isSuccess()) {
                                showToast("正在获取验证码");
                            } else {
                                showToast(response.body().msg);
                            }
                        }

                        @Override
                        public void onError(Response<SimpleResponse> response) {
                            super.onError(response);
                            showToast(getResources().getString(R.string.network_error));
                        }

                    });

            CountDownTimer timer = new CountDownTimer(60*1000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    fGetcode.setEnabled(false);
                    fGetcode.setText(millisUntilFinished / 1000 + "s");

                }

                @Override
                public void onFinish() {
                    fGetcode.setEnabled(true);
                    fGetcode.setText("重新获取验证码");

                }
            }.start();
        }
    }

    @OnClick(R.id.back_btn)
    public void onClickBackBtn() {
        finish();
    }
}
