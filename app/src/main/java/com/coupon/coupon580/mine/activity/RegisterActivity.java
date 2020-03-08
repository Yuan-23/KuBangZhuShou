package com.coupon.coupon580.mine.activity;

import android.os.CountDownTimer;
import android.support.design.widget.TextInputEditText;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.coupon.coupon580.R;
import com.coupon.coupon580.core.base.BaseActivity;
import com.coupon.coupon580.core.data.UrlConstants;
import com.coupon.coupon580.core.model.SimpleResponse;
import com.coupon.coupon580.core.network.okgo.GsonCallback;
import com.coupon.coupon580.core.utils.RandomStrUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {

    private boolean isChecked;

    @BindView(R.id.register_btn)
    TextView mRegisterBtn;
    @BindView(R.id.phone_et)
    TextInputEditText mPhoneEt;
    /*@BindView(R.id.user_name_et)
    TextInputEditText mUserNameEt;*/
    @BindView(R.id.password_et)
    TextInputEditText mPasswordEt;
    @BindView(R.id.password_et_check)
    TextInputEditText mPasswordCk;
    @BindView(R.id.AgreeBotton)
    CheckBox mAgree;
    @BindView(R.id.Agreetext)
    TextView mAgreeText;
    @BindView(R.id.getcode_btn)
    Button mGetcode;
    @BindView(R.id.code_et)
    TextInputEditText mSendcode;
    @BindView(R.id.rec_code_rv)
    TextInputEditText mRecCode;

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void initView() {
        setStatusBarTextBlack(false);
        initText();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    //用户协议与免责声明 文本与点击事件
    private void initText() {
        SpannableString spannableString = new SpannableString("我已阅读并同意《用户协议》和《免责声明》");


        ClickableSpan clickPact = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                startActivity(PactPActivity.class);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                ds.setColor(ContextCompat.getColor(RegisterActivity.this, R.color.colorPrimary));//设置颜色
                ds.setUnderlineText(false);//去掉下划线
            }
        };
        spannableString.setSpan(clickPact, 7, 13, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);

        ClickableSpan clickState = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                startActivity(PactStateActivity.class);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                ds.setColor(ContextCompat.getColor(RegisterActivity.this, R.color.colorPrimary));//设置颜色
                ds.setUnderlineText(false);//去掉下划线
            }

        };
        spannableString.setSpan(clickState, 14, 20, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);

        ForegroundColorSpan colorSpan = new ForegroundColorSpan(ContextCompat.getColor(this, R.color.colorPrimary));
        spannableString.setSpan(colorSpan, 7, 13, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);

        ForegroundColorSpan colorSpan2 = new ForegroundColorSpan(ContextCompat.getColor(this, R.color.colorPrimary));
        spannableString.setSpan(colorSpan2, 14, 20, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);

        mAgreeText.setText(spannableString);
        mAgreeText.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @OnClick(R.id.getcode_btn)
    public void onClickGetCode(){
        if (TextUtils.isEmpty(mPhoneEt.getText())){
            showToast("请输入手机号");
        }
        else {
            OkGo.<SimpleResponse>post(UrlConstants.sendCode)
                    .params("phonenum",mPhoneEt.getText().toString())
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
                    mGetcode.setEnabled(false);
                    mGetcode.setText(millisUntilFinished / 1000 + "s");

                }

                @Override
                public void onFinish() {
                    mGetcode.setEnabled(true);
                    mGetcode.setText("重新获取验证码");

                }
            }.start();
        }
    }

    @OnClick(R.id.register_btn)
    public void onClickRegister() {
        if (TextUtils.isEmpty(mPhoneEt.getText())) {
            showToast("请输入手机号");
        }
        /*else if(TextUtils.isEmpty(mUserNameEt.getText()))
        {
            showToast("请输入用户名");
        }*/
        else if(TextUtils.isEmpty(mPasswordEt.getText()))
        {
            showToast("请输入密码");
        }
        else if (!mPasswordEt.getText().toString().equals(mPasswordCk.getText().toString())) {
            showToast("两次密码输入不一致，请确认");
        }
        else if (TextUtils.isEmpty(mSendcode.getText())){
            showToast("请输入验证码");
        }
        else if (!mAgree.isChecked()) {
            showToast("请阅读并同意《用户协议》及《隐私政策》");
        }

        else {
            String rec_code = RandomStrUtils.getRandomString(8);
            OkGo.<SimpleResponse>post(UrlConstants.register)
                    .params("phonenum", mPhoneEt.getText().toString())
                    .params("username", "用户"+rec_code)
                    .params("password", mPasswordEt.getText().toString())
                    .params("rec_code", rec_code)
                    .params("con_code", mSendcode.getText().toString())
                    .params("other_rec_code", mRecCode.getText().toString())
                    .execute(new GsonCallback<SimpleResponse>(SimpleResponse.class) {
                        @Override
                        public void onSuccess(Response<SimpleResponse> response) {
                            if (response.body().isSuccess()) {
                                showToast("注册成功");
                                System.out.println("++注册成功++");
                                finish();
                            } else {
                                showToast(response.body().msg);
                                System.out.println("++注册失败++");
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

    @OnClick(R.id.back_btn)
    public void onClickBackBtn() {
        finish();
    }
}