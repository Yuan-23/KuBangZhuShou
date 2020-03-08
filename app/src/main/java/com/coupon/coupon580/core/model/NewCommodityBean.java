package com.coupon.coupon580.core.model;

import com.google.gson.annotations.SerializedName;

/**
 * @description:
 * @author: NoOne
 * @date: 2019-05-10 20:15
 */
public class NewCommodityBean {

    @SerializedName(value="coupon_info_id",alternate = {"id"})
    private String couponInfoId;

    @SerializedName("coupon_activity_id")
    private String couponActivityId;

    @SerializedName(value="name",alternate = {"title"})
    private String name;

    @SerializedName(value = "price_ticket",alternate = {"label"})
    private String priceTicket;

    @SerializedName(value = "price",alternate = {"kind"})
    private String price;

    @SerializedName(value = "city")
    private String city;

    @SerializedName(value = "image",alternate = {"img_url"})
    private String images;

    @SerializedName("start_period")
    private String startPeriod;

    @SerializedName("end_period")
    private String endPeriod;

    @SerializedName(value="coupon_intro",alternate = {"intro"})
    private String couponIntro;

    @SerializedName("product_category_id")
    private String productCategoryId;

    @SerializedName("coupon_num")
    private String couponNum;

    @SerializedName("coupon_remain_num")
    private String couponRemainNum;

    @SerializedName("coupon_status_id")
    private String couponStatusId;

    @SerializedName(value = "link",alternate = {"location"})
    private String link;

    @SerializedName("status")
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCouponInfoId() {
        return couponInfoId;
    }

    public void setCouponInfoId(String couponInfoId) {
        this.couponInfoId = couponInfoId;
    }

    public String getCouponActivityId() {
        return couponActivityId;
    }

    public void setCouponActivityId(String couponActivityId) {
        this.couponActivityId = couponActivityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPriceTicket() {
        return priceTicket;
    }

    public void setPriceTicket(String priceTicket) {
        this.priceTicket = priceTicket;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return images ;
    }

    public void setImage(String image) {
        this.images = image;
    }

    public String getStartPeriod() {
        return startPeriod;
    }

    public void setStartPeriod(String startPeriod) {
        this.startPeriod = startPeriod;
    }

    public String getEndPeriod() {
        return endPeriod;
    }

    public void setEndPeriod(String endPeriod) {
        this.endPeriod = endPeriod;
    }

    public String getCouponIntro() {
        return couponIntro;
    }

    public void setCouponIntro(String couponIntro) {
        this.couponIntro = couponIntro;
    }

    public String getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(String productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getCouponNum() {
        return couponNum;
    }

    public void setCouponNum(String couponNum) {
        this.couponNum = couponNum;
    }

    public String getCouponRemainNum() {
        return couponRemainNum;
    }

    public void setCouponRemainNum(String couponRemainNum) {
        this.couponRemainNum = couponRemainNum;
    }

    public String getCouponStatusId() {
        return couponStatusId;
    }

    public void setCouponStatusId(String couponStatusId) {
        this.couponStatusId = couponStatusId;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    /*public String getImages() {
        return image;
    }

    public void setImages(String image) {
        this.image = image;
    }*/

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }
}
