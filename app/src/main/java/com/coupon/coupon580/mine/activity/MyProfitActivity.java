package com.coupon.coupon580.mine.activity;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.coupon.coupon580.R;
import com.coupon.coupon580.core.base.BaseActivity;
import com.coupon.coupon580.core.data.SharedPreDataHelper;
import com.coupon.coupon580.core.data.UrlConstants;
import com.coupon.coupon580.core.helper.LoginHelper;
import com.coupon.coupon580.core.network.okgo.GsonCallback;
import com.coupon.coupon580.core.utils.RandomStrUtils;
import com.coupon.coupon580.core.widget.MyToolbar;
import com.coupon.coupon580.mine.adapter.ProfitAdapter;
import com.coupon.coupon580.mine.model.UserInfoBean;

import java.util.List;

import butterknife.BindView;

public class MyProfitActivity extends BaseActivity {


    @BindView(R.id.tool_bar)
    MyToolbar mToolBar;
    @BindView(R.id.total_profit_tv)
    TextView mTotalProfitTv;
    @BindView(R.id.profit_rv)
    RecyclerView mProfitRv;
    @BindView(R.id.user_Score_tv)
    TextView mUserScoreTv;


    private ProfitAdapter mProfitAdapter;
    private UserInfoBean mUserInfoBean;

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_profit;
    }

    @Override
    public void initView() {
        mToolBar.setPadding(0, getStatusBarHeight(), 0, 0);
        if (LoginHelper.isLogin(mActivity)) {
            loadUserInfo(true);
        } else {
            showToast("请登录后查看");
        }
        mProfitAdapter = new ProfitAdapter();
        mUserInfoBean = new UserInfoBean();
        mProfitAdapter.bindToRecyclerView(mProfitRv);
        //mProfitAdapter.setEmptyView(R.layout.empty_view);
        mProfitAdapter.addFooterView(View.inflate(mActivity, R.layout.rv_footer_layout, null));
    }

    @Override
    public void initData() {
        showToast("用户积分："+mUserInfoBean.getUserScore());
    }


    @Override
    public void initListener() {

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
        mUserScoreTv.setText(bean.getUserScore());
    }


}
