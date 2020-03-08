package com.coupon.coupon580.mine.model;

import com.google.gson.annotations.SerializedName;

/**
 * 用户资料
 *
 * @author NoOne 2019.04.06
 */
public class UserInfoBean {


    @SerializedName("user_info_id")
    private String userInfoId;
    @SerializedName("name")
    private String name;
    @SerializedName("user_phone")
    private String userPhone;
    @SerializedName("user_sex_id")
    private String userSexId;
    @SerializedName("user_grade_id")
    private String userGradeId;
    @SerializedName("balance")
    private String balance;
    @SerializedName("rec_code")
    private String recCode;
    @SerializedName("user_avatar")
    private String userAvatar;
    @SerializedName("login_password")
    private String loginPassword;
    @SerializedName("interest")
    private String interest;
    @SerializedName("score")
    private String userScore;
    @SerializedName("level")
    private String userLevel;

    public String getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(String userInfoId) {
        this.userInfoId = userInfoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserSexId() {
        return userSexId;
    }

    public void setUserSexId(String userSexId) {
        this.userSexId = userSexId;
    }

    public String getUserGradeId() {
        return userGradeId;
    }

    public void setUserGradeId(String userGradeId) {
        this.userGradeId = userGradeId;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getRecCode() {
        return recCode;
    }

    public void setRecCode(String recCode) {
        this.recCode = recCode;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getUserLevel() {return userLevel;}

    public String getUserScore() {return userScore;}

    public void setUserScore(String score){
        this.userScore = score;
    }
    public void setUserLevel(String level){
        this.userLevel = level;
    }

}
