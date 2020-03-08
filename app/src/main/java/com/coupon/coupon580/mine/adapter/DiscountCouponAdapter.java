package com.coupon.coupon580.mine.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.coupon.coupon580.R;
import com.coupon.coupon580.core.model.NewCommodityBean;

/**
 * @author NoOne 2019.03.10
 */
public class DiscountCouponAdapter extends BaseQuickAdapter<NewCommodityBean, BaseViewHolder> {


    public DiscountCouponAdapter() {
        super(R.layout.item_discount_coupon);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewCommodityBean item) {
        if (item.getPriceTicket()==null){
            helper.setVisible(R.id.coupon_price_tv, false);
        }
        helper.setText(R.id.coupon_price_tv, item.getPriceTicket() + "元");
        helper.setText(R.id.intro_tv, item.getCouponIntro());
        helper.setText(R.id.store_name_tv, item.getName());
        helper.setText(R.id.time_tv, item.getStartPeriod()
                + "\n - "
                + item.getEndPeriod());
    }
}
