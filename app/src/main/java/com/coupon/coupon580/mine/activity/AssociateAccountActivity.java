package com.coupon.coupon580.mine.activity;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.coupon.coupon580.R;
import com.coupon.coupon580.core.base.BaseActivity;
import com.coupon.coupon580.core.widget.MyToolbar;

import butterknife.BindView;
import butterknife.OnClick;

public class AssociateAccountActivity extends BaseActivity {


    @BindView(R.id.tool_bar)
    MyToolbar mToolBar;
    @BindView(R.id.qq_ll)
    LinearLayout mQqLl;
    @BindView(R.id.weibo_ll)
    LinearLayout mWeiboLl;
    @BindView(R.id.wechat_ll)
    LinearLayout mWechatLl;
    @BindView(R.id.qq_bind_state_tv)
    TextView mQqBindStateTv;
    @BindView(R.id.weibo_bind_state_tv)
    TextView mWeiboBindStateTv;
    @BindView(R.id.wechat_bind_state_tv)
    TextView mWechatBindStateTv;

    @Override
    public int getLayoutId() {
        return R.layout.activity_associate_account;
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

    @OnClick({R.id.qq_ll, R.id.weibo_ll, R.id.wechat_ll})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.qq_ll:
                showToast("尚未实现功能");
                break;
            case R.id.weibo_ll:
                showToast("尚未实现功能");
                break;
            case R.id.wechat_ll:
                showToast("尚未实现功能");
                break;
        }
    }
}
