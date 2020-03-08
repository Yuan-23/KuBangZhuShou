package com.coupon.coupon580.mine.activity;

import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.coupon.coupon580.R;
import com.coupon.coupon580.core.base.BaseActivity;
import com.coupon.coupon580.core.data.SharedPreDataHelper;
import com.coupon.coupon580.core.data.UrlConstants;
import com.coupon.coupon580.core.helper.LoginHelper;
import com.coupon.coupon580.core.model.SimpleResponse;
import com.coupon.coupon580.core.network.okgo.GsonCallback;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {


    @BindView(R.id.login_btn)
    TextView mLoginBtn;
    @BindView(R.id.register_btn)
    TextView mRegisterBtn;
    @BindView(R.id.user_name_et)
    TextInputEditText mUserNameEt;
    @BindView(R.id.password_et)
    TextInputEditText mPasswordEt;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
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

    @OnClick(R.id.login_btn)
    public void onClickLogin() {
        if (TextUtils.isEmpty(mUserNameEt.getText())) {
            showToast("请输入手机号");
        }
        else if(TextUtils.isEmpty(mPasswordEt.getText()))
        {
            showToast("请输入密码");
        }
        else {
            OkGo.<SimpleResponse>post(UrlConstants.login)
                    .params("username", mUserNameEt.getText().toString())
                    .params("password", mPasswordEt.getText().toString())
                    .execute(new GsonCallback<SimpleResponse>(SimpleResponse.class) {
                        @Override
                        public void onSuccess(Response<SimpleResponse> response) {
                            SimpleResponse body = response.body();
                            if (body.isSuccess()) {
                                showToast("登录成功");
                                SharedPreDataHelper.setUserId(mUserNameEt.getText().toString());
                                LoginHelper.login();
                                finish();
                            } else if (body.code == 402) {
                                showToast("首次登录，需设置兴趣标签");
                                SharedPreDataHelper.setUserId(mUserNameEt.getText().toString());
                                LoginHelper.login();
                                startActivity(ChooseTipsActivity.class);
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

    @OnClick(R.id.register_btn)
    public void onClickRegisterBtn() {
        startActivity(RegisterActivity.class);
    }

    @OnClick(R.id.forgetpsw_btn)
    public void onClickForgetBtn(){
        startActivity(ForgetPswActivity.class);
    }

    @OnClick(R.id.back_btn)
    public void onClickBackBtn() {
        finish();
    }
}
