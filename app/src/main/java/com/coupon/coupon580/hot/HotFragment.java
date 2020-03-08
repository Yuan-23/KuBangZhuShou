package com.coupon.coupon580.hot;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.coupon.coupon580.R;
import com.coupon.coupon580.core.base.BasePagerFragment;
import com.coupon.coupon580.core.data.UrlConstants;
import com.coupon.coupon580.core.model.NewActBean;
import com.coupon.coupon580.core.model.NewCommodityBean;
import com.coupon.coupon580.core.network.okgo.GsonCallback;
import com.coupon.coupon580.core.utils.DialogUtils;
import com.coupon.coupon580.home.model.PageCommodityBean;
import com.coupon.coupon580.hot.activity.CommodityDetailActivity;
import com.coupon.coupon580.hot.adapter.HotActAdapter;
import com.coupon.coupon580.hot.widget.HotHeaderView;
import com.coupon.coupon580.hot.widget.HotMainHeaderView;
import com.coupon.coupon580.search.activity.SearchActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;



/**
 * @author NoOne 2019.03.04
 */
public class HotFragment extends BasePagerFragment {
    /*@BindView(R.id.top_tl)
    TabLayout mTopTl;*/
    @BindView(R.id.share_btn)
    ImageView mShareBtn;
    @BindView(R.id.help_btn)
    ImageView mHelpBtn;
    @BindView(R.id.act_rv)
    RecyclerView mActRv;
    @BindView(R.id.top_bar)
    ViewGroup mTopBar;

    private HotActAdapter mActAdapter;
    private HotMainHeaderView mMainHeaderView;

    public static HotFragment newInstance() {

        Bundle args = new Bundle();

        HotFragment fragment = new HotFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hot;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        setStatusBarTextBlack(true);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mTopBar.getLayoutParams();
        int statusBarHeight = getStatusBarHeight();
        layoutParams.height = layoutParams.height + statusBarHeight;
        mTopBar.setLayoutParams(layoutParams);
        mTopBar.setPadding(0, statusBarHeight, 0, 0);

        /*mTopTl.addTab(mTopTl.newTab().setText("热门"));
        mTopTl.addTab(mTopTl.newTab().setText("活动"));*/

        mActAdapter = new HotActAdapter();
        mActAdapter.setHeaderFooterEmpty(true, false);
        mActAdapter.bindToRecyclerView(mActRv);

        mMainHeaderView = new HotMainHeaderView(mActivity);
        HotHeaderView mHeaderView = new HotHeaderView(mActivity);
        mActAdapter.addHeaderView(mMainHeaderView.getView());
        mActAdapter.addHeaderView(mHeaderView.getView());
        mActAdapter.addFooterView(View.inflate(mActivity, R.layout.rv_footer_layout, null));
        mActAdapter.setEmptyView(R.layout.empty_view);
    }

