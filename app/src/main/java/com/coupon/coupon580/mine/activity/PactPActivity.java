package com.coupon.coupon580.mine.activity;


import com.coupon.coupon580.R;
import com.coupon.coupon580.core.base.BaseActivity;
import com.coupon.coupon580.core.widget.MyToolbar;

import butterknife.BindView;

public class PactPActivity extends BaseActivity {

    @BindView(R.id.tool_bar)
    MyToolbar ppToolBar;

    @Override
    public int getLayoutId() {
        return R.layout.activity_pact_p;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        ppToolBar.setPadding(0, getStatusBarHeight(), 0, 0);
    }


    @Override
    public void initListener() {

    }
}