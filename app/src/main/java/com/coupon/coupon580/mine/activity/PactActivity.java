package com.coupon.coupon580.mine.activity;

import android.view.View;
import android.widget.LinearLayout;

import com.coupon.coupon580.R;
import com.coupon.coupon580.core.base.BaseActivity;
import com.coupon.coupon580.core.widget.MyToolbar;

import butterknife.BindView;
import butterknife.OnClick;

public class PactActivity extends BaseActivity {

    @BindView(R.id.pact_p)
    LinearLayout mPactp;
    @BindView(R.id.pact_state)
    LinearLayout mPacts;
    @BindView(R.id.tool_bar)
    MyToolbar pToolBar;

    @Override
    public int getLayoutId() {
        return R.layout.activity_pact;
    }

    @Override
    public void initView() {
        pToolBar.setPadding(0, getStatusBarHeight(), 0, 0);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @OnClick({R.id.pact_state, R.id.pact_p})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pact_p:
                startActivity(PactPActivity.class);
                break;
            case R.id.pact_state:
                startActivity(PactStateActivity.class);
                break;
        }
    }
}
