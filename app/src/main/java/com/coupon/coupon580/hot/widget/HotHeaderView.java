package com.coupon.coupon580.hot.widget;

import android.content.Context;
import android.view.View;

import com.coupon.coupon580.R;
import com.coupon.coupon580.hot.activity.CommodityListActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.blankj.utilcode.util.ActivityUtils.startActivity;

/**
 * @author NoOne 2019.03.04
 */
public class HotHeaderView {

    private View mView;
    private Context mContext;


    public HotHeaderView(Context context) {
        mContext = context;
        mView = View.inflate(mContext, R.layout.hot_header_layout, null);
        ButterKnife.bind(this, mView);
    }


    public View getView() {
        return mView;
    }

    @OnClick({R.id.hot_more_tv})
    public void onCLickMoreBtn() {
        startActivity(CommodityListActivity.class);
    }

    }


