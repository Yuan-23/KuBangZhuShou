package com.coupon.coupon580.mine.activity;

import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;

import com.blankj.utilcode.util.ConvertUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.nex3z.flowlayout.FlowLayout;
import com.coupon.coupon580.R;
import com.coupon.coupon580.core.base.BaseActivity;
import com.coupon.coupon580.core.data.SharedPreDataHelper;
import com.coupon.coupon580.core.data.UrlConstants;
import com.coupon.coupon580.core.model.SimpleResponse;
import com.coupon.coupon580.core.network.okgo.GsonCallback;
import com.coupon.coupon580.core.widget.RoundTextView;
import com.coupon.coupon580.core.widget.RtvCheckHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ChooseTipsActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.tips_fl)
    FlowLayout mTipsFl;

    private RtvCheckHelper mRtvCheckHelper;


    @Override
    public int getLayoutId() {
        return R.layout.activity_choose_tips;
    }

    @Override
    public void initView() {
        setStatusBarTextBlack(false);
        mRtvCheckHelper = new RtvCheckHelper(RtvCheckHelper.MULTI_MODE,
                new RtvCheckHelper.RtvAppearance(Color.WHITE, getResources().getColor(R.color.color_fc286b), getResources().getColor(R.color.color_fc286b)),
                new RtvCheckHelper.RtvAppearance(Color.WHITE, getResources().getColor(R.color.color_7a7a7a), getResources().getColor(R.color.color_7a7a7a)));

    }

    @Override
    public void initData() {
        addView("运动健身");
        addView("女装");
        addView("户外");
        addView("男装");
        addView("美妆");
        addView("书籍");
    }

    @Override
    public void initListener() {

    }

    private void addView(String text) {
        RoundTextView rtv = new RoundTextView(mActivity);
        rtv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
        rtv.setBorderWidth(ConvertUtils.dp2px(1F));
        rtv.setCornerRadius(ConvertUtils.dp2px(14));
        int paddingLeft = ConvertUtils.dp2px(14);
        int paddingTop = ConvertUtils.dp2px(3);
        rtv.setPadding(paddingLeft, paddingTop, paddingLeft, paddingTop);
        rtv.setOnClickListener(this);
        rtv.setText(text);
        mTipsFl.addView(rtv);
        mRtvCheckHelper.addView(rtv);
    }

    @Override
    public void onClick(View v) {
        mRtvCheckHelper.onClick((RoundTextView) v);
    }

    @OnClick(R.id.back_btn)
    public void onClickBackBtn() {
        finish();
    }

    @OnClick(R.id.submit_btn)
    public void onClickSubmitBtn() {
        if (mRtvCheckHelper.getCheckedViews().isEmpty()) {
            showToast("请至少选择一个标签");
            return;
        }

        List<String> checkedTexts = mRtvCheckHelper.getCheckedTexts();
        StringBuilder userInterest = new StringBuilder();
        for (int i = 0; i < checkedTexts.size() - 1; i++) {
            userInterest.append(checkedTexts.get(i)).append(",");
        }
        userInterest.append(checkedTexts.get(checkedTexts.size() - 1));
        OkGo.<SimpleResponse>post(UrlConstants.postInterest)
                .params("user_info_id", SharedPreDataHelper.getUserId())
                .params("user_interest", userInterest.toString())
                .execute(new GsonCallback<SimpleResponse>(SimpleResponse.class) {
                    @Override
                    public void onSuccess(Response<SimpleResponse> response) {
                        if (response.body().isSuccess()) {
                            showToast("提交成功");
                            finish();
                        } else {
                            showToast("提交失败");
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
