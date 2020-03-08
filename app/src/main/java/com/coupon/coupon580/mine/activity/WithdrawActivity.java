package com.coupon.coupon580.mine.activity;

import android.widget.EditText;
import android.widget.TextView;

import com.coupon.coupon580.R;
import com.coupon.coupon580.core.base.BaseActivity;
import com.coupon.coupon580.core.widget.MyToolbar;

import butterknife.BindView;
import butterknife.OnClick;

public class WithdrawActivity extends BaseActivity {


    @BindView(R.id.tool_bar)
    MyToolbar mToolBar;
    @BindView(R.id.withdraw_et)
    EditText mWithdrawEt;
    @BindView(R.id.withdraw_btn)
    TextView mWithdrawBtn;

    @Override
    public int getLayoutId() {
        return R.layout.activity_withdraw;
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

    @OnClick(R.id.withdraw_btn)
    public void onClick() {
        showToast("提现暂未开放");
        finish();
    }
}
