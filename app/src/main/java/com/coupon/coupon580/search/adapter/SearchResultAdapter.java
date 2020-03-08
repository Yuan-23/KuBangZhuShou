package com.coupon.coupon580.search.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.coupon.coupon580.R;
import com.coupon.coupon580.core.image.GlideHelper;
import com.coupon.coupon580.core.model.NewCommodityBean;

/**
 * @author NoOne 2019.03.09
 */
public class SearchResultAdapter extends BaseQuickAdapter<NewCommodityBean, BaseViewHolder> {
    public SearchResultAdapter() {
        super(R.layout.item_search_result);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewCommodityBean item) {
        GlideHelper.loadImage(mContext, item.getImage(), (ImageView) helper.getView(R.id.thumbnail_iv));
        helper.setText(R.id.name_tv, item.getName());
        helper.setText(R.id.sale_tv, "已售出" + item.getCouponRemainNum() + "件");
        helper.setText(R.id.intro_tv, item.getCouponIntro());
        helper.setText(R.id.discount_coupon_tv, item.getPriceTicket() + "元");
        helper.setText(R.id.price_after_tv, item.getPrice());
        helper.setText(R.id.price_before_tv, item.getPrice());
    }
}
