package com.coupon.coupon580.home.model;

import com.coupon.coupon580.core.model.NewCommodityBean;

import java.util.List;

/**
 * @description:
 * @author: NoOne
 * @date: 2019-04-14 20:28
 */
public class CategoryBean {

    private String categoryName;

    public int type;

    private List<NewCommodityBean> recommendCommodities;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<NewCommodityBean> getRecommendCommodities() {
        return recommendCommodities;
    }

    public void setRecommendCommodities(List<NewCommodityBean> recommendCommodities) {
        this.recommendCommodities = recommendCommodities;
    }

}
