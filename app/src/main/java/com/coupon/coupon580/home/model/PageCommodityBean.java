package com.coupon.coupon580.home.model;

import com.google.gson.annotations.SerializedName;
import com.coupon.coupon580.core.model.NewCommodityBean;

import java.util.List;

/**
 * @description:
 * @author: NoOne
 * @date: 2019-05-11 16:35
 */
public class PageCommodityBean {

    public List<NewCommodityBean> result;

    @SerializedName("page_sum")
    public int pageSum;

    public String currentpage;
}
