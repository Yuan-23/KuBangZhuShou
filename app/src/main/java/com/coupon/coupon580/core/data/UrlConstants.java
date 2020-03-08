package com.coupon.coupon580.core.data;

/**
 * @author NoOne 2019.01.13
 */
public interface UrlConstants {

    //String BASE_URL = "https://www.coupon580.com/";
    String BASE_URL = "http://39.106.29.108:3030/";

    String getCommodity = BASE_URL + "user/home";

    String register = BASE_URL + "user/doRegister";

    String login = BASE_URL + "user/doLogin";

    String doCollect = BASE_URL + "user/U_Collect_Cou";

    String getCollect = BASE_URL + "user/User_Check_Coupon";

    String deleteCollect = BASE_URL + "user/Delete_coupon";

    String getDetail = BASE_URL + "user/commodit-detail/";

    String postInterest = BASE_URL + "user/User_Interest";

    String getOtherCommodity = BASE_URL + "user/commodit-detail-o";

    String updatePassword = BASE_URL + "user/UserChangePwd";

    String getUserInfo = BASE_URL + "user/User_Check_Userinfo";

    String updateUserInfo = BASE_URL + "user/UserChangeInfo";

    String searchByDefault = BASE_URL + "user/User_Search/1";

    String searchByPrice = BASE_URL + "user/Search_order_price";

    String searchBySoldNum = BASE_URL + "user/Search_order_num";

    String logout = BASE_URL + "user/logout";

    String hotPage = BASE_URL + "user/index/3";

    String userSendInfo = BASE_URL + "user/User_Send_Info";

    String getUnder = BASE_URL + "user/SendInfoing";

    String getAlready = BASE_URL + "user/SendInfo";

    String sendCode = BASE_URL + "user/send_code";

    String forgetpsw = BASE_URL + "user/UserForgetPwd";


    int TYPE_SPORTS = 1;

    int TYPE_EDU = 2;

    int TYPE_MAKEUP = 3;

    int TYPE_AGU = 4;

    int TYPE_OTHER = 5;

    String getCommodityByType = BASE_URL + "user/index/";

    String getCommodityByTypeOrderByPrice = BASE_URL + "user/order_price_coupon/";

    String getCommodityByTypeOrderByNum = BASE_URL + "user/order_num_coupon/";


}
