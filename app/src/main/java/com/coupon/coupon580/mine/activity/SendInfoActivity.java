package com.coupon.coupon580.mine.activity;

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
import com.coupon.coupon580.hot.activity.CommodityDetailActivity;
import com.coupon.coupon580.mine.adapter.SendInfoAdapter;
import com.coupon.coupon580.mine.model.SendInfoBean;

import butterknife.BindView;
import butterknife.OnClick;

public class SendInfoActivity extends BaseActivity {

    @BindView(R.id.send_info_rv)
    RecyclerView sCouponSendRv;
    @BindView(R.id.already_send_iv)
    ImageView sCouponAlreadyIndicator;
    @BindView(R.id.under_verify_iv)
    ImageView sCouponUnderIndicator;
    @BindView(R.id.tool_bar)
    MyToolbar sToolBar;

    private SendInfoAdapter mCouponAdapter;
    private int mCouponMode = COUPON_ALREADY;

    public static final int COUPON_ALREADY = 1;
    public static final int COUPON_UNDER = 2;

    @Override
    public int getLayoutId() {
        return R.layout.activity_send_info;
    }

    @Override
    public void initView() {
        sToolBar.setPadding(0, getStatusBarHeight(), 0, 0);
        sToolBar.addRightText("新建", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(CreateCouponActivity.class);
            }
        });
        mCouponAdapter = new SendInfoAdapter();
        mCouponAdapter.bindToRecyclerView(sCouponSendRv);
        mCouponAdapter.setEmptyView(R.layout.empty_view);
        mCouponAdapter.addFooterView(View.inflate(mActivity, R.layout.rv_footer_layout, null));

        sCouponAlreadyIndicator.setVisibility(View.VISIBLE);
        sCouponUnderIndicator.setVisibility(View.INVISIBLE);

        /*mCouponAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
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
        });*/

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

    /*private void delete(final int pos) {
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
    }*/

    private void updateOrderView() {
        sCouponAlreadyIndicator.setVisibility(View.INVISIBLE);
        sCouponUnderIndicator.setVisibility(View.INVISIBLE);
        if (mCouponMode == COUPON_ALREADY) {
            sCouponAlreadyIndicator.setVisibility(View.VISIBLE);
        } else {
            sCouponUnderIndicator.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void initData() {
        loadData();
    }

    private String getUrl() {
        if (mCouponMode == COUPON_ALREADY) {
            return UrlConstants.getAlready;
        } else {
            return UrlConstants.getUnder;
        }
    }

    private void loadData(){
        /*if (mCouponMode == COUPON_ALREADY){
            mCouponAdapter.setNewData(CommonDataProvider.getInstance().getNewCommodityList(4));
        }
        if (mCouponMode == COUPON_UNDER) {
            mCouponAdapter.setNewData(CommonDataProvider.getInstance().getNewCommodityList2(4));
        }*/
        OkGo.<SendInfoBean>get(getUrl())
                .execute(new GsonCallback<SendInfoBean>(SendInfoBean.class) {
                    public void onSuccess(Response<SendInfoBean> response) {
                        SendInfoBean body = response.body();
                        if (body != null ) {
                            mCouponAdapter.setNewData(body.getResult());
                        }
                    }
                    @Override
                    public void onError(Response<SendInfoBean> response) {

                        super.onError(response);

                    }
                });

    }

    @Override
    public void initListener() {

    }


    @OnClick({R.id.already_send_btn, R.id.under_verify_btn})
    public void onClickCouponBtn(View view) {
        int preMode = mCouponMode;
        switch (view.getId()) {
            case R.id.already_send_btn:
                mCouponMode = COUPON_ALREADY;
                break;
            case R.id.under_verify_btn:
                mCouponMode = COUPON_UNDER;
                break;
            default:
                break;
        }
        if (mCouponMode != preMode) {
            mCouponAdapter.setNewData(null);
            updateOrderView();
            loadData();
        }
    }


}
