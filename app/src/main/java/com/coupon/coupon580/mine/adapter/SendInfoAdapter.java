package com.coupon.coupon580.mine.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.coupon.coupon580.R;
import com.coupon.coupon580.core.model.NewCommodityBean;

public class SendInfoAdapter extends BaseQuickAdapter<NewCommodityBean, BaseViewHolder> {

    public SendInfoAdapter() {
        super(R.layout.item_discount_coupon);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewCommodityBean item) {
        helper.setText(R.id.store_name_tv,item.getName());
        helper.setText(R.id.coupon_price_tv,item.getName());
        helper.setText(R.id.intro_tv,item.getPriceTicket());
        helper.setText(R.id.time_tv,item.getPriceTicket());
        if (item.getStatus().equals("1")){
            helper.setText(R.id.use_now_tv,"已发布");
        }
        if (item.getStatus().equals("2")){
            helper.setText(R.id.use_now_tv,"审核中");
        }
    }
}
