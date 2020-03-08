package com.coupon.coupon580.mine;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.coupon.coupon580.R;
import com.coupon.coupon580.core.base.BasePagerFragment;
import com.coupon.coupon580.core.data.SharedPreDataHelper;
import com.coupon.coupon580.core.data.UrlConstants;
import com.coupon.coupon580.core.event.LoginEvent;
import com.coupon.coupon580.core.event.LogoutEvent;
import com.coupon.coupon580.core.event.UserInfoUpdateEvent;
import com.coupon.coupon580.core.helper.LoginHelper;
import com.coupon.coupon580.core.image.GlideHelper;
import com.coupon.coupon580.core.model.SimpleResponse;
import com.coupon.coupon580.core.network.okgo.GsonCallback;
import com.coupon.coupon580.core.utils.RandomStrUtils;
import com.coupon.coupon580.mine.activity.AssociateAccountActivity;
import com.coupon.coupon580.mine.activity.EditInfoActivity;
import com.coupon.coupon580.mine.activity.EditPwdActivity;
import com.coupon.coupon580.mine.activity.LoginActivity;
import com.coupon.coupon580.mine.activity.MyCouponActivity;
import com.coupon.coupon580.mine.activity.MyProfitActivity;
import com.coupon.coupon580.mine.activity.PactActivity;
import com.coupon.coupon580.mine.activity.SendInfoActivity;
import com.coupon.coupon580.mine.model.UserInfoBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author NoOne 2019.03.04
 */
public class MineFragment extends BasePagerFragment {

    @BindView(R.id.head_img_iv)
    CircleImageView mHeadImgIv;
    @BindView(R.id.user_name_tv)
    TextView mUserNameTv;
    @BindView(R.id.level_iv)
    ImageView mLevelIv;
    @BindView(R.id.level_tv)
    TextView mLevelTv;
    @BindView(R.id.invite_code_tv)
    TextView mInviteCodeTv;
    @BindView(R.id.copy_recommend_code_ll)
    LinearLayout mCopyRecommendCodeLl;
    @BindView(R.id.associate_account_ll)
    LinearLayout mRelevanceAccountLl;
    @BindView(R.id.edit_pwd_ll)
    LinearLayout mEditPwdLl;
    @BindView(R.id.edit_info_ll)
    LinearLayout mEditInfoLl;
    @BindView(R.id.logout_ll)
    LinearLayout mLogoutLl;
    @BindView(R.id.discount_coupon_ll)
    LinearLayout mDiscountCouponLl;
    @BindView(R.id.recommend_profit_ll)
    LinearLayout mRecommendProfitLl;
    @BindView(R.id.login_btn)
    TextView mLoginBtn;
    @BindView(R.id.rec_code_tv)
    TextView mRecCodeTv;

    @BindView(R.id.share_ll)
    LinearLayout mShareLl;
    @BindView(R.id.pact_ll)
    LinearLayout mPactLl;

    private UserInfoBean mUserInfoBean;

