package com.coupon.coupon580.core.model;

import java.util.List;

/**
 * @description:
 * @author: NoOne
 * @date: 2019-05-12 14:05
 */
public class NewActBean {

    private String coverImage;

    private int type;

    private List<NewCommodityBean> data;

    private CommodityBean bean;

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public List<NewCommodityBean> getData() {
        return data;
    }

    public void setData(List<NewCommodityBean> data) {
        this.data = data;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setBean(CommodityBean bean) {
        this.bean = bean;
    }

    public CommodityBean getBean() {
        return bean;
    }
}