    @Override
    protected void initData() {

        OkGo.<PageCommodityBean>get(UrlConstants.getCommodityByType + UrlConstants.TYPE_EDU + "/1")
                .execute(new GsonCallback<PageCommodityBean>(PageCommodityBean.class) {
                    @Override
                    public void onSuccess(Response<PageCommodityBean> response) {
                        try {
                            List<NewCommodityBean> result = response.body().result;
                            NewActBean bean = new NewActBean();
                            bean.setCoverImage(result.get(0).getImage());
                            bean.setType(1);
                            bean.setData(result);
                            mActAdapter.addData(bean);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Response<PageCommodityBean> response) {
                        super.onError(response);
                        showToast(getResources().getString(R.string.network_error));
                    }
                });

        OkGo.<PageCommodityBean>get(UrlConstants.getCommodityByType + UrlConstants.TYPE_AGU + "/1")
                .execute(new GsonCallback<PageCommodityBean>(PageCommodityBean.class) {
                    @Override
                    public void onSuccess(Response<PageCommodityBean> response) {
                        try {
                            List<NewCommodityBean> result = response.body().result;
                            NewActBean bean = new NewActBean();
                            bean.setCoverImage(result.get(0).getImage());
                            bean.setType(2);
                            bean.setData(result);
                            mActAdapter.addData(bean);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Response<PageCommodityBean> response) {
                        super.onError(response);
                        showToast(getResources().getString(R.string.network_error));
                    }
                });

        OkGo.<PageCommodityBean>get(UrlConstants.getCommodityByType + UrlConstants.TYPE_SPORTS + "/1")
                .execute(new GsonCallback<PageCommodityBean>(PageCommodityBean.class) {
                    @Override
                    public void onSuccess(Response<PageCommodityBean> response) {
                        try {
                            List<NewCommodityBean> result = response.body().result;
                            NewActBean bean = new NewActBean();
                            bean.setCoverImage(result.get(0).getImage());
                            bean.setType(3);
                            bean.setData(result);
                            mActAdapter.addData(bean);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Response<PageCommodityBean> response) {
                        super.onError(response);
                        showToast(getResources().getString(R.string.network_error));
                    }
                });

        OkGo.<PageCommodityBean>get(UrlConstants.getCommodityByType + UrlConstants.TYPE_MAKEUP + "/1")
                .execute(new GsonCallback<PageCommodityBean>(PageCommodityBean.class) {
                    @Override
                    public void onSuccess(Response<PageCommodityBean> response) {
                        try {
                            List<NewCommodityBean> result = response.body().result;
                            NewActBean bean = new NewActBean();
                            bean.setCoverImage(result.get(0).getImage());
                            bean.setType(4);
                            bean.setData(result);
                            mActAdapter.addData(bean);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Response<PageCommodityBean> response) {
                        super.onError(response);
                        showToast(getResources().getString(R.string.network_error));
                    }
                });


    }

    @OnClick(R.id.top_search_bar)
    public void onClickSearchBar() {
        startActivity(SearchActivity.class);
    }

    @Override
    protected void initListener() {
       /* mTopTl.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.d(TAG, "onTabSelected: getScrollState" + mActRv.getScrollState());
                if (mActRv.getScrollState() == RecyclerView.SCROLL_STATE_IDLE) {
                    mActRv.smoothScrollToPosition(tab.getPosition());
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                if (mActRv.getScrollState() == RecyclerView.SCROLL_STATE_IDLE) {
                    mActRv.smoothScrollToPosition(tab.getPosition());
                }
            }
        });*/
       //onCLickMoreBtn();

        mActRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.d(TAG, "onScrollStateChanged: ");
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.d(TAG, "onScrolled: getScrollState:" + recyclerView.getScrollState() + "  firstVisibleItemPosition" + ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition());
//                if (recyclerView.getScrollState() != RecyclerView.SCROLL_STATE_SETTLING) {
              /*  int firstVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                if (firstVisibleItemPosition == 0) {
                    mTopTl.getTabAt(0).select();
                } else if (firstVisibleItemPosition > 0) {
                    mTopTl.getTabAt(1).select();
                }*/
//                }
            }
        });

        mActAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                NewActBean bean = mActAdapter.getItem(position);
                NewCommodityBean item;

                if (bean.getData()==null){
                    return;
                }
                switch (view.getId()){
                    case R.id.commodity_item1:
                        item = bean.getData().get(0);
                        CommodityDetailActivity.startActivity(mActivity, item.getCouponInfoId());
                        break;
                    case R.id.commodity_item2:
                        item = bean.getData().get(1);
                        CommodityDetailActivity.startActivity(mActivity, item.getCouponInfoId());
                        break;
                    case R.id.commodity_item3:
                        item = bean.getData().get(2);
                        CommodityDetailActivity.startActivity(mActivity, item.getCouponInfoId());
                        break;
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mMainHeaderView.startBanner();
    }

    @Override
    public void onStop() {
        super.onStop();
        mMainHeaderView.stopBanner();
    }

    @OnClick({R.id.share_btn, R.id.help_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.share_btn:
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, "分享自酷邦小助手：" +
                        "  领券链接🔗：" + "http://39.107.125.199:3030/coupon_home");
                shareIntent.setType("text/plain");
                startActivity(shareIntent);
                //showToast("分享成功");
                break;
            case R.id.help_btn:
                DialogUtils.showDialog(mActivity, "帮助");
                break;
        }
    }
}