    public static MineFragment newInstance() {

        Bundle args = new Bundle();

        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        setStatusBarTextBlack(false);
        mRelevanceAccountLl.setVisibility(View.GONE);
        if (LoginHelper.isLogin(mActivity)) {
            showLoginView();
            loadUserInfo(true);
        } else {
            showLogoutView();
        }
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) mHeadImgIv.getLayoutParams();
        layoutParams.topMargin += getStatusBarHeight();
        mHeadImgIv.setLayoutParams(layoutParams);
    }

    @Override
    protected void initData() {

    }


    @Override
    protected void initListener() {
        EventBus.getDefault().register(this);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @OnClick({R.id.copy_recommend_code_ll, R.id.associate_account_ll, R.id.edit_pwd_ll, R.id.edit_info_ll, R.id.discount_coupon_ll, R.id.recommend_profit_ll, R.id.share_ll, R.id.pact_ll})
    public void onClick(View view) {
        if (!LoginHelper.isLogin(mActivity)) {
            showToast("请先登录/注册");
            return;
        }
        switch (view.getId()) {
            case R.id.copy_recommend_code_ll:
                //获取剪贴板管理器：
                ClipboardManager cm = (ClipboardManager) mActivity.getSystemService(Context.CLIPBOARD_SERVICE);
                // 创建普通字符型ClipData
                ClipData mClipData = ClipData.newPlainText("Label", mRecCodeTv.getText());
                // 将ClipData内容放到系统剪贴板里。
                cm.setPrimaryClip(mClipData);
                showToast("已复制到剪贴板");
                break;
            case R.id.associate_account_ll:
                //startActivity(AssociateAccountActivity.class);
                break;
            case R.id.edit_pwd_ll:
                startActivity(EditPwdActivity.class);
                break;
            case R.id.edit_info_ll:
                startActivity(EditInfoActivity.class);
                break;
            case R.id.discount_coupon_ll:
                startActivity(MyCouponActivity.class);
                break;
            case R.id.recommend_profit_ll:
                startActivity(MyProfitActivity.class);
                break;

            case R.id.share_ll:
                startActivity(SendInfoActivity.class);
                break;
            case R.id.pact_ll:
                startActivity(PactActivity.class);
                break;

        }
    }

    @OnClick({R.id.head_img_iv, R.id.user_name_tv})
    public void UserClick(View view){
        startActivity(EditInfoActivity.class);
    }


    @OnClick(R.id.logout_ll)
    public void onClickLogout() {
        OkGo.<SimpleResponse>get(UrlConstants.logout)
                .execute(new GsonCallback<SimpleResponse>(SimpleResponse.class) {
                    @Override
                    public void onSuccess(Response<SimpleResponse> response) {
                        if (response.body().isSuccess()) {
                            LoginHelper.logout();
                            showToast("注销成功");
                            showLogoutView();
                        } else {
                            showToast("注销失败，请重试");
                        }
                    }

                    @Override
                    public void onError(Response<SimpleResponse> response) {
                        super.onError(response);
                        showToast(getResources().getString(R.string.network_error));
                    }
                });

    }

    @OnClick(R.id.login_btn)
    public void onClickLogin() {
        startActivity(LoginActivity.class);
    }

    // 展示登录状态下的View
    public void showLoginView() {
        mLogoutLl.setVisibility(View.VISIBLE);
        mLoginBtn.setVisibility(View.GONE);
        mHeadImgIv.setVisibility(View.VISIBLE);
        mUserNameTv.setVisibility(View.VISIBLE);
        mLevelIv.setVisibility(View.VISIBLE);
        mLevelTv.setVisibility(View.VISIBLE);
        mInviteCodeTv.setVisibility(View.VISIBLE);

        mShareLl.setVisibility(View.VISIBLE);
        mPactLl.setVisibility(View.VISIBLE);
    }


    // 展示未登录状态下的View
    public void showLogoutView() {
        mLogoutLl.setVisibility(View.INVISIBLE);
        mLoginBtn.setVisibility(View.VISIBLE);
        mHeadImgIv.setVisibility(View.INVISIBLE);
        mUserNameTv.setVisibility(View.INVISIBLE);
        mLevelIv.setVisibility(View.INVISIBLE);
        mLevelTv.setVisibility(View.INVISIBLE);
        mInviteCodeTv.setVisibility(View.INVISIBLE);
        mRecCodeTv.setText("");

        mShareLl.setVisibility(View.VISIBLE);
        mPactLl.setVisibility(View.VISIBLE);
    }

    @Subscribe
    public void onLogin(LoginEvent loginEvent) {
        showLoginView();
        loadUserInfo(true);
    }

    @Subscribe
    public void onLogout(LogoutEvent logoutEvent) {
        showLogoutView();
    }

    @Subscribe
    public void onUserInfoUpdate(UserInfoUpdateEvent event) {
        loadUserInfo(true);
    }


    private void loadUserInfo(boolean forceRefresh) {
        if (!forceRefresh) {
            mUserInfoBean = SharedPreDataHelper.getUserInfoBean();
            if (mUserInfoBean != null) {
                setUserInfoView(mUserInfoBean);
                return;
            }
        }
        OkGo.<List<UserInfoBean>>get(UrlConstants.getUserInfo)
                .execute(new GsonCallback<List<UserInfoBean>>(new TypeToken<List<UserInfoBean>>() {
                }.getType()) {
                    @Override
                    public void onSuccess(Response<List<UserInfoBean>> response) {
                        List<UserInfoBean> body = response.body();
                        if (body != null && body.size() != 0) {
                            UserInfoBean bean = body.get(0);
                            mUserInfoBean = bean;
                            if (TextUtils.isEmpty(bean.getRecCode())) {
                                bean.setRecCode(RandomStrUtils.getRandomString(8));
                            }
                            SharedPreDataHelper.setUserInfoBean(bean);
                            setUserInfoView(bean);
                        }
                    }

                    @Override
                    public void onError(Response<List<UserInfoBean>> response) {
                        super.onError(response);
                        showToast(getResources().getString(R.string.network_error));
                        mUserInfoBean = SharedPreDataHelper.getUserInfoBean();
                        if (mUserInfoBean != null) {
                            setUserInfoView(mUserInfoBean);
                        }
                    }
                });
    }

    private void setUserInfoView(UserInfoBean bean) {
        GlideHelper.loadImage(mActivity, bean.getUserAvatar(), R.mipmap.ic_launcher, mHeadImgIv);
        mUserNameTv.setText(bean.getName());
        mInviteCodeTv.setText("我的邀请码：" + bean.getRecCode());
        mRecCodeTv.setText(bean.getRecCode());
        mLevelTv.setText(bean.getUserLevel());
    }

}
