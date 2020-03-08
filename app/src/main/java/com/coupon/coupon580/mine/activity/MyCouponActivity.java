package com.coupon.coupon580.mine.activity;

import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.coupon.coupon580.R;
import com.coupon.coupon580.core.base.BaseActivity;
import com.coupon.coupon580.core.data.UrlConstants;
import com.coupon.coupon580.core.model.NewCommodityBean;
import com.coupon.coupon580.core.model.SimpleResponse;
import com.coupon.coupon580.core.network.okgo.GsonCallback;
import com.coupon.coupon580.core.utils.DialogUtils;
import com.coupon.coupon580.core.widget.MyToolbar;
import com.coupon.coupon580.hot.activity.CommodityDetailActivity;
import com.coupon.coupon580.mine.adapter.DiscountCouponAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MyCouponActivity extends BaseActivity {


    @BindView(R.id.tool_bar)
    MyToolbar mToolBar;
    @BindView(R.id.discount_coupon_rv)
    RecyclerView mDiscountCouponRv;

    private DiscountCouponAdapter mCouponAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_discount_coupon;
    }

    @Override
    public void initView() {
        mToolBar.setPadding(0, getStatusBarHeight(), 0, 0);

        mCouponAdapter = new DiscountCouponAdapter();
        mCouponAdapter.bindToRecyclerView(mDiscountCouponRv);
        mCouponAdapter.setEmptyView(R.layout.empty_view);
        mCouponAdapter.addFooterView(View.inflate(mActivity, R.layout.rv_footer_layout, null));
    }

    @Override
    public void initData() {
        OkGo.<List<List<NewCommodityBean>>>get(UrlConstants.getCollect)
                .execute(new GsonCallback<List<List<NewCommodityBean>>>(new TypeToken<List<List<NewCommodityBean>>>() {
                }.getType()) {
                    @Override
                    public void onSuccess(Response<List<List<NewCommodityBean>>> response) {
                        List<List<NewCommodityBean>> body = response.body();
                        List<NewCommodityBean> result = new ArrayList<>();
                        if (body != null && body.size() != 0) {
                            for (List<NewCommodityBean> newCommodityBeans : body) {
                                if (newCommodityBeans != null && newCommodityBeans.size() != 0) {
                                    result.add(newCommodityBeans.get(0));
                                }
                            }
                            mCouponAdapter.setNewData(result);
                        } else {

                        }
                    }

                    @Override
                    public void onError(Response<List<List<NewCommodityBean>>> response) {
                        super.onError(response);

                    }
                });

    }

    @Override
    public void initListener() {
        mCouponAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, final int position) {
                DialogUtils.showDialog(mActivity, "提示", "是否要删除该优惠券？", "删除",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                delete(position);
                            }
                        }, "取消",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                return true;
            }
        });

        mCouponAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                NewCommodityBean item = mCouponAdapter.getItem(position);
                if (item != null) {
                    CommodityDetailActivity.startActivity(mActivity, item.getCouponInfoId());
                }
            }
        });
    }

    private void delete(final int pos) {
        OkGo.<SimpleResponse>post(UrlConstants.deleteCollect)
                .params("coupon_id", mCouponAdapter.getData().get(pos).getCouponInfoId())
                .execute(new GsonCallback<SimpleResponse>(SimpleResponse.class) {
                    @Override
                    public void onSuccess(Response<SimpleResponse> response) {
                        if (response.body().isSuccess()) {
                            showToast("删除成功");
                            mCouponAdapter.remove(pos);
                        } else {
                            showToast(response.body().msg);
                        }
                    }
                });
    }
}
