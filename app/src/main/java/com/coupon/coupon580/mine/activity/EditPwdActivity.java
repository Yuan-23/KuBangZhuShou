package com.coupon.coupon580.mine.activity;

import android.widget.EditText;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.coupon.coupon580.R;
import com.coupon.coupon580.core.base.BaseActivity;
import com.coupon.coupon580.core.data.SharedPreDataHelper;
import com.coupon.coupon580.core.data.UrlConstants;
import com.coupon.coupon580.core.model.SimpleResponse;
import com.coupon.coupon580.core.network.okgo.GsonCallback;
import com.coupon.coupon580.core.widget.MyToolbar;

import butterknife.BindView;
import butterknife.OnClick;

public class EditPwdActivity extends BaseActivity {


    @BindView(R.id.tool_bar)
    MyToolbar mToolBar;
    @BindView(R.id.confirm_btn)
    TextView mConfirmBtn;
    @BindView(R.id.old_pwd_et)
    EditText mOldPwdEt;
    @BindView(R.id.new_pwd_et)
    EditText mNewPwdEt;
    @BindView(R.id.new_pwd_check_et)
    EditText mNewPwdCheckEt;

    @Override
    public int getLayoutId() {
        return R.layout.activity_edit_pwd;
    }

    @Override
    public void initView() {
        mToolBar.setPadding(0, getStatusBarHeight(), 0, 0);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @OnClick(R.id.confirm_btn)
    public void onClick() {
        if (!mNewPwdEt.getText().toString().equals(mNewPwdCheckEt.getText().toString())) {
            showToast("两次密码输入不一致，请确认");
            return;
        }

        OkGo.<SimpleResponse>post(UrlConstants.updatePassword)
                .params("user_id", SharedPreDataHelper.getUserId())
                .params("password", mOldPwdEt.getText().toString())
                .params("password1", mNewPwdCheckEt.getText().toString())
                .execute(new GsonCallback<SimpleResponse>(SimpleResponse.class) {
                    @Override
                    public void onSuccess(Response<SimpleResponse> response) {
                        SimpleResponse simpleResponse = response.body();
                        showToast(simpleResponse.msg);
                        if (simpleResponse.isSuccess()) {
                            finish();
                        }
                    }

                    @Override
                    public void onError(Response<SimpleResponse> response) {
                        super.onError(response);
                    }
                });
    }
}
