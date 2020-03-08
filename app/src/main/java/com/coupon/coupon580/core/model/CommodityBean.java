package com.coupon.coupon580.core.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * 商品
 *
 * @author NoOne 2019.04.06
 */
public class CommodityBean implements Serializable, Parcelable {

    @SerializedName("coupon_info_id")
    private String id;

    private String name;

    @SerializedName("coupon_intro")
    private String intro;

    private String thumbnail;

    @SerializedName("image")
    private String bannerImgs;

    @Expose(serialize = false, deserialize = false)
    private String detailImgs;

    //    @SerializedName("start_period")
    private long startTime;

    //    @SerializedName("end_period")
    private long endTime;

    //    @SerializedName("price")
    private double priceBefore;

    private double priceAfter;

    //    @SerializedName("price_ticket")
    private int couponPrice;

    //    @SerializedName("coupon_remain_num")
    private int soldOutNum;

    @SerializedName("coupon_activity_id")
    private String couponActivityId;

    @SerializedName("product_category_id")
    private String productCategoryId;

    private String link;

    @SerializedName("coupon_status_id")
    private String couponStatusId;

    public CommodityBean() {
    }

    protected CommodityBean(Parcel in) {
        id = in.readString();
        name = in.readString();
        intro = in.readString();
        thumbnail = in.readString();
        bannerImgs = in.readString();
        detailImgs = in.readString();
        startTime = in.readLong();
        endTime = in.readLong();
        priceBefore = in.readDouble();
        priceAfter = in.readDouble();
        couponPrice = in.readInt();
        soldOutNum = in.readInt();
        couponActivityId = in.readString();
        productCategoryId = in.readString();
        link = in.readString();
        couponStatusId = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(intro);
        dest.writeString(thumbnail);
        dest.writeString(bannerImgs);
        dest.writeString(detailImgs);
        dest.writeLong(startTime);
        dest.writeLong(endTime);
        dest.writeDouble(priceBefore);
        dest.writeDouble(priceAfter);
        dest.writeInt(couponPrice);
        dest.writeInt(soldOutNum);
        dest.writeString(couponActivityId);
        dest.writeString(productCategoryId);
        dest.writeString(link);
        dest.writeString(couponStatusId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CommodityBean> CREATOR = new Creator<CommodityBean>() {
        @Override
        public CommodityBean createFromParcel(Parcel in) {
            return new CommodityBean(in);
        }

        @Override
        public CommodityBean[] newArray(int size) {
            return new CommodityBean[size];
        }
    };

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

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getBannerImgs() {
        return bannerImgs;
    }

    public void setBannerImgs(String bannerImgs) {
        this.bannerImgs = bannerImgs;
    }

    public String getDetailImgs() {
        return detailImgs;
    }

    public void setDetailImgs(String detailImgs) {
        this.detailImgs = detailImgs;
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

    public double getPriceBefore() {
        return priceBefore;
    }

    public void setPriceBefore(double priceBefore) {
        this.priceBefore = priceBefore;
    }

    public double getPriceAfter() {
        return priceAfter;
    }

    public void setPriceAfter(double priceAfter) {
        this.priceAfter = priceAfter;
    }

    public int getCouponPrice() {
        return couponPrice;
    }

    public void setCouponPrice(int couponPrice) {
        this.couponPrice = couponPrice;
    }

    public int getSoldOutNum() {
        return soldOutNum;
    }

    public void setSoldOutNum(int soldOutNum) {
        this.soldOutNum = soldOutNum;
    }

    public String getCouponActivityId() {
        return couponActivityId;
    }

    public void setCouponActivityId(String couponActivityId) {
        this.couponActivityId = couponActivityId;
    }

    public String getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(String productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCouponStatusId() {
        return couponStatusId;
    }

    public void setCouponStatusId(String couponStatusId) {
        this.couponStatusId = couponStatusId;
    }
}
