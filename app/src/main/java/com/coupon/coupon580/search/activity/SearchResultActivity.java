package com.coupon.coupon580.search.activity;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;

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
import com.coupon.coupon580.hot.activity.CommodityDetailActivity;
import com.coupon.coupon580.search.adapter.SearchResultAdapter;

import butterknife.BindView;
import butterknife.OnClick;

public class SearchResultActivity extends BaseActivity {

    public static final String BUNDLE_KEY_WORD = "BUNDLE_KEY_WORD";
    public static final String BUNDLE_TYPE = "BUNDLE_TYPE";

    @BindView(R.id.tool_bar)
    MyToolbar mToolBar;
    @BindView(R.id.search_result_rv)
    RecyclerView mSearchResultRv;
    @BindView(R.id.order_by_default_indicator)
    View mOrderByDefaultIndicator;
    @BindView(R.id.order_by_price_indicator)
    View mOrderByPriceIndicator;
    @BindView(R.id.order_by_sold_num_indicator)
    View mOrderBySoldNumIndicator;

    private int mOrderMode = ORDER_BY_DEFAULT;

    public static final int ORDER_BY_DEFAULT = 1;
    public static final int ORDER_BY_PRICE = 2;
    public static final int ORDER_BY_SOLD_NUM = 3;


    private SearchResultAdapter mResultAdapter;
    private String mKeyWord;
    private int mType;

    @Override
    public int getLayoutId() {
        return R.layout.activity_search_result;
    }

    @Override
    public void initView() {
        setStatusBarTextBlack(false);
        mToolBar.setBackgroundColor(Color.TRANSPARENT);

        mResultAdapter = new SearchResultAdapter();
        mResultAdapter.bindToRecyclerView(mSearchResultRv);
        mResultAdapter.addFooterView(View.inflate(mActivity, R.layout.rv_footer_layout, null));
        mResultAdapter.setEmptyView(R.layout.empty_view);
    }

    @Override
    public void initData() {
        mKeyWord = getIntent().getStringExtra(BUNDLE_KEY_WORD);
        mType = Integer.parseInt(getIntent().getStringExtra(BUNDLE_TYPE));
        //System.out.println("++++++++++++++++"+mKeyWord+"++++++++++++"+mType);
        updateOrderView();
        loadData();
    }

    private void loadData() {
        OkGo.<PageCommodityBean>post(getSearchUrl())
                .params("key_word", mKeyWord)
                .params("type",mType)
                .execute(new GsonCallback<PageCommodityBean>(PageCommodityBean.class) {
                    @Override
                    public void onSuccess(Response<PageCommodityBean> response) {
                        mResultAdapter.setNewData(response.body().result);
                      /*  if (mResultAdapter.getData().size() == 0) {
                            showToast("搜索返回结果为空");
                        }*/
                    }

                    @Override
                    public void onError(Response<PageCommodityBean> response) {
                        super.onError(response);
                        showToast(getResources().getString(R.string.network_error));
                    }
                });
    }

    private String getSearchUrl() {
        if (mOrderMode == ORDER_BY_SOLD_NUM) {
            return UrlConstants.searchBySoldNum;
        } else if (mOrderMode == ORDER_BY_PRICE) {
            return UrlConstants.searchByPrice;
        } else {
            return UrlConstants.searchByDefault;
        }
    }

    @Override
    public void initListener() {
        mResultAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                NewCommodityBean item = mResultAdapter.getItem(position);
                if (item != null) {
                    CommodityDetailActivity.startActivity(mActivity, item.getCouponInfoId());
                }
            }
        });
    }

    @OnClick({R.id.robot_iv, R.id.robot_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.robot_iv:
            case R.id.robot_tv:
                startActivity(RobotTalkActivity.class);
                break;
        }
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
            updateOrderView();
            loadData();
        }
    }
}
