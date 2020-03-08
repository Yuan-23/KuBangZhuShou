package com.coupon.coupon580.core.model;

import java.util.List;

/**
 * 活动
 *
 * @author NoOne 2019.03.04
 */
public class ActivityBean {

    private String id;
    private String name;
    private String url;
    private long startTime;
    private long endTime;
    private List<CommodityBean> recommendCommodity;

    public List<CommodityBean> getRecommendCommodity() {
        return recommendCommodity;
    }

    public void setRecommendCommodity(List<CommodityBean> recommendCommodity) {
        this.recommendCommodity = recommendCommodity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
}
