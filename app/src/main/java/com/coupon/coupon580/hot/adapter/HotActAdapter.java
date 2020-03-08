package com.coupon.coupon580.hot.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.coupon.coupon580.R;
import com.coupon.coupon580.core.image.GlideHelper;
import com.coupon.coupon580.core.model.NewActBean;
import com.coupon.coupon580.core.model.NewCommodityBean;

import java.util.List;

/**
 * @author NoOne 2019.03.04
 */
public class HotActAdapter extends BaseQuickAdapter<NewActBean, BaseViewHolder> {
    public HotActAdapter() {
        super(R.layout.item_hot_act);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewActBean item) {
        //GlideHelper.loadImage(mContext, item.getCoverImage(), (ImageView) helper.getView(R.id.cover_iv));

        View commodity1 = helper.getView(R.id.commodity_item1);
        View commodity2 = helper.getView(R.id.commodity_item2);
        View commodity3 = helper.getView(R.id.commodity_item3);

        helper.addOnClickListener(R.id.commodity_item1);
        helper.addOnClickListener(R.id.commodity_item2);
        helper.addOnClickListener(R.id.commodity_item3);

        commodity1.setVisibility(View.INVISIBLE);
        commodity2.setVisibility(View.INVISIBLE);
        commodity3.setVisibility(View.INVISIBLE);

        View[] views = {commodity1, commodity2, commodity3};
        List<NewCommodityBean> recommendCommodity = item.getData();


        if (recommendCommodity != null && recommendCommodity.size() > 0) {
            int size = Math.min(recommendCommodity.size(), 3);
            for (int i = 0; i < size; i++) {
                views[i].setVisibility(View.VISIBLE);
                bindItemView(views[i], recommendCommodity.get(i));
                //System.out.println("+++++++++++++"+recommendCommodity.get(i).getCouponInfoId()+"++++++++++++");
            }

        }
    }

    private void bindItemView(View view, NewCommodityBean item) {
        GlideHelper.loadImage(mContext, item.getImage(), (ImageView) view.findViewById(R.id.thumbnail_iv));
        ((TextView) view.findViewById(R.id.name_tv)).setText(item.getName());
        ((TextView) view.findViewById(R.id.price_after_tv)).setText(item.getPrice());
        String IntroId = item.getCouponInfoId();
    }
}
