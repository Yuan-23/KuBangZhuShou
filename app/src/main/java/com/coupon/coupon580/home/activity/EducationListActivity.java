package com.coupon.coupon580.home.activity;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.coupon.coupon580.R;
import com.coupon.coupon580.core.base.BaseActivity;
import com.coupon.coupon580.core.data.UrlConstants;
import com.coupon.coupon580.core.model.NewCommodityBean;
import com.coupon.coupon580.core.network.okgo.GsonCallback;
import com.coupon.coupon580.core.widget.MyToolbar;
import com.coupon.coupon580.home.model.PageCommodityBean;
import com.coupon.coupon580.hot.adapter.CommodityAdapter;
import com.coupon.coupon580.hot.activity.CommodityDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class EducationListActivity extends BaseActivity {


    @BindView(R.id.commodity_rv)
    RecyclerView mCommodityRv;
    @BindView(R.id.order_by_default_indicator)
    ImageView mOrderByDefaultIndicator;
    @BindView(R.id.order_by_price_indicator)
    ImageView mOrderByPriceIndicator;
    @BindView(R.id.order_by_sold_num_indicator)
    ImageView mOrderBySoldNumIndicator;
    @BindView(R.id.tool_bar)
    MyToolbar mToolBar;

    private CommodityAdapter mCommodityAdapter;


    public static final String BUNDLE_TYPE = "BUNDLE_TYPE";

    private int mType = 1;

    private int mCurrentPage = 1;

    private int mOrderMode = ORDER_BY_DEFAULT;

    public static final int ORDER_BY_DEFAULT = 1;
    public static final int ORDER_BY_PRICE = 2;
    public static final int ORDER_BY_SOLD_NUM = 3;


    @Override
    public int getLayoutId() {
        return R.layout.activity_education_list;
    }

    @Override
    public void initView() {
        mToolBar.setPadding(0, getStatusBarHeight(), 0, 0);
        mCommodityAdapter = new CommodityAdapter();
        mCommodityAdapter.bindToRecyclerView(mCommodityRv);
        mCommodityAdapter.setEmptyView(R.layout.empty_view);

        mCommodityAdapter.addFooterView(View.inflate(mActivity, R.layout.rv_footer_layout, null));
        mCommodityAdapter.setHeaderAndEmpty(true);
    }

    @Override
    public void initData() {
        mType = getIntent().getIntExtra(BUNDLE_TYPE, 1);
        loadData();
    }

    private String getUrl() {
        /*if (mOrderMode == ORDER_BY_SOLD_NUM) {
            return UrlConstants.getCommodityByType;
        } else if (mOrderMode == ORDER_BY_PRICE) {
            return UrlConstants.getCommodityByType;
        } else {*/
            return UrlConstants.getCommodityByType;
        //}
    }

    private void loadData() {
        OkGo.<PageCommodityBean>get(getUrl() + mType + "/" + mCurrentPage)
                .execute(new GsonCallback<PageCommodityBean>(PageCommodityBean.class) {
                    @Override
                    public void onSuccess(Response<PageCommodityBean> response) {
                        List<NewCommodityBean> result = response.body().result;
                        List<NewCommodityBean> data = new ArrayList<>();
                        for (NewCommodityBean newCommodityBean : result) {
                            if (newCommodityBean != null) {
                                data.add(newCommodityBean);
                            }
                        }
                        mCommodityAdapter.addData(data);
                        mCurrentPage++;
                        if (mCurrentPage == response.body().pageSum) {
                            mCommodityAdapter.loadMoreEnd(true);
                        } else {
                            mCommodityAdapter.loadMoreComplete();
                        }
                    }

                    @Override
                    public void onError(Response<PageCommodityBean> response) {
                        super.onError(response);
                        mCommodityAdapter.loadMoreFail();
                        showToast(getResources().getString(R.string.network_error));
                    }
                });
    }

    @Override
    public void initListener() {
        mCommodityAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                CommodityDetailActivity.startActivity(mActivity, mCommodityAdapter.getData().get(position).getCouponInfoId());
            }
        });

        mCommodityAdapter.setEnableLoadMore(true);
        mCommodityAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadData();
            }
        });
    }

    private void updateOrderView() {
        mOrderByDefaultIndicator.setVisibility(View.INVISIBLE);
        mOrderByPriceIndicator.setVisibility(View.INVISIBLE);
        mOrderBySoldNumIndicator.setVisibility(View.INVISIBLE);
        if (mOrderMode == ORDER_BY_PRICE) {
            mOrderByPriceIndicator.setVisibility(View.VISIBLE);
        } else if (mOrderMode == ORDER_BY_SOLD_NUM) {
            mOrderBySoldNumIndicator.setVisibility(View.VISIBLE);
        } else {
            mOrderByDefaultIndicator.setVisibility(View.VISIBLE);
        }
    }

    @OnClick({R.id.order_by_default_btn, R.id.order_by_price_btn, R.id.order_by_sold_num_btn})
    public void onClickOrderBtn(View view) {
        int preMode = mOrderMode;
        switch (view.getId()) {
            case R.id.order_by_default_btn:
                mOrderMode = ORDER_BY_DEFAULT;
                break;
            case R.id.order_by_price_btn:
                mOrderMode = ORDER_BY_PRICE;
                break;
            case R.id.order_by_sold_num_btn:
                mOrderMode = ORDER_BY_SOLD_NUM;
                break;
            default:
                mOrderMode = ORDER_BY_DEFAULT;
                break;
        }
        if (mOrderMode != preMode) {
            mCurrentPage = 1;
            mCommodityAdapter.setNewData(null);
            updateOrderView();
            loadData();
        }
    }
}
