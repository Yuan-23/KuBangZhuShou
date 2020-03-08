package com.coupon.coupon580.home.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.coupon.coupon580.R;
import com.coupon.coupon580.core.image.GlideHelper;
import com.coupon.coupon580.core.model.NewCommodityBean;

/**
 * @author NoOne 2019.03.10
 */
public class SnapUpAdapter extends BaseQuickAdapter<NewCommodityBean, BaseViewHolder> {
    public SnapUpAdapter() {
        super(R.layout.item_snap_up);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewCommodityBean item) {
        GlideHelper.loadImage(mContext, item.getImage(), (ImageView) helper.getView(R.id.thumbnail_iv));
        helper.setText(R.id.name_tv, item.getName());
        helper.setText(R.id.snap_up_state_tv, "已抢" + item.getCouponRemainNum() + "件");
        helper.setText(R.id.intro_tv, item.getCouponIntro());
        helper.setText(R.id.price_after_tv, item.getPrice());
        helper.setText(R.id.price_before_tv, item.getPrice());

    }
}
